package com.zwj.supertools.ui.activity.xs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zwj.customview.progress.ProgressBean;
import com.zwj.customview.progress.ProgressUtil;
import com.zwj.customview.titleview.CommonTitleView;
import com.zwj.customview.titleview.SimpleTitleMenuClickListener;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.Page;
import com.zwj.supertools.bean.xs.XsContent;
import com.zwj.supertools.constant.XSConstant;
import com.zwj.supertools.greendao.XsContentDaoOpe;
import com.zwj.supertools.ui.activity.base.BaseAutoLayoutCommonActivity;
import com.zwj.zwjutils.DateUtil;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;

import java.util.List;

public class XsContentListActivity extends BaseAutoLayoutCommonActivity {
    private CommonTitleView titleView;
    private RecyclerView rv;
    private CommonAdapter<XsContent> adapter;
    private List<XsContent> contentList;

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

        new ProgressBean()
                .setLoadingTip("获取内容中")
                .startProgress(mContext);

        XsContentDaoOpe.asyncQueryAll(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (isFinishing())
                    return;

                contentList = (List<XsContent>) operation.getResult();
//                LogUtils.i(TAG, "contentList --> "+ JSON.toJSONString(contentList));
                refreshUI();
            }
        }, new Page(0, 10));
    }

    @Override
    protected void setListener() {
        titleView.setOnTitleMenuClickListener(new SimpleTitleMenuClickListener(){
            @Override
            public void onClickImLeftListener() {
                finish();
            }
        });
    }

    private void refreshUI() {
        if (adapter == null) {
            adapter = new CommonAdapter<XsContent>(mContext, R.layout.item_xs_content, contentList) {
                @Override
                protected void convert(ViewHolder viewHolder, XsContent xsContent, int position) {
                    viewHolder.setText(R.id.tv_content, xsContent.getContent());
                    viewHolder.setText(R.id.tv_book, xsContent.getFromBookName());
                    if (xsContent.getCreateTime() != null) {
                        viewHolder.setText(R.id.tv_create_time, DateUtil.date2Str(xsContent.getCreateTime()));
                    }
//                    viewHolder.setVisibility(R.id.line, !(position == contentList.size() - 1));
                }
            };

            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setHasFixedSize(true);
            rv.setAdapter(adapter);
            rv.setItemAnimator(new DefaultItemAnimator());

            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<XsContent>() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, XsContent xsContent, int i) {
                    Intent intent = new Intent(mContext, XsContentActivity.class);
                    intent.putExtra(XSConstant.XS_CONTENT, xsContent);
                    startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, XsContent xsContent, int i) {
                    return false;
                }
            });
        } else {
            adapter.setDatasWithRefresh(contentList);
        }

        ProgressUtil.hideProgress();
    }
}
