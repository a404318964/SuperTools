package com.zwj.supertools.bean.fund;

import java.util.List;

/**
 * Created by zwj on 2017/11/20.
 */

public class RepCurFundInfo {
    private List<EnableSellFund> enableSellFundList;
    private List<CurFundInfo>  downFundInfoList;

    public List<EnableSellFund> getEnableSellFundList() {
        return enableSellFundList;
    }

    public void setEnableSellFundList(List<EnableSellFund> enableSellFundList) {
        this.enableSellFundList = enableSellFundList;
    }

    public List<CurFundInfo> getDownFundInfoList() {
        return downFundInfoList;
    }

    public void setDownFundInfoList(List<CurFundInfo> downFundInfoList) {
        this.downFundInfoList = downFundInfoList;
    }
}
