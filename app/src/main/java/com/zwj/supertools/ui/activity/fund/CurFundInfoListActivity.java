package com.zwj.supertools.ui.activity.fund;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.zhy.adapter.common.MyFragmentPagerWithTitleAdapter;
import com.zwj.customview.progress.ProgressBean;
import com.zwj.customview.progress.ProgressUtil;
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

    @Override
    protected int getContentViewId() {
        return R.layout.activity_cur_fund_info_list;
    }

    @Override
    protected void findViews() {
        titleView = getView(R.id.id_title);
        mTabLayout = getView(R.id.tabs);
        mViewPager = getView(R.id.pager);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        new ProgressBean()
                .setLoadingTip("加载中...")
                .startProgress(mContext);

        getCurFundInfo();
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

    private void getCurFundInfo() {
        new RequestBean(UrlConstant.URL_GET_CUR_FUND_INFO, RequestBean.METHOD_GET)
                .setCallback(new ParseBeanCallBack<RepCurFundInfo>(RepCurFundInfo.class) {
                    @Override
                    public void onSuccess(ResponseBean responseBean, RepCurFundInfo repCurFundInfo) {

                        enableSellFundFragment = new EnableSellFundFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("enableSellFundList", (ArrayList<? extends Parcelable>) repCurFundInfo.getEnableSellFundList());
                        enableSellFundFragment.setArguments(bundle);

                        enableBuyFundFragment = new EnableBuyFundFragment();
                        Bundle bundle2 = new Bundle();
                        bundle2.putParcelableArrayList("downFundInfoList", (ArrayList<? extends Parcelable>) repCurFundInfo.getDownFundInfoList());
                        enableBuyFundFragment.setArguments(bundle2);

                        fragments = new ArrayList<>();
                        fragments.add(enableSellFundFragment);
                        fragments.add(enableBuyFundFragment);

                        mViewPager.setAdapter(new MyFragmentPagerWithTitleAdapter(getSupportFragmentManager(),
                                fragments, getResources().getStringArray(R.array.rep_cur_fund_info_items)));

                        mTabLayout.setViewPager(mViewPager);
                    }

                    @Override
                    public void onFinished(ResponseBean responseBean) {
                        ProgressUtil.hideProgress();
                    }
                }).request(MyApplication.getGlobalContext());
    }
}
