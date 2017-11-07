package com.zwj.supertools.bean;

/**
 * Created by zwj on 2017/11/7.
 */

public class Page {
    private int pageNo;
    private int pageNum;

    public Page() {}

    public Page(int pageNo, int pageNum) {
        this.pageNo = pageNo;
        this.pageNum = pageNum;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
