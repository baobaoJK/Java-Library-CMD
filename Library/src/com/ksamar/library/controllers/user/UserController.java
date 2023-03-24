package com.ksamar.library.controllers.user;

import com.ksamar.library.entitys.User;
import com.ksamar.library.tools.SqlList;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 用户操作控制器
 */
public class UserController {

    /**
     * 用户数据库
     */
    ArrayList<User> userArrayList;

    public UserController() {
        userArrayList = SqlList.getUserList();
    }

    /**
     * 搜索所有用户
     * @return ArrayList userArrayList 返回用户列表
     */
    public ArrayList<User> searchAll() {
        return userArrayList;
    }

    /**
     * ID搜索
     * @param text 用户ID
     * @return User user 返回用户
     */
    public User searchId(String text) {
        for (User user : userArrayList) {
            if(String.valueOf(user.getId()).equals(text)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 名称搜索
     * @param text 名称
     * @return ArrayLst newArrayList
     */
    public ArrayList<User> searchName(String text) {
        ArrayList<User> newArrayList = new ArrayList<>();
        for (User user : userArrayList) {
            if (user.getName().equals(text)) {
                newArrayList.add(user);
            }
        }
        return newArrayList;
    }

    /**
     * 身份卡搜索
     * @param text 身份卡信息
     * @return User user
     */
    public User searchIdCard(String text) {
        for (User user : userArrayList) {
            if (user.getIdCard().equals(text)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 手机号码搜索
     * @param text 手机号
     * @return User user
     */
    public User searchPhone(String text) {
        for (User user : userArrayList) {
            if (user.getPhone().equals(text)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 以用户名搜索用户
     * @param username 用户名
     * @return User user
     */
    public User searchUsername(String username) {
        for (User user : userArrayList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 添加用户
     * @param groups 用户所属组
     * @param name 名称
     * @param username 用户名
     * @param password 密码
     * @param gender 性别
     * @param idCard 身份卡
     * @param phone 手机号
     * @param identity 身份
     * @return ArrayList objectArrayList
     *
     * 状态
     * 1 用户存在
     * 2 身份卡重复
     * 3 手机号重复
     */
    public ArrayList<Object> addUser(String groups, String name, String username, String password, char gender, String idCard, String phone, String identity) {
        ArrayList<Object> objectArrayList = new ArrayList<>();
        objectArrayList.add(false);

        User user = searchUsername(username);

        if (user == null) {
            user = searchIdCard(idCard);
            if (user == null) {
                user = searchPhone(phone);
                if (user == null) {
                    int id = SqlList.getUserListId() + 1;
                    int bookCount = getBookCount(identity);
                    User newUser = new User(id, groups, name, username, password, gender, idCard, phone, identity, bookCount, 1);
                    if (userArrayList.add(newUser)) {
                        objectArrayList.set(0, true);
                        SqlList.setUserListId(id);
                    }
                }
                else {
                    objectArrayList.add(3);
                }
            }
            else {
                objectArrayList.add(2);
            }
        }
        else {
            objectArrayList.add(1);
        }

        return objectArrayList;
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return int
     */
    public int deleteUser(int id) {
        User user = searchId(String.valueOf(id));
        if (user != null && id != 1) {
            if (userArrayList.remove(user)) {
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
     * 编辑用户
     * @param user 修改用户
     * @param type 修改类型
     * @param text 修改信息
     * @return ArrayList objectArrayList
     *
     * 状态
     * 1 身份卡号存在
     * 2 手机号存在
     */
    public ArrayList<Object> editUser(User user, String type, String text) {
        ArrayList<Object> objectArrayList = new ArrayList<>();
        objectArrayList.add(true);

        User userTemp;

        switch (type) {
            case "groups":
                user.setGroups(text); break;
            case "name":
                user.setName(text); break;
            case "password":
                user.setPassword(text); break;
            case "gender":
                user.setGender(text.charAt(0)); break;
            case "idCard":
                userTemp = searchIdCard(text);
                if (userTemp == null) {
                    user.setIdCard(text);
                }
                else {
                    objectArrayList.set(0, false);
                    objectArrayList.add(1);
                }
                break;
            case "phone":
                userTemp = searchPhone(text);
                if (userTemp == null) {
                    user.setPhone(text);
                }
                else {
                    objectArrayList.set(0, false);
                    objectArrayList.add(2);
                }
                break;
            case "identity":
                user.setIdentity(text); break;
            case "bookCount":
                user.setBookCount(Integer.parseInt(text)); break;
            case "state":
                user.setState(Integer.parseInt(text)); break;
            default:
                break;
        }

        return objectArrayList;
    }

    /**
     * 修改密码
     * @param username 用户名
     * @param password 密码
     * @return User user
     */
    public User changePassword(String username, String password) {
        User user = searchUsername(username);
        if (user != null) {
           user.setPassword(password);
        }
        return user;
    }

    /**
     * 获取用户数量
     * @return int
     */
    public int getCount() {
        return userArrayList.size();
    }

    /**
     * 获取身份可借阅书籍数量
     * @param identity 身份
     * @return int
     */
    public int getBookCount(String identity) {
        switch (identity) {
            case "学生":
                return 3;
            case "教师":
                return 5;
            default:
                break;
        }
        return 3;
    }

    /**
     * 获取可借阅图书数量
     * @param name 名称
     * @return int
     */
    public int getBorrowCount(String name) {
        return searchUsername(name).getBookCount();
    }
}
