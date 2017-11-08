package com.zwj.supertools.bean.xs;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * Created by zwj on 2017/11/5.
 */

@Entity
public class XsContent implements Parcelable{
    @Id
    private String id;

    private Integer bookType;

    private Integer contentType;

    private String fromBook;

    private String content;

    private String bookTypeName;
    private String contentTypeName;
    private String fromBookName;

    private Date createTime;

    @Generated(hash = 939277113)
    public XsContent(String id, Integer bookType, Integer contentType,
            String fromBook, String content, String bookTypeName,
            String contentTypeName, String fromBookName, Date createTime) {
        this.id = id;
        this.bookType = bookType;
        this.contentType = contentType;
        this.fromBook = fromBook;
        this.content = content;
        this.bookTypeName = bookTypeName;
        this.contentTypeName = contentTypeName;
        this.fromBookName = fromBookName;
        this.createTime = createTime;
    }

    @Generated(hash = 967546032)
    public XsContent() {
    }

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

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.bookType);
        dest.writeValue(this.contentType);
        dest.writeString(this.fromBook);
        dest.writeString(this.content);
        dest.writeString(this.bookTypeName);
        dest.writeString(this.contentTypeName);
        dest.writeString(this.fromBookName);
        dest.writeLong(this.createTime != null ? this.createTime.getTime() : -1);
    }

    protected XsContent(Parcel in) {
        this.id = in.readString();
        this.bookType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.contentType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fromBook = in.readString();
        this.content = in.readString();
        this.bookTypeName = in.readString();
        this.contentTypeName = in.readString();
        this.fromBookName = in.readString();
        long tmpCreateTime = in.readLong();
        this.createTime = tmpCreateTime == -1 ? null : new Date(tmpCreateTime);
    }

    public static final Creator<XsContent> CREATOR = new Creator<XsContent>() {
        @Override
        public XsContent createFromParcel(Parcel source) {
            return new XsContent(source);
        }

        @Override
        public XsContent[] newArray(int size) {
            return new XsContent[size];
        }
    };
}
