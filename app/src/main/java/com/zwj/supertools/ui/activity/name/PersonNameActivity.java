package com.zwj.supertools.ui.activity.name;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;
import com.zwj.supertools.MyApplication;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.GeneratePersonNameParam;
import com.zwj.supertools.bean.PersonNameWithBook;
import com.zwj.supertools.constant.UrlConstant;
import com.zwj.supertools.ui.activity.base.BaseAutoLayoutCommonActivity;
import com.zwj.zwjutils.CommonUtil;
import com.zwj.zwjutils.net.bean.RequestBean;
import com.zwj.zwjutils.net.bean.ResponseBean;
import com.zwj.zwjutils.net.callback.ParseListCallBack;

import java.util.ArrayList;
import java.util.List;

public class PersonNameActivity extends BaseAutoLayoutCommonActivity implements View.OnClickListener{
    private RadioButton rbBoth, rbOne, rbTwo;
    private RecyclerView rv;
    private CommonAdapter<PersonNameWithBook> adapter;
    private List<PersonNameWithBook> personNameList = new ArrayList<>();

    private GeneratePersonNameParam param = new GeneratePersonNameParam();;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_person_name;
    }

    @Override
    protected void findViews() {
        rv = getView(R.id.rv);
        rbBoth = getView(R.id.rb_both);
        rbOne = getView(R.id.rb_one);
        rbTwo = getView(R.id.rb_two);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        rbBoth.setOnClickListener(this);
        rbOne.setOnClickListener(this);
        rbTwo.setOnClickListener(this);
    }

    // 点击生成按钮
    public void generate(View view) {




        new RequestBean(UrlConstant.URL_GENERATE_PERSON_NAME, RequestBean.METHOD_POST)
                .setBodyContent(JSON.toJSONString(param))
                .setCallback(new ParseListCallBack<PersonNameWithBook>(PersonNameWithBook.class) {
                    @Override
                    public void onSuccess(ResponseBean responseBean, List<PersonNameWithBook> list) {
                        if(CommonUtil.isValidList(list)) {
                            personNameList = list;
                        }else {
                            personNameList = new ArrayList<>();
                        }

                        refreshUI();
                    }
                }).request(MyApplication.getGlobalContext());
    }

    public void refreshUI() {
        if(adapter == null) {
            adapter = new CommonAdapter<PersonNameWithBook>(mContext, R.layout.item_generate_name, personNameList) {
                @Override
                protected void convert(ViewHolder viewHolder, PersonNameWithBook item, int i) {
                    TextView tv = viewHolder.getView(R.id.tv);

                    AutoLinearLayout.LayoutParams lps = (AutoLinearLayout.LayoutParams) tv.getLayoutParams();
                    if(i % 3 == 0) {            // 最右边
//                        lps.leftMargin = AutoUtils.getPercentWidthSize(0);
                        lps.leftMargin = 0;
                    }else if(i % 3 == 2) {      // 最左边
                        lps.rightMargin = 0;
                    }
                    viewHolder.setText(R.id.tv, item.getName());
                }
            };

            rv.setLayoutManager(new GridLayoutManager(mContext, 3));
            rv.setHasFixedSize(true);
            rv.setAdapter(adapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<PersonNameWithBook>() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, PersonNameWithBook item, int position) {

                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, PersonNameWithBook item, int i) {
                    return false;
                }
            });
        }else {
            adapter.setDatasWithRefresh(personNameList);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_both:
                param.setNameCount(0);
                break;

            case R.id.rb_one:
                param.setNameCount(1);
                break;

            case R.id.rb_two:
                param.setNameCount(2);
                break;
        }
    }
}
