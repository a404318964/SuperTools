package com.zwj.supertools.bean.fund;

/**
 * Created by zwj on 2017/11/20.
 */

public class CurFundInfo {
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
}
