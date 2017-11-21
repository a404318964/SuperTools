package com.zwj.supertools.bean.fund;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by zwj on 2017/11/20.
 */

public class Fund implements Parcelable{
    private String id;

    private String name;

    private String code;

    private Boolean bad;

    private Boolean disable;

    private Date createTime;

    private Double presupposeIncrease;

    private Double increase;

    private Double profit;

    private Double buyMoney;

    private Double buyCount;

    private Double totalSellMoney;

    private Double beforeProfit;

    private String buyRateFlag;

    private String sellRateFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Boolean getBad() {
        return bad;
    }

    public void setBad(Boolean bad) {
        this.bad = bad;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getPresupposeIncrease() {
        return presupposeIncrease;
    }

    public void setPresupposeIncrease(Double presupposeIncrease) {
        this.presupposeIncrease = presupposeIncrease;
    }

    public Double getIncrease() {
        return increase;
    }

    public void setIncrease(Double increase) {
        this.increase = increase;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }

    public Double getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Double buyCount) {
        this.buyCount = buyCount;
    }

    public Double getTotalSellMoney() {
        return totalSellMoney;
    }

    public void setTotalSellMoney(Double totalSellMoney) {
        this.totalSellMoney = totalSellMoney;
    }

    public Double getBeforeProfit() {
        return beforeProfit;
    }

    public void setBeforeProfit(Double beforeProfit) {
        this.beforeProfit = beforeProfit;
    }

    public String getBuyRateFlag() {
        return buyRateFlag;
    }

    public void setBuyRateFlag(String buyRateFlag) {
        this.buyRateFlag = buyRateFlag == null ? null : buyRateFlag.trim();
    }

    public String getSellRateFlag() {
        return sellRateFlag;
    }

    public void setSellRateFlag(String sellRateFlag) {
        this.sellRateFlag = sellRateFlag == null ? null : sellRateFlag.trim();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}