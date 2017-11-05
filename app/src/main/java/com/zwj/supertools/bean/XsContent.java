package com.zwj.supertools.bean;

/**
 * Created by zwj on 2017/11/5.
 */

public class XsContent {
    private String id;

    private Integer bookType;

    private Integer contentType;

    private String fromBook;

    private String content;

    private String bookTypeName;
    private String contentTypeName;
    private String fromBookName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getFromBook() {
        return fromBook;
    }

    public void setFromBook(String fromBook) {
        this.fromBook = fromBook == null ? null : fromBook.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public String getFromBookName() {
        return fromBookName;
    }

    public void setFromBookName(String fromBookName) {
        this.fromBookName = fromBookName;
    }
}
