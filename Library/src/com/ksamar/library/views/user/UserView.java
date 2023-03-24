package com.ksamar.library.views.user;

import com.ksamar.library.controllers.book.BookController;
import com.ksamar.library.controllers.book.BorrowController;
import com.ksamar.library.controllers.user.UserController;
import com.ksamar.library.entitys.Borrow;
import com.ksamar.library.entitys.User;
import com.ksamar.library.tools.HorizontalRule;
import com.ksamar.library.views.View;
import com.ksamar.library.views.share.SearchBookView;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 用户视图
 */
public class UserView extends View {

    /**
     * 属性
     */
    int bookCount;
    int borrowCount;
    int returnCount;
    int overCount;

    HorizontalRule horizontalRule;
    /**
     * 控制器
     */
    BookController bookController;
    BorrowController borrowController;
    UserController userController;


    /**
     * 用户界面
     * @param name 名称
     */
    public UserView(String name) {
        super(name);

        // 初始化
        init();

        // 试图头部
        header();

        // 用户操作
        options();

        // 关闭输入流
        scanner.close();
    }

    /**
     * 初始化
     */
    @Override
    public void init() {
        bookController = new BookController();
        borrowController = new BorrowController();
        userController = new UserController();

        horizontalRule = new HorizontalRule();

        bookCount = bookController.getCount();
        borrowCount = userController.getBorrowCount(name);
        returnCount = borrowController.getCount(name);
        overCount = borrowController.getOverCount(name);
    }

    /**
     * 头部
     */
    @Override
    public void header() {
        System.out.println("==================================================");
        System.out.println("欢迎 " + name + " 登录 Java图书管理系统");
        System.out.println("图书馆图书数量：" + bookCount);
        System.out.println("当前可借阅图书数量：" + borrowCount);
        System.out.println("当前未归还图书数量：" + returnCount);
        System.out.println("当前超时未归还图书数量：" + returnCount);
        System.out.println("==================================================");
    }

    /**
     * 用户操作
     */
    @Override
    public void options() {
        while(visible) {
            // 操作选项
            System.out.println("==================================================");
            System.out.println("请选择执行操作");
            System.out.println("1.查询图书");
            System.out.println("2.修改密码");
            System.out.println("3.查询已借阅图书");
            System.out.println("0.退出系统");
            System.out.println("==================================================");

            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    new SearchBookView().searchBook(); break;
                case 2:
                    changePassword(); break;
                case 3:
                    searchBorrowBook(); break;
                case 0:
                    visible = false; break;
                default:
                    break;
            }
        }
    }

    /**
     * 修改密码
     */
    public void changePassword() {
        System.out.print("请输入新的密码：");
        String password = scanner.next();

        User user = userController.changePassword(name, password);
        if (user != null) {
            System.out.println("修改成功");
        }
        else {
            System.out.println("修改失败，查找不到此用户");
        }
    }


    /**
     * 搜索借阅图书
     */
    public void searchBorrowBook() {
        ArrayList<Borrow> borrowArrayList = borrowController.searchBorrowBook(name);

        horizontalRule.boldHorizon();
        System.out.println("ID\t书名\tISBN\t用户名\t身份卡\t手机号\t借阅时间\t应归还时间");
        horizontalRule.boldHorizon();

        for (Borrow borrow : borrowArrayList) {
            System.out.println(borrow);
            horizontalRule.thinHorizon();
        }
    }
}
