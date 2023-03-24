package com.ksamar.library.controllers.book;

import com.ksamar.library.entitys.Book;
import com.ksamar.library.tools.SqlList;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 图书操作控制器
 */
public class BookController {

    /**
     * 图书数据库
     */
    ArrayList<Book> bookArrayList;

    public BookController() {
        bookArrayList = SqlList.getBookList();
    }

    /**
     * 搜索所有图书
     */
    public ArrayList<Book> searchAll() {
        return bookArrayList;
    }

    /**
     * ID搜索
     * @param text 图书ID
     * @return Book book
     */
    public Book searchId(String text) {
        for (Book book : bookArrayList) {
            if (String.valueOf(book.getId()).equals(text)) {
                return book;
            }
        }
        return null;
    }

    /**
     * 组别搜索
     * @param text 组别
     * @return ArrayList newArrayList
     */
    public ArrayList<Book> searchGroups(String text) {
        ArrayList<Book> newArrayList = new ArrayList<>();
        for (Book book : bookArrayList) {
            if (book.getGroups().equals(text)) {
                newArrayList.add(book);
            }
        }
        return newArrayList;
    }

    /**
     * 书名搜索
     * @param text 书名
     * @return ArrayList newArrayList
     */
    public ArrayList<Book> searchName(String text) {
        ArrayList<Book> newArrayList = new ArrayList<>();
        for(Book book : bookArrayList) {
            if(book.getName().equals(text)) {
                newArrayList.add(book);
            }
        }
        return newArrayList;
    }

    /**
     * 作者搜索
     * @param text 作者名称
     * @return ArrayList newArrayList
     */
    public ArrayList<Book> searchAuthor(String text) {
        ArrayList<Book> newArrayList = new ArrayList<>();
        for(Book book : bookArrayList) {
            if(book.getAuthor().equals(text)) {
                newArrayList.add(book);
            }
        }
        return newArrayList;
    }

    /**
     * 出版社搜索
     * @param text 出版社名称
     * @return ArrayList newArrayList
     */
    public ArrayList<Book> searchPress(String text) {
        ArrayList<Book> newArrayList = new ArrayList<>();
        for(Book book : bookArrayList) {
            if(book.getPress().equals(text)) {
                newArrayList.add(book);
            }
        }
        return newArrayList;
    }

    /**
     * ISBN号码搜索
     * @param text ISBN号码
     * @return book
     */
    public Book searchIsbn(String text) {
        for(Book book : bookArrayList) {
            if(book.getIsbn().equals(text)) {
                return book;
            }
        }

        return null;
    }

    /**
     * 添加图书
     * @param groups 图书组别
     * @param name 图书名称
     * @param author 图书作者
     * @param press 图书出版社
     * @param price 图书价格
     * @param quantity 图书数量
     * @param isbn ISBN号码
     * @return ArrayList objectArrayList
     * 状态
     * 1 ISBN号码存在
     */
    public ArrayList<Object> addBook(String groups, String name, String author, String press, double price, int quantity, String isbn) {
        ArrayList<Object> objectArrayList = new ArrayList<>();
        objectArrayList.add(false);

        Book book = searchIsbn(isbn);

        if (book == null) {
            int id = SqlList.getBookListId() + 1;
            Book newBook = new Book(id, groups, name, author, press, price, quantity, isbn);
            if (bookArrayList.add(newBook)) {
                objectArrayList.set(0, true);
                SqlList.setBookListId(id);
            }
        }
        else {
            objectArrayList.add(1);
        }

        return objectArrayList;
    }

    /**
     * 删除图书
     * @param id 图书ID
     * @return int
     */
    public int deleteBook(int id) {
        Book book = searchId(String.valueOf(id));
        if (book != null) {
            if (bookArrayList.remove(book)) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 编辑图书
     * @param book 图书
     * @param type 编辑类型
     * @param text 编辑信息
     * @return ArrayList objectArrayList
     */
    public ArrayList<Object> editBook(Book book, String type, String text) {
        ArrayList<Object> objectArrayList = new ArrayList<>();
        objectArrayList.add(true);

        switch (type) {
            case "groups":
                book.setGroups(text); break;
            case "name":
                book.setName(text); break;
            case "author":
                book.setAuthor(text); break;
            case "press":
                book.setPress(text); break;
            case "price":
                book.setPrice(Double.parseDouble(text)); break;
            case "quantity":
                book.setQuantity(Integer.parseInt(text)); break;
            case "isbn":
                Book bookTemp = searchIsbn(text);
                if (bookTemp == null) {
                    book.setIsbn(text);
                    break;
                }
                else {
                    objectArrayList.set(0, false);
                }
                break;
            default:
                break;
        }

        return objectArrayList;
    }

    /**
     * 获取图书数量
     * @return int
     */
    public int getCount() {
        int count = 0;
        for (Book book : bookArrayList) {
            count += book.getQuantity();
        }
        return count;
    }
}
