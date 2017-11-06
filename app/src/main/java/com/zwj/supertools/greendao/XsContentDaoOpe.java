package com.zwj.supertools.greendao;

import com.zwj.supertools.MyApplication;
import com.zwj.supertools.bean.XsContent;
import com.zwj.zwjutils.CommonUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by zwj on 2017/11/6.
 */

public class XsContentDaoOpe {

    /**
     * 添加数据至数据库
     */
    public static long insertData(XsContent obj) {
        return DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().insert(obj);
    }


    /**
     * 将数据实体通过事务添加至数据库
     */
    public static void insertData(List<XsContent> list) {
        if (!CommonUtil.isValidList(list)) {
            return;
        }
        DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     */
    public static void saveData(XsContent obj) {
        DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().save(obj);
    }

    /**
     * 删除数据至数据库
     */
    public static void deleteData(XsContent obj) {
        DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().delete(obj);
    }

    /**
     * 根据id删除数据至数据库
     */
    public static void deleteByKeyData( String id) {
        DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     */
    public static void deleteAllData() {
        DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().deleteAll();
    }

    /**
     * 更新数据库
     */
    public static void updateData(XsContent obj) {
        DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().update(obj);
    }

    /**
     * 查询所有数据
     */
    public static List<XsContent> queryAll() {
        QueryBuilder<XsContent> builder = DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 根据id，其他的字段类似
     */
    public static List<XsContent> queryForId(String id) {
        QueryBuilder<XsContent> builder = DbManager.getDaoSession(MyApplication.getGlobalContext()).getXsContentDao().queryBuilder();
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(StudentDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();
         */
        // Query<Student> build = builder.where(StudentDao.Properties.Id.eq(id)).build();
        // List<Student> list = build.list();
        return builder.where(XsContentDao.Properties.Id.eq(id)).list();
    }
}
