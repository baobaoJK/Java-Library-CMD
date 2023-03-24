package com.ksamar.library.views.admin;

import com.ksamar.library.controllers.book.BookController;
import com.ksamar.library.controllers.book.BorrowController;
import com.ksamar.library.controllers.user.UserController;
import com.ksamar.library.entitys.Book;
import com.ksamar.library.entitys.Borrow;
import com.ksamar.library.entitys.User;
import com.ksamar.library.tools.HorizontalRule;
import com.ksamar.library.views.View;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 搜索图书界面
 */
public class BorrowView extends View {

    /**
     * 控制器
     */
    BorrowController borrowController;
    BookController bookController;
    UserController userController;

    /**
     * 属性
     */
    HorizontalRule horizontalRule;
    int operation;

    public BorrowView() {
        borrowController = new BorrowController();
        bookController = new BookController();
        userController = new UserController();

        horizontalRule = new HorizontalRule();
    }

    /**
     * 借阅图书
     */
    public void borrowBook() {

        boolean borrowState = true;

        while (borrowState) {
            System.out.println("==================================================");
            System.out.println("借阅图书");
            System.out.println("==================================================");
            System.out.print("请输入要借阅的图书ISBN号码：");
            String isbn = scanner.next();

            Book book = bookController.searchIsbn(isbn);

            if (book != null) {
                System.out.println("==================================================");
                System.out.println(book);
                System.out.println("==================================================");
                System.out.println("1.确定借阅");
                System.out.println("0.重新输入");
                System.out.println("==================================================");

                operation = scanner.nextInt();

                if (operation == 1) {
                    System.out.print("请输入要借阅的用户身份卡号：");
                    String idCard = scanner.next();

                    User user = userController.searchIdCard(idCard);

                    if (user != null) {
                        ArrayList<Object> borrowArrayList = borrowController.borrowBook(book, user);

                        if (borrowArrayList.get(0).equals(true)) {
                            System.out.println("借阅成功");
                        }
                        else if (borrowArrayList.get(1).equals(-1)) {
                            System.out.println("此账户已被冻结，无法借阅");
                        }
                        else if (borrowArrayList.get(1).equals(1)) {
                            System.out.println("此书数量为 0 无法借阅");
                        }
                        else if (borrowArrayList.get(1).equals(2)) {
                            System.out.println("当前账户已超过借阅上限");
                        }
                        else {
                            System.out.println("借阅失败");
                        }
                        borrowState = false;
                    }
                    else {
                        System.out.println("此用户不存在，请重新输入");
                    }
                }
            }
            else {
                System.out.println("此书不存在，请重新输入");
            }
        }
    }

    /**
     * 归还图书
     */
    public void returnBook() {
        boolean returnState = true;

        while (returnState) {
            System.out.println("==================================================");
            System.out.println("归还图书");
            System.out.println("==================================================");
            System.out.print("请输入要归还图书的身份卡号码：");
            String idCard = scanner.next();

            User user = userController.searchIdCard(idCard);

            if (user != null) {
                ArrayList<Borrow> borrowArrayList = borrowController.searchBorrowBook(user.getUsername());
                int index = 1;
                System.out.println("==================================================");
                for (Borrow borrow : borrowArrayList) {
                    System.out.println(index++ + ". " + borrow);
                }
                System.out.println("==================================================");
                System.out.print("请输入要归还的图书编号：");

                operation = scanner.nextInt();

                ArrayList<Object> returnArrayList = borrowController.returnBook(borrowArrayList.get(operation - 1), user);

                if (returnArrayList.get(0).equals(true)) {
                    System.out.println("归还成功");
                    returnState = false;
                }
                else {
                    System.out.println("归还失败");
                }
            }
            else {
                System.out.println("此用户不存在，请重新输入");
            }
        }
    }

    /**
     * 搜索借阅图书
     */
    public void searchBorrowBook() {
        ArrayList<Borrow> borrowArrayList = borrowController.searchBorrowBook();

        horizontalRule.boldHorizon();
        System.out.println("ID\t书名\tISBN\t用户名\t身份卡\t手机号\t借阅时间\t应归还时间");
        horizontalRule.boldHorizon();

        for (Borrow borrow : borrowArrayList) {
            System.out.println(borrow);
            horizontalRule.thinHorizon();
        }
    }

    /**
     * 搜索超时未归还图书
     */
    public void searchOverTimeBook() {
        System.out.println("==================================================");
        System.out.println("1.查询所有");
        System.out.println("2.指定查询");
        System.out.println("0.返回");
        System.out.println("==================================================");

        operation = scanner.nextInt();
        switch (operation) {
            case 1:
                searchOverTimeBorrowsAll(); break;
            case 2:
                borrowType();
                String[] searchType = {"username", "idCard", "phone"};
                System.out.print("请选择搜索类型:");
                int type = scanner.nextInt();
                System.out.print("请输入信息:");
                String text = scanner.next();
                search(searchType[type - 1], text);
                break;
            default:
                break;
        }
    }

    /**
     * 头部
     */
    @Override
    public void header() {
        horizontalRule.boldHorizon();
        System.out.println("ID\t书名\tISBN号码\t用户名\t身份卡号\t手机号\t借阅时间\t应归还时间");
        horizontalRule.boldHorizon();
    }

    /**
     * 搜索类型
     */
    public void borrowType() {
        System.out.println("==================================================");
        System.out.println("1.用户名搜索");
        System.out.println("2.身份号搜索");
        System.out.println("3.手机号搜索");
        System.out.println("0.返回");
        System.out.println("==================================================");
    }

    /**
     * 搜索超时未归还图书
     */
    public void searchOverTimeBorrowsAll() {
        header();

        ArrayList<Borrow> borrowArrayList = borrowController.searchOverTimeAll();

        for (Borrow borrow : borrowArrayList) {
            System.out.println(borrow);
            horizontalRule.thinHorizon();
        }
    }

    /**
     * 指定搜索
     * @param type 搜索类型
     * @param text 搜索信息
     */
    public void search(String type, String text) {
        header();

        ArrayList<Borrow> borrowArrayList = new ArrayList<>();

        switch (type) {
            case "username":
                borrowArrayList = borrowController.searchOverTimeUsername(text); break;
            case "idCard":
                borrowArrayList = borrowController.searchOverTimeIdCard(text); break;
            case "phone":
                borrowArrayList = borrowController.searchOverTimePhone(text); break;
            default:
                break;
        }

        if (!borrowArrayList.isEmpty()) {
            for (Borrow tempBorrow : borrowArrayList) {
                System.out.println(tempBorrow);
                horizontalRule.thinHorizon();
            }
        }
    }
}
