package com.zwj.supertools.bean.fund;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zwj on 2017/11/20.
 */

public class CurFundInfo implements Parcelable{
    private String FCODE;
    private String SHORTNAME;
    private String PDATE;           // 上次的估值日期
    private double NAV;             // 上次的估值
    private double ACCNAV;         //
    private double NAVCHGRT;       // 上次涨幅
    private double GSZ;             // 最新估值
    private double GSZZL;           // 最新涨幅
    private String GZTIME;          // 最新估值时间


    public String getFCODE() {
        return FCODE;
    }

    public void setFCODE(String FCODE) {
        this.FCODE = FCODE;
    }

    public String getSHORTNAME() {
        return SHORTNAME;
    }

    public void setSHORTNAME(String SHORTNAME) {
        this.SHORTNAME = SHORTNAME;
    }

    public String getPDATE() {
        return PDATE;
    }

    public void setPDATE(String PDATE) {
        this.PDATE = PDATE;
    }

    public double getNAV() {
        return NAV;
    }

    public void setNAV(double NAV) {
        this.NAV = NAV;
    }

    public double getACCNAV() {
        return ACCNAV;
    }

    public void setACCNAV(double ACCNAV) {
        this.ACCNAV = ACCNAV;
    }

    public double getNAVCHGRT() {
        return NAVCHGRT;
    }

    public void setNAVCHGRT(double NAVCHGRT) {
        this.NAVCHGRT = NAVCHGRT;
    }

    public double getGSZ() {
        return GSZ;
    }

    public void setGSZ(double GSZ) {
        this.GSZ = GSZ;
    }

    public double getGSZZL() {
        return GSZZL;
    }

    public void setGSZZL(double GSZZL) {
        this.GSZZL = GSZZL;
    }

    public String getGZTIME() {
        return GZTIME;
    }

    public void setGZTIME(String GZTIME) {
        this.GZTIME = GZTIME;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FCODE);
        dest.writeString(this.SHORTNAME);
        dest.writeString(this.PDATE);
        dest.writeDouble(this.NAV);
        dest.writeDouble(this.ACCNAV);
        dest.writeDouble(this.NAVCHGRT);
        dest.writeDouble(this.GSZ);
        dest.writeDouble(this.GSZZL);
        dest.writeString(this.GZTIME);
    }

    public CurFundInfo() {
    }

    protected CurFundInfo(Parcel in) {
        this.FCODE = in.readString();
        this.SHORTNAME = in.readString();
        this.PDATE = in.readString();
        this.NAV = in.readDouble();
        this.ACCNAV = in.readDouble();
        this.NAVCHGRT = in.readDouble();
        this.GSZ = in.readDouble();
        this.GSZZL = in.readDouble();
        this.GZTIME = in.readString();
    }

    public static final Creator<CurFundInfo> CREATOR = new Creator<CurFundInfo>() {
        @Override
        public CurFundInfo createFromParcel(Parcel source) {
            return new CurFundInfo(source);
        }

        @Override
        public CurFundInfo[] newArray(int size) {
            return new CurFundInfo[size];
        }
    };
}
