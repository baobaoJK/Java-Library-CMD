package com.ksamar.library.entitys;

import com.ksamar.library.tools.TimeConversion;

import java.util.Date;

/**
 * @author KSaMar
 * 图书管理系统 - 操作纪录列表
 */
public class Operation {
    /**
     * 属性定义
     */
    int id;
    Date operationTime;
    String name;
    String bookName;
    String info;

    public Operation() { }

    public Operation(int id, String operationTime, String name, String bookName, String info) {
        this.id = id;
        this.operationTime = TimeConversion.setFormatDate(operationTime);
        this.name = name;
        this.bookName = bookName;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return  id + "\t" +
                TimeConversion.getFormatDate(operationTime) + '\t' +
                name + '\t' +
                bookName + '\t' +
                info + '\t';
    }
}
