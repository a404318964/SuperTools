package com.zwj.supertools.ui.activity.xs;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.zwj.customview.titleview.CommonTitleView;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.XsContent;
import com.zwj.supertools.greendao.XsContentDaoOpe;
import com.zwj.supertools.ui.activity.base.BaseAutoLayoutCommonActivity;
import com.zwj.zwjutils.LogUtils;

import java.util.List;

public class XsContentListActivity extends BaseAutoLayoutCommonActivity {
    private CommonTitleView titleView;
    private RecyclerView rv;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_xs_content_list;
    }

    @Override
    protected void findViews() {
        titleView = getView(R.id.id_title);
        rv = getView(R.id.rv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

//        new ProgressBean()
//                .setLoadingTip("获取内容中")
//                .startProgress(mContext);

        // TODO 异步
        List<XsContent> contentList = XsContentDaoOpe.queryAll();
        LogUtils.i(TAG, "contentList --> "+ JSON.toJSONString(contentList));
    }

    @Override
    protected void setListener() {

    }
}
