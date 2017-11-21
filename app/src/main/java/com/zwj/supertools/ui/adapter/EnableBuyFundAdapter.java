package com.zwj.supertools.ui.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.fund.CurFundInfo;

import java.util.List;

/**
 * Created by zwj on 2017/11/20.
 */
public class EnableBuyFundAdapter extends CommonAdapter<CurFundInfo> {

    public EnableBuyFundAdapter(Context context, List<CurFundInfo> datas) {
        super(context, R.layout.item_enable_buy_fund, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, CurFundInfo curFundInfo, int i) {
        viewHolder.setText(R.id.tv_fund_name, curFundInfo.getSHORTNAME());
        viewHolder.setText(R.id.tv_current_price, String.valueOf(curFundInfo.getGSZ()));
    }
}
