package com.zwj.supertools.ui.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zwj.supertools.bean.fund.EnableSellFund;

import java.util.List;

/**
 * Created by zwj on 2017/11/20.
 */
public class EnableSellFundAdapter extends CommonAdapter<EnableSellFund> {

    public EnableSellFundAdapter(Context context, int layoutId, List<EnableSellFund> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, EnableSellFund enableSellFund, int i) {

    }
}
