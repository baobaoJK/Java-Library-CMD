package com.ksamar.library.views.admin;

import com.ksamar.library.controllers.user.UserController;
import com.ksamar.library.entitys.User;
import com.ksamar.library.tools.HorizontalRule;
import com.ksamar.library.views.View;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 用户管理试图
 */
public class UserManageView extends View {

    /**
     * 控制器
     */
    UserController userController;

    /**
     * 属性
     */
    int operation;
    HorizontalRule horizontalRule;

    public UserManageView() {
        // 初始化
        init();

        // 操作选项
        options();
    }

    /**
     * 初始化
     */
    @Override
    public void init() {
        userController = new UserController();

        horizontalRule = new HorizontalRule();
    }

    /**
     * 操作面板
     */
    @Override
    public void options() {
        while (visible) {
            System.out.println("==================================================");
            System.out.println("请选择执行操作");
            System.out.println("1.查询用户");
            System.out.println("2.增加用户");
            System.out.println("3.删除用户");
            System.out.println("4.编辑用户");
            System.out.println("0.返回");
            System.out.println("==================================================");
            operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    searchUser(); break;
                case 2:
                    addUser(); break;
                case 3:
                    deleteUser(); break;
                case 4:
                    editUser(); break;
                case 0:
                    visible = false; break;
                default:
                    break;
            }
        }
    }

    /**
     * 头部
     */
    @Override
    public void header() {
        horizontalRule.boldHorizon();
        System.out.println("ID\t组别\t名称\t用户名\t密码\t性别\t身份卡号\t手机\t身份\t可借阅图书数量\t账号状态");
        horizontalRule.boldHorizon();
    }

    /**
     * 搜索用户
     */
    public void searchUser() {
        System.out.println("==================================================");
        System.out.println("1.查询所有");
        System.out.println("2.指定查询");
        System.out.println("0.返回");
        System.out.println("==================================================");

        operation = scanner.nextInt();
        switch (operation) {
            case 1:
                searchAll(); break;
            case 2:
                userType();
                String[] searchType = {"name", "idCard", "phone"};
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
     * 搜索全部用户
     */
    public void searchAll() {
        header();

        ArrayList<User> userArrayList = userController.searchAll();
        for(User user : userArrayList) {
            System.out.println(user);
            horizontalRule.thinHorizon();
        }
    }

    /**
     * 搜索类型
     */
    public void userType() {
        System.out.println("==================================================");
        System.out.println("1.名称搜索");
        System.out.println("2.身份卡号搜索");
        System.out.println("3.手机号码搜索");
        System.out.println("==================================================");
    }

    /**
     * 指定搜索
     * @param type
     * 搜索类型
     * @param text
     * 搜索信息
     */
    public void search(String type, String text) {
        header();

        User user = null;
        ArrayList<User> userArrayList = new ArrayList<>();

        switch (type) {
            case "id":
                user = userController.searchId(text); break;
            case "name":
                userArrayList = userController.searchName(text); break;
            case "idCard":
                user = userController.searchIdCard(text); break;
            case "phone":
                user = userController.searchPhone(text); break;
            default:
                break;
        }

        if (user != null) {
            System.out.println(user);
            horizontalRule.thinHorizon();
        }

        if (!userArrayList.isEmpty()) {
            for (User tempUser : userArrayList) {
                System.out.println(tempUser);
                horizontalRule.thinHorizon();
            }
        }
    }

    /**
     * 添加用户
     */
    public void addUser() {
        System.out.println("==================================================");
        System.out.print("请输入组别：");
        String groups = scanner.next();
        System.out.print("请输入名称：");
        String name = scanner.next();
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        System.out.print("请输入性别：");
        char gender = scanner.next().charAt(0);
        System.out.print("请输入身份卡号：");
        String idCard = scanner.next();
        System.out.print("请输入手机号：");
        String phone = scanner.next();
        System.out.print("请输入身份：");
        String identity = scanner.next();
        System.out.println("==================================================");

        ArrayList<Object> objectArrayList = userController.addUser(groups, name, username, password, gender, idCard, phone, identity);

        if (objectArrayList.get(0).equals(true)) {
            System.out.println("添加成功");
        }
        else if (objectArrayList.get(1).equals(1)) {
            System.out.println("添加失败，该用户名已存在");
        }
        else if (objectArrayList.get(1).equals(2)) {
            System.out.println("添加失败，该身份号码已存在");
        }
        else if (objectArrayList.get(1).equals(3)) {
            System.out.println("添加失败，该手机号已存在");
        }
        else {
            System.out.println("添加失败");
        }
    }

    /**
     * 删除用户
     */
    public void deleteUser() {
        System.out.println("==================================================");
        System.out.print("请输入要删除的用户ID：");
        int id = scanner.nextInt();
        System.out.println("==================================================");

        int delete = userController.deleteUser(id);

        if (delete == 1) {
            System.out.println("删除成功");
        }
        else {
            System.out.println("删除失败");
        }
    }

    /**
     * 编辑用户
     */
    public void editUser() {
        System.out.println("==================================================");
        System.out.print("请输入要编辑的用户ID：");
        int id = scanner.nextInt();
        System.out.println("==================================================");

        User user = userController.searchId(String.valueOf(id));

        if (user != null) {
            boolean editState = true;
            while (editState) {
                System.out.println("==================================================");
                System.out.println(user);
                System.out.println("==================================================");
                System.out.println("1.修改组别");
                System.out.println("2.修改名称");
                System.out.println("3.修改密码");
                System.out.println("4.修改性别");
                System.out.println("5.修改身份卡号");
                System.out.println("6.修改手机号");
                System.out.println("7.修改身份");
                System.out.println("8.修改可借阅图书数量");
                System.out.println("9.修改账户状态 - 0/关闭 | 1/启用");
                System.out.println("0.退出编辑");
                System.out.println("==================================================");
                System.out.print("请输入操作选项：");
                int options = scanner.nextInt();

                if (options > 0 && options < 10) {
                    String[] editType = {"groups", "name", "password", "gender", "idCard", "phone", "identity", "bookCount", "state"};

                    System.out.print("请输入编辑信息：");
                    String text = scanner.next();

                    ArrayList<Object> objectArrayList = userController.editUser(user, editType[options - 1], text);

                    if (objectArrayList.get(0).equals(false)) {
                        if (objectArrayList.get(1).equals(1)) {
                            System.out.println("编辑失败，身份卡号已存在");
                        }
                        else if (objectArrayList.get(1).equals(2)) {
                            System.out.println("编辑失败，手机号已存在");
                        }
                    }
                }
                else if (options == 0) {
                    editState = false;
                }
            }
        }
        else {
            System.out.println("此用户不存在，无法编辑");
        }
    }
}
