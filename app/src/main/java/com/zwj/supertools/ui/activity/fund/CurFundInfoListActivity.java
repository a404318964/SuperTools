package com.zwj.supertools.ui.activity.fund;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.zhy.adapter.common.MyFragmentPagerWithTitleAdapter;
import com.zwj.customview.titleview.CommonTitleView;
import com.zwj.customview.titleview.SimpleTitleMenuClickListener;
import com.zwj.supertools.MyApplication;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.fund.RepCurFundInfo;
import com.zwj.supertools.constant.UrlConstant;
import com.zwj.supertools.ui.activity.base.BaseAutoLayoutCommonActivity;
import com.zwj.supertools.ui.fragment.fund.EnableBuyFundFragment;
import com.zwj.supertools.ui.fragment.fund.EnableSellFundFragment;
import com.zwj.zwjutils.net.bean.RequestBean;
import com.zwj.zwjutils.net.bean.ResponseBean;
import com.zwj.zwjutils.net.callback.ParseBeanCallBack;

import java.util.ArrayList;
import java.util.List;

public class CurFundInfoListActivity extends BaseAutoLayoutCommonActivity {
    private CommonTitleView titleView;
    private ViewPager mViewPager;
    private SlidingTabLayout mTabLayout;
    private List<Fragment> fragments;
    private EnableSellFundFragment enableSellFundFragment;
    private EnableBuyFundFragment enableBuyFundFragment;
//    private RecyclerView rv;
//    private CommonAdapter<XsContent> adapter;
//    private List<XsContent> contentList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_cur_fund_info_list;
    }

    @Override
    protected void findViews() {
        titleView = getView(R.id.id_title);
//        rv = getView(R.id.rv);
        mTabLayout = getView(R.id.tabs);
        mViewPager = getView(R.id.pager);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

//        new ProgressBean()
//                .setLoadingTip("获取内容中")
//                .startProgress(mContext);

        enableSellFundFragment = new EnableSellFundFragment();
        enableBuyFundFragment = new EnableBuyFundFragment();

        fragments = new ArrayList<>();
        fragments.add(enableSellFundFragment);
        fragments.add(enableBuyFundFragment);

        mViewPager.setAdapter(new MyFragmentPagerWithTitleAdapter(getSupportFragmentManager(),
                fragments, getResources().getStringArray(R.array.rep_cur_fund_info_items)));

        mTabLayout.setViewPager(mViewPager);
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
//        if (adapter == null) {
//            adapter = new CommonAdapter<XsContent>(mContext, R.layout.item_xs_content, contentList) {
//                @Override
//                protected void convert(ViewHolder viewHolder, XsContent xsContent, int position) {
//                    viewHolder.setText(R.id.tv_content, xsContent.getContent());
//                    viewHolder.setText(R.id.tv_book, xsContent.getFromBookName());
//                    if (xsContent.getCreateTime() != null) {
//                        viewHolder.setText(R.id.tv_create_time, DateUtil.date2Str(xsContent.getCreateTime()));
//                    }
////                    viewHolder.setVisibility(R.id.line, !(position == contentList.size() - 1));
//                }
//            };
//
//            rv.setLayoutManager(new LinearLayoutManager(mContext));
//            rv.setHasFixedSize(true);
//            rv.setAdapter(adapter);
//            rv.setItemAnimator(new DefaultItemAnimator());
//
//            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<XsContent>() {
//                @Override
//                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, XsContent xsContent, int i) {
//                    Intent intent = new Intent(mContext, XsContentActivity.class);
//                    intent.putExtra(XSConstant.XS_CONTENT, xsContent);
//                    startActivity(intent);
//                }
//
//                @Override
//                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, XsContent xsContent, int i) {
//                    return false;
//                }
//            });
//        } else {
//            adapter.setDatasWithRefresh(contentList);
//        }
//
//        ProgressUtil.hideProgress();
    }

    private void getCurFundInfo() {
        new RequestBean(UrlConstant.URL_GET_CUR_FUND_INFO, RequestBean.METHOD_GET)
                .setCallback(new ParseBeanCallBack<RepCurFundInfo>(RepCurFundInfo.class) {
                    @Override
                    public void onSuccess(ResponseBean responseBean, RepCurFundInfo repCurFundInfo) {

                    }
                }).request(MyApplication.getGlobalContext());
    }
}
