package com.zwj.supertools.bean.fund;

/**
 * Created by zwj on 2017/11/20.
 */

/**
 * 当前可卖出的基金实体类
 */
public class EnableSellFund {
    private Fund fund;
    private CurFundInfo curFundInfo;

    // 可卖出的原因
    private String reason;
    // 可卖出份额，-1：全部；0 - 未知；大于0 则便是具体数额
    private double sellCount;

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public CurFundInfo getCurFundInfo() {
        return curFundInfo;
    }

    public void setCurFundInfo(CurFundInfo curFundInfo) {
        this.curFundInfo = curFundInfo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getSellCount() {
        return sellCount;
    }

    public void setSellCount(double sellCount) {
        this.sellCount = sellCount;
    }
}