package com.zwj.supertools.bean;

/**
 * Created by zwj on 2017/11/7.
 */

public class GeneratePersonNameParam {
    private int firstnameNumber = 3;
    private int lastnamenumber = 4;

    // 0 - 女性名字； 1 - 男性名字； 2 - 男女皆可用的名字
    private int sex = 2;

    public int getFirstnameNumber() {
        return firstnameNumber;
    }

    public void setFirstnameNumber(int firstnameNumber) {
        this.firstnameNumber = firstnameNumber;
    }

    public int getLastnamenumber() {
        return lastnamenumber;
    }

    public void setLastnamenumber(int lastnamenumber) {
        this.lastnamenumber = lastnamenumber;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}