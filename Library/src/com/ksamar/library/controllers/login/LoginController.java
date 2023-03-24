package com.ksamar.library.controllers.login;

import com.ksamar.library.entitys.User;
import com.ksamar.library.tools.SqlList;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 登录操作控制器
 */
public class LoginController {

    public LoginController() {}

    /**
     * 登录操作函数
     * @param username 用户名
     * @param password 密码
     * @return ArrayList objectList 返回列表
     */
    public ArrayList<Object> login(String username, String password) {
        ArrayList<Object> objectList = new ArrayList<>();

        ArrayList<User> userArrayList = SqlList.getUserList();

        // 循环搜索
        for (User user : userArrayList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                objectList.add(true);
                objectList.add(user.getGroups());
                return objectList;
            }
        }

        objectList.add(false);
        return objectList;
    }
}
