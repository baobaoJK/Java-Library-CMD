package com.ksamar.library.entitys;

/**
 * @author KSaMar
 * 图书管理系统 - 图书实体
 */
public class Book {
    /**
     * 属性定义
     */
    private int id;
    private String groups;
    private String name;
    private String author;
    private String press;
    private double price;
    private int quantity;
    private String isbn;

    public Book() { }

    public Book(int id, String groups, String name, String author, String press, double price, int quantity, String isbn) {
        this.id = id;
        this.groups = groups;
        this.name = name;
        this.author = author;
        this.press = press;
        this.price = price;
        this.quantity = quantity;
        this.isbn = isbn;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return id + "\t" +
                groups + "\t" +
                name + "\t" +
                author + "\t" +
                press + "\t" +
                price + "\t" +
                quantity + "\t" +
                isbn;
    }
}
