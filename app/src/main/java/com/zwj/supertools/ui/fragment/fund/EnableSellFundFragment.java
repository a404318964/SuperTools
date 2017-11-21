package com.zwj.supertools.ui.fragment.fund;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zwj.supertools.R;
import com.zwj.supertools.bean.fund.EnableSellFund;
import com.zwj.supertools.ui.adapter.EnableSellFundAdapter;
import com.zwj.supertools.ui.fragment.base.BaseFragment;

import java.util.List;

/**
 * Created by zwj on 2017/11/20.
 */

public class EnableSellFundFragment extends BaseFragment {
    private RecyclerView rv;
    private List<EnableSellFund> enableSellFundList;
    private EnableSellFundAdapter enableSellFundAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_enable_sell_fund;
    }

    @Override
    protected void receiveArguments(Bundle savedInstanceState) {
        enableSellFundList = getArguments().getParcelableArrayList("enableSellFundList");
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
        enableSellFundAdapter = new EnableSellFundAdapter(mContext, enableSellFundList);

        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setHasFixedSize(true);
        rv.setAdapter(enableSellFundAdapter);
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
