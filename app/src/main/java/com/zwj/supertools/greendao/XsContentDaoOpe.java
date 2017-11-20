package com.zwj.supertools.greendao;

import com.zwj.supertools.MyApplication;
import com.zwj.supertools.bean.Page;
import com.zwj.supertools.bean.xs.XsContent;
import com.zwj.zwjutils.CommonUtil;

import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by zwj on 2017/11/6.
 */

public class XsContentDaoOpe {

    /**
     * 添加数据至数据库
     */
    public static long insertData(XsContent obj) {
        if(obj.getCreateTime() == null) {
            obj.setCreateTime(new Date());
        }
        return DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().insert(obj);
    }


    /**
     * 将数据实体通过事务添加至数据库
     */
    public static void insertData(List<XsContent> list) {
        if (!CommonUtil.isValidList(list)) {
            return;
        }
        DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     */
    public static void saveData(XsContent obj) {
        DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().save(obj);
    }

    /**
     * 删除数据至数据库
     */
    public static void deleteData(XsContent obj) {
        DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().delete(obj);
    }

    /**
     * 根据id删除数据至数据库
     */
    public static void deleteByKeyData( String id) {
        DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     */
    public static void deleteAllData() {
        DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().deleteAll();
    }

    /**
     * 更新数据库
     */
    public static void updateData(XsContent obj) {
        DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().update(obj);
    }

    /**
     * 查询所有数据
     */
    public static List<XsContent> queryAll() {
        QueryBuilder<XsContent> builder = DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 根据id，其他的字段类似
     */
    public static List<XsContent> queryForId(String id) {
        QueryBuilder<XsContent> builder = DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD).getXsContentDao().queryBuilder();
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(StudentDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();
         */
        // Query<Student> build = builder.where(StudentDao.Properties.Id.eq(id)).build();
        // List<Student> list = build.list();
        return builder.where(XsContentDao.Properties.Id.eq(id)).list();
    }

    /**
     * 查询所有数据(异步)
     */
    public static void asyncQueryAll(AsyncOperationListener asyncOperationListener) {
        asyncQueryAll(asyncOperationListener, null);
    }

    /**
     * 查询所有数据(异步)
     */
    public static void asyncQueryAll(AsyncOperationListener asyncOperationListener, Page page) {
        AsyncSession async = DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD)
                .startAsyncSession();
        async.setListener(asyncOperationListener);

        QueryBuilder<XsContent> builder = DbManager.getDaoSession(MyApplication.getGlobalContext(), DbManager.DB_MY, DbManager.DB_MY_PASSWORD)
                .getXsContentDao().queryBuilder();

        if(page != null) {
            builder.offset(page.getPageNo())
                    .limit(page.getPageNum());
        }

        async.queryList(builder.orderDesc(XsContentDao.Properties.CreateTime).build());
    }

}
