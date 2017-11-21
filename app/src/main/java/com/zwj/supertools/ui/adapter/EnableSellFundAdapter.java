package com.zwj.supertools.ui.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.fund.EnableSellFund;

import java.util.List;

/**
 * Created by zwj on 2017/11/20.
 */
public class EnableSellFundAdapter extends CommonAdapter<EnableSellFund> {

    public EnableSellFundAdapter(Context context, List<EnableSellFund> datas) {
        super(context, R.layout.item_enable_sell_fund, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, EnableSellFund enableSellFund, int i) {
        viewHolder.setText(R.id.tv_fund_name, enableSellFund.getFund().getName());
        viewHolder.setText(R.id.tv_sell_count, "可卖份额: "+enableSellFund.getSellCount());
        viewHolder.setText(R.id.tv_current_price, "当前估值: "+String.valueOf(enableSellFund.getCurFundInfo().getGSZ()));
        viewHolder.setText(R.id.tv_cur_increase, "当前涨幅: "+
                String.valueOf(enableSellFund.getCurFundInfo().getGSZZL()) + "%");
        viewHolder.setText(R.id.tv_reason, enableSellFund.getReason());
    }
}
