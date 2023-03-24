package com.ksamar.library.entitys;

import com.ksamar.library.tools.TimeConversion;

import java.util.Date;

/**
 * @author KSaMar
 * 图书管理系统 - 借阅图书实体
 */
public class Borrow {
    /**
     * 属性定义
     */
    private int id;
    private String bookName;
    private String isbn;
    private String username;
    private String idCard;
    private String phone;
    private Date borrowTime;
    private Date returnTime;

    public Borrow() { }

    public Borrow(int id, String bookName, String isbn, String username, String idCard, String phone, String borrowTime, String returnTime) {
        this.id = id;
        this.bookName = bookName;
        this.isbn = isbn;
        this.username = username;
        this.idCard = idCard;
        this.phone = phone;
        this.borrowTime = TimeConversion.setFormatDate(borrowTime);
        this.returnTime = TimeConversion.setFormatDate(returnTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public String toString() {
        return id + "\t" +
                bookName + '\t' +
                isbn + '\t' +
                username + '\t' +
                idCard + '\t' +
                phone + '\t' +
                TimeConversion.getFormatDate(borrowTime) + '\t' +
                TimeConversion.getFormatDate(returnTime);
    }
}
