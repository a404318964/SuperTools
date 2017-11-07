package com.zwj.supertools.bean.xs;

/**
 * Created by zwj on 2017/11/7.
 */

public class XsBook {
    private String id;

    private String name;

    private Integer bookType;

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

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }
}
