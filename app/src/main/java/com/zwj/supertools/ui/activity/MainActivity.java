package com.zwj.supertools.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;
import com.zwj.supertools.R;
import com.zwj.supertools.ui.activity.base.BaseAutoLayoutCommonActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAutoLayoutCommonActivity {
    private RecyclerView rv;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        rv = getView(R.id.rv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        rv.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv.setHasFixedSize(true);

        List<String> menuList = new ArrayList<>();
        menuList.add("基金");
        menuList.add("小说");
        menuList.add("名称生成");


        CommonAdapter<String> adapter = new CommonAdapter<String>(mContext, R.layout.item_main_menu, menuList) {
            @Override
            protected void convert(ViewHolder viewHolder, String s, int i) {
                TextView tv = viewHolder.getView(R.id.tv);

                AutoLinearLayout.LayoutParams lps = (AutoLinearLayout.LayoutParams) tv.getLayoutParams();
                if(i % 2 == 0) {
                    lps.rightMargin = AutoUtils.getPercentWidthSize(18);
                }else {
                    lps.leftMargin = AutoUtils.getPercentWidthSize(18);
                }
                viewHolder.setText(R.id.tv, s);
            }
        };

        rv.setAdapter(adapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, String deviceBean, int i) {
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, String deviceBean, int i) {
                return false;
            }
        });

    }

    @Override
    protected void setListener() {

    }


}
