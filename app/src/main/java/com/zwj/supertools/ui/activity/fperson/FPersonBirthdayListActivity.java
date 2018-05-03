package com.zwj.supertools.ui.activity.fperson;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.zwj.customview.progress.ProgressBean;
import com.zwj.customview.progress.ProgressUtil;
import com.zwj.customview.titleview.CommonTitleView;
import com.zwj.customview.titleview.SimpleTitleMenuClickListener;
import com.zwj.supertools.MyApplication;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.fperson.FPerson;
import com.zwj.supertools.constant.UrlConstant;
import com.zwj.supertools.ui.activity.base.BaseAutoLayoutCommonActivity;
import com.zwj.supertools.ui.adapter.fperson.BirthdayAdapter;
import com.zwj.zwjutils.JsonUtil;
import com.zwj.zwjutils.net.bean.RequestBean;
import com.zwj.zwjutils.net.bean.ResponseBean;
import com.zwj.zwjutils.net.callback.ParseListCallBack;

import java.util.List;

public class FPersonBirthdayListActivity extends BaseAutoLayoutCommonActivity {
    private CommonTitleView titleView;
    private RecyclerView rv;
    private BirthdayAdapter birthdayAdapter;
//    private List<FPerson> fPersonList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fperson_birthday_list;
    }

    @Override
    protected void findViews() {
        titleView = getView(R.id.id_title);
        rv = getView(R.id.rv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        String content = getIntent().getStringExtra("content");
        if(!TextUtils.isEmpty(content)) {
            refreshUI(JsonUtil.getObjects(content, FPerson.class));
        }else {
            new ProgressBean()
                    .setLoadingTip("获取数据中")
                    .startProgress(mContext);

            new RequestBean(UrlConstant.URL_GET_4_BIRTHDAY, RequestBean.METHOD_GET)
                    .setCallback(new ParseListCallBack<FPerson>(FPerson.class) {

                        @Override
                        public void onSuccess(ResponseBean responseBean, List<FPerson> list) {
                            refreshUI(list);
                        }

                        @Override
                        public void onFinished(ResponseBean responseBean) {
                            ProgressUtil.hideProgress();
                        }
                    }).request(MyApplication.getGlobalContext());
        }
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

    private void refreshUI(List<FPerson> fPersonList) {
        if (birthdayAdapter == null) {
            birthdayAdapter = new BirthdayAdapter(mContext, fPersonList);

            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setHasFixedSize(true);
            rv.setAdapter(birthdayAdapter);
            rv.setItemAnimator(new DefaultItemAnimator());
        } else {
            birthdayAdapter.setDatasWithRefresh(fPersonList);
        }
    }
}
