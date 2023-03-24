package com.ksamar.library.tools;

import com.ksamar.library.entitys.Book;
import com.ksamar.library.entitys.Borrow;
import com.ksamar.library.entitys.Operation;
import com.ksamar.library.entitys.User;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 数据库
 */
public class SqlList {
    /**
     * 数据库
     */
    private final static ArrayList<Book> bookList = new ArrayList<>();
    private final static ArrayList<User> userList = new ArrayList<>();
    private final static ArrayList<Borrow> borrowList = new ArrayList<>();
    private final static ArrayList<Operation> operationList = new ArrayList<>();

    private static int bookListId = 0;
    private static int userListId = 0;
    private static int borrowListId = 0;
    private static int operationListId = 0;

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static ArrayList<Borrow> getBorrowList() {
        return borrowList;
    }

    public static ArrayList<Operation> getOperationList() {
        return operationList;
    }

    public static int getBookListId() {
        return bookListId;
    }

    public static int getUserListId() {
        return userListId;
    }

    public static int getBorrowListId() {
        return borrowListId;
    }

    public static int getOperationListId() {
        return operationListId;
    }

    public static void setBookListId(int bookListId) {
        SqlList.bookListId = bookListId;
    }

    public static void setUserListId(int userListId) {
        SqlList.userListId = userListId;
    }

    public static void setBorrowListId(int borrowListId) {
        SqlList.borrowListId = borrowListId;
    }

    public static void setOperationListId(int operationListId) {
        SqlList.operationListId = operationListId;
    }
}
