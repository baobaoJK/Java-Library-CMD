package com.ksamar.library.views.login;

import com.ksamar.library.controllers.login.LoginController;
import com.ksamar.library.tools.Initialization;
import com.ksamar.library.views.user.UserView;
import com.ksamar.library.views.View;
import com.ksamar.library.views.admin.AdminView;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 登录页面
 */
public class LoginView extends View {

    /**
     * 属性
     */
    String username;
    String password;

    /**
     * 登录页面
     */
    public LoginView() {
        super();

        // 初始化数据
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
        new Initialization();
    }

    /**
     * 图形界面
     */
    @Override
    public void header() {
        System.out.println("==================================================");
        System.out.println("Java图书管理系统 v1.0");
        System.out.println("作者：KSaMar/和泉纱雾");
        System.out.println("==================================================");
    }

    /**
     * 用户操作界面
     */
    @Override
    public void options() {
        // 判断用户输入
        while (visible) {
            System.out.print("账号：");
            username = scanner.next();
            System.out.print("密码：");
            password = scanner.next();

            // 登录操作
            LoginController loginController = new LoginController();
            ArrayList<Object> loginList = loginController.login(username, password);

            if (loginList.get(0).equals(true)) {
                String groups = loginList.get(1).toString();
                // 切换视图
                switch (groups) {
                    case "admin":
                        new AdminView(username);
                        break;
                    case "user":
                        new UserView(username);
                        break;
                    default:
                        break;
                }
                visible = false;
                break;
            } else {
                System.out.println("账号或密码错误,请重新登录！");
            }
        }
    }
}
