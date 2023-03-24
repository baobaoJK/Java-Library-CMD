package com.ksamar.library.controllers.book;

import com.ksamar.library.entitys.Book;
import com.ksamar.library.entitys.Borrow;
import com.ksamar.library.entitys.Operation;
import com.ksamar.library.entitys.User;
import com.ksamar.library.tools.SqlList;
import com.ksamar.library.tools.TimeConversion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author KSaMar
 * 图书管理系统 - 借阅图书控制器
 */
public class BorrowController {

    /**
     * 属性
     */
    ArrayList<Borrow> borrowArrayList;
    ArrayList<Operation> operationArrayList;

    BookController bookController;

    public BorrowController() {
        borrowArrayList = SqlList.getBorrowList();
        operationArrayList = SqlList.getOperationList();

        bookController = new BookController();
    }

    /**
     * 获取借阅图书数量
     * @return int
     */
    public int getCount() {
        return borrowArrayList.size();
    }

    /**
     * 以用户名获取借阅图书数量
     * @param text 用户名
     * @return int
     */
    public int getCount(String text) {
        int count = 0;
        for (Borrow borrow : borrowArrayList) {
            if (borrow.getUsername().equals(text)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取超时未归还图书数量
     * @return int
     */
    public int getOverCount() {
        int count = 0;
        for(Borrow borrow : borrowArrayList) {
            Date date = new Date();
            if (borrow.getReturnTime().before(date)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取用户超时未归还图书数量
     * @param name 名称
     * @return int
     */
    public int getOverCount(String name) {
        int count = 0;
        for(Borrow borrow : borrowArrayList) {
            Date date = new Date();
            if (borrow.getReturnTime().before(date) && borrow.getUsername().equals(name)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 以用户名搜索为归还的图书
     * @param text 用户名
     * @return ArrayList newArrayList
     */
    public ArrayList<Borrow> searchUsername(String text) {
        ArrayList<Borrow> newArrayList = new ArrayList<>();

        for (Borrow borrow : borrowArrayList) {
            if (borrow.getUsername().equals(text)) {
                newArrayList.add(borrow);
            }
        }

        return newArrayList;
    }

    /**
     * 以身份卡搜索未归还的图书
     * @param text 身份卡
     * @return ArrayList newArrayList
     */
    public ArrayList<Borrow> searchIdCard(String text) {
        ArrayList<Borrow> newArrayList = new ArrayList<>();

        for (Borrow borrow : borrowArrayList) {
            if (borrow.getIdCard().equals(text)) {
                newArrayList.add(borrow);
            }
        }

        return newArrayList;
    }

    /**
     * 以手机号搜索未归还的图书
     * @param text 手机号
     * @return ArrayList newArrayList
     */
    public ArrayList<Borrow> searchPhone(String text) {
        ArrayList<Borrow> newArrayList = new ArrayList<>();

        for (Borrow borrow : borrowArrayList) {
            if (borrow.getPhone().equals(text)) {
                newArrayList.add(borrow);
            }
        }

        return newArrayList;
    }

    /**
     * 查询借阅的图书列表
     * @return ArrayList borrowArrayList
     */
    public ArrayList<Borrow> searchBorrowBook() {
        return borrowArrayList;
    }

    /**
     * 查询借阅的图书列表
     * @param name 名称
     * @return ArrayList newArrayList
     */
    public ArrayList<Borrow> searchBorrowBook(String name) {
        ArrayList<Borrow> newArrayList = new ArrayList<>();

        for (Borrow borrow : borrowArrayList) {
            if (borrow.getUsername().equals(name)) {
                newArrayList.add(borrow);
            }
        }

        return newArrayList;
    }

    /**
     * 搜索超时未归还图书
     * @return ArrayList newArrayList
     */
    public ArrayList<Borrow> searchOverTimeAll() {
        ArrayList<Borrow> newArrayList = new ArrayList<>();

        Date date = new Date();
        for(Borrow borrow : borrowArrayList) {
            if(borrow.getReturnTime().before(date)) {
                newArrayList.add(borrow);
            }
        }

        return newArrayList;
    }

    /**
     * 搜索超时未归还图书
     * @return ArrayList newArrayList
     */
    public ArrayList<Borrow> searchOverTimeUsername(String text) {
        ArrayList<Borrow> newArrayList = new ArrayList<>();

        Date date = new Date();
        for(Borrow borrow : borrowArrayList) {
            if(borrow.getUsername().equals(text) && borrow.getReturnTime().before(date)) {
                newArrayList.add(borrow);
            }
        }

        return newArrayList;
    }
    /**
     * 以身份卡号搜索超时未归还图书
     * @return ArrayList newArrayList
     */
    public ArrayList<Borrow> searchOverTimeIdCard(String text) {
        ArrayList<Borrow> newArrayList = new ArrayList<>();

        Date date = new Date();
        for(Borrow borrow : borrowArrayList) {
            if(borrow.getIdCard().equals(text) && borrow.getReturnTime().before(date)) {
                newArrayList.add(borrow);
            }
        }

        return newArrayList;
    }
    /**
     * 以手机号搜索超时未归还图书
     * @return ArrayList newArrayList
     */
    public ArrayList<Borrow> searchOverTimePhone(String text) {
        ArrayList<Borrow> newArrayList = new ArrayList<>();

        Date date = new Date();
        for(Borrow borrow : borrowArrayList) {
            if(borrow.getPhone().equals(text) && borrow.getReturnTime().before(date)) {
                newArrayList.add(borrow);
            }
        }

        return newArrayList;
    }
    /**
     * 借阅图书操作
     * @param book 图书名称
     * @param user 用户名称
     * @return ArrayList objectArrayList
     *
     * 状态
     * -1 冻结
     *  1 图书数量为 0
     *  2 超出借阅数量
     */
    public ArrayList<Object> borrowBook(Book book, User user) {
        ArrayList<Object> objectArrayList = new ArrayList<>();
        objectArrayList.add(false);

        if (user.getState() != 0) {
            if (book.getQuantity() != 0) {
                if (user.getBookCount() != 0) {

                    // 借阅操作
                    int id = SqlList.getBorrowListId() + 1;
                    Date date = new Date();
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, 7);
                    String borrowTime = TimeConversion.getFormatDate(date);
                    String returnTime = TimeConversion.getFormatDate(calendar.getTime());
                    Borrow borrow = new Borrow(id, book.getName(), book.getIsbn(), user.getUsername(), user.getIdCard(), user.getPhone(), borrowTime, returnTime);

                    if (borrowArrayList.add(borrow)) {
                        objectArrayList.set(0, true);
                        // 图书数量 -1
                        book.setQuantity(book.getQuantity() - 1);
                        // 用户借阅数量 -1
                        user.setBookCount(user.getBookCount() - 1);
                        // 插入日志
                        int operationId = SqlList.getOperationListId() + 1;
                        String text = user.getUsername() + " 借走了一本 " + book.getName();
                        Operation operation = new Operation(operationId, borrowTime, user.getUsername(), book.getName(), text);
                        operationArrayList.add(operation);
                        // id自增
                        SqlList.setBorrowListId(id);
                        SqlList.setOperationListId(operationId);
                    }
                }
                else {
                    objectArrayList.add(2);
                }
            }
            else {
                objectArrayList.add(1);
            }
        }
        else {
            objectArrayList.add(-1);
        }

        return objectArrayList;
    }

    /**
     * 归还图书
     * @param borrow 归还图书信息
     * @param user 归还用户
     * @return ArrayList objectArrayList
     */
    public ArrayList<Object> returnBook(Borrow borrow, User user) {
        ArrayList<Object> objectArrayList = new ArrayList<>();

        if (borrowArrayList.remove(borrow)) {
            objectArrayList.add(true);
            user.setBookCount(user.getBookCount() + 1);
            Book book = bookController.searchIsbn(borrow.getIsbn());
            book.setQuantity(book.getQuantity() + 1);

            // 插入日志
            int operationId = SqlList.getOperationListId() + 1;
            String returnTime = TimeConversion.getFormatDate(new Date());
            String text = user.getUsername() + " 归还了一本 " + book.getName();
            Operation operation = new Operation(operationId, returnTime, user.getUsername(), book.getName(), text);
            operationArrayList.add(operation);
            SqlList.setOperationListId(SqlList.getOperationListId() + 1);
        }
        else {
            objectArrayList.add(false);
        }

        return objectArrayList;
    }
}
