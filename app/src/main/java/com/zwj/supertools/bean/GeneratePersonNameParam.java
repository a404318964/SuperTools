package com.zwj.supertools.bean;

/**
 * 生成人名的参数实体类
 */
public class GeneratePersonNameParam {
    private int firstnameNumber = 3;
    private int lastnamenumber = 4;

    // 1 - 单字； 2 - 双字； 其他则认为都要
    private int nameCount;      // 名字个数，一般就是单字或者双字

    // 0 - 女性名字； 1 - 男性名字； 2 - 男女皆可用的名字
    private int sex = 2;

    // 姓氏
    private String firstName;

    // 名字中的一个字
    private String lastNameForOne;
    // 双字时所在位置
    private int lastName4OnePosition = 1;

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

    public int getNameCount() {
        return nameCount;
    }

    public void setNameCount(int nameCount) {
        this.nameCount = nameCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNameForOne() {
        return lastNameForOne;
    }

    public void setLastNameForOne(String lastNameForOne) {
        this.lastNameForOne = lastNameForOne;
    }

    public int getLastName4OnePosition() {
        return lastName4OnePosition;
    }

    public void setLastName4OnePosition(int lastName4OnePosition) {
        this.lastName4OnePosition = lastName4OnePosition;
    }
}
