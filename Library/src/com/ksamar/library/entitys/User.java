package com.ksamar.library.entitys;

/**
 * @author KSaMar
 * 图书管理系统 - 用户实体
 */
public class User {
    /**
     * 属性定义
     */
    int id;
    String groups;
    String name;
    String username;
    String password;
    char gender;
    String idCard;
    String phone;
    String identity;
    int bookCount;
    int state;

    public User() { }

    public User(int id, String groups, String name, String username, String password, char gender, String idCard, String phone, String identity, int bookCount, int state) {
        this.id = id;
        this.groups = groups;
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.idCard = idCard;
        this.phone = phone;
        this.identity = identity;
        this.bookCount = bookCount;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return  id + "\t" +
                groups + '\t' +
                name + '\t' +
                username + '\t' +
                password + '\t' +
                gender + '\t' +
                idCard + '\t' +
                phone + '\t' +
                identity + '\t' +
                bookCount + '\t' +
                (state==1?"启用":"停用");
    }
}
