package com.ksamar.library.views.admin;

import com.ksamar.library.controllers.book.BookController;
import com.ksamar.library.controllers.book.BorrowController;
import com.ksamar.library.controllers.operate.OperateController;
import com.ksamar.library.controllers.user.UserController;
import com.ksamar.library.entitys.Operation;
import com.ksamar.library.entitys.User;
import com.ksamar.library.views.View;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 管理员视图
 */
public class AdminView extends View {

    /**
     * 属性
     */
    int bookCount;
    int overCount;
    int borrowCount;
    int userCount;

    /**
     * 控制器
     */
    BookController bookController;
    BorrowController borrowController;
    UserController userController;
    OperateController operateController;

    /**
     * 管理员界面
     * @param name 名称
     */
    public AdminView(String name) {
        super(name);

        // 初始化
        init();

        // 视图头部
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
        operateController = new OperateController();

        bookCount = bookController.getCount();
        overCount = borrowController.getOverCount();
        borrowCount = borrowController.getCount();
        userCount = userController.getCount();
    }

    /**
     * 头部
     */
    @Override
    public void header() {
        System.out.println("==================================================");
        System.out.println("欢迎 " + name + " 登录 Java图书管理系统");
        System.out.println("图书馆图书数量：" + bookCount);
        System.out.println("超时未归还图书数量：" + overCount);
        System.out.println("借出图书数量：" + borrowCount);
        System.out.println("图书馆用户数量：" + userCount);
        System.out.println("==================================================");
    }

    /**
     * 用户操作
     */
    @Override
    public void options() {
        // 操作选项
        while(visible) {
            System.out.println("==================================================");
            System.out.println("请选择执行操作");
            System.out.println("1.图书管理");
            System.out.println("2.用户管理");
            System.out.println("3.修改密码");
            System.out.println("4.查看日志");
            System.out.println("0.退出系统");
            System.out.println("==================================================");

            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    new BookManageView(); break;
                case 2:
                    new UserManageView(); break;
                case 3:
                    changePassword(); break;
                case 4:
                    showLog(); break;
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
     * 显示日志
     */
    public void showLog() {
        ArrayList<Operation> operationArrayList = operateController.searchAll();

        System.out.println("==================================================");
        System.out.println("ID\t操作时间\t名称\t书名\t详细");
        System.out.println("==================================================");

        for (Operation operation : operationArrayList) {
            System.out.println(operation);
            System.out.println("==================================================");
        }
    }
}
