package com.zwj.supertools.ui.fragment.fund;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zwj.supertools.R;
import com.zwj.supertools.bean.fund.CurFundInfo;
import com.zwj.supertools.ui.adapter.EnableBuyFundAdapter;
import com.zwj.supertools.ui.fragment.base.BaseFragment;

import java.util.List;

/**
 * Created by zwj on 2017/11/20.
 */

public class EnableBuyFundFragment extends BaseFragment {
    private RecyclerView rv;
    private List<CurFundInfo> curFundInfoList;
    private EnableBuyFundAdapter enableBuyFundAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_enable_buy_fund;
    }

    @Override
    protected void receiveArguments(Bundle savedInstanceState) {
        curFundInfoList = getArguments().getParcelableArrayList("downFundInfoList");
    }

    @Override
    protected void initView() {
        rv = getView(R.id.rv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        refreshUI();
    }

    @Override
    protected void setListener() {

    }

    private void refreshUI() {
        enableBuyFundAdapter = new EnableBuyFundAdapter(mContext, curFundInfoList);

        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setHasFixedSize(true);
        rv.setAdapter(enableBuyFundAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());

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
    }
}
