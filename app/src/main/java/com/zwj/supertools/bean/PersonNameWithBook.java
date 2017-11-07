package com.zwj.supertools.bean;

import com.zwj.supertools.bean.xs.XsBook;

import java.util.List;

/**
 * Created by zwj on 2017/11/7.
 */

public class PersonNameWithBook {
    private String name;
    private List<XsBook> fromBookNameList;

    public PersonNameWithBook() {

    }

    public PersonNameWithBook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XsBook> getFromBookNameList() {
        return fromBookNameList;
    }

    public void setFromBookNameList(List<XsBook> fromBookNameList) {
        this.fromBookNameList = fromBookNameList;
    }
}
