package com.ksamar.library.tools;

import com.ksamar.library.entitys.Book;
import com.ksamar.library.entitys.Borrow;
import com.ksamar.library.entitys.Operation;
import com.ksamar.library.entitys.User;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author KSaMar
 * 图书管理系统 - 初始化数据
 */
public class Initialization {

    private final ArrayList<Book> bookArrayList;
    private final ArrayList<User> userArrayList;
    private final ArrayList<Borrow> borrowArrayList;
    private final ArrayList<Operation> operationArrayList;

    public Initialization() {

        bookArrayList = SqlList.getBookList();
        userArrayList = SqlList.getUserList();
        borrowArrayList = SqlList.getBorrowList();
        operationArrayList = SqlList.getOperationList();

        setBookInfo();
        setUserInfo();
        setBorrowInfo();
        setOperateInfo();
    }

    /**
     * 设置图书信息
     */
    private void setBookInfo() {
        Book book1 = new Book(1, "地理", "房龙地理", "房龙", "文汇出版社", 29.00, 9, "9780000000001");
        Book book2 = new Book(2, "地理", "地理学与生活", "[美] 阿瑟·格蒂斯 ", "世界图书出版公司", 49.00, 9, "9780000000002");
        Book book3 = new Book(3, "地理", "古老阳光的末日", "Thom Hartmann", "上海远东出版社", 20.00, 9, "9780000000003");
        Book book4 = new Book(4, "法律", "洞穴奇案", "[美] 萨伯", "生活.读书.新知三联书店", 18.00, 9, "9780000000004");
        Book book5 = new Book(5, "法律", "西窗法雨", "刘星", "法律出版社", 24.00, 9, "9780000000005");
        Book book6 = new Book(6, "法律", "审判为什么不公正", "[英] 卡德里", "新星出版社", 49.50, 9, "9780000000006");
        Book book7 = new Book(7, "军事", "亮剑（舒适阅读版）", "都梁", "北京联合出版公司", 45.00, 9, "9780000000007");
        Book book8 = new Book(8, "军事", "二战记忆 安妮日记", "[德] 安妮·弗兰克", "人民文学出版社", 23.00, 9, "9780000000008");
        Book book9 = new Book(9, "军事", "亮剑", "都梁", "解放军文艺出版社", 25.00, 9, "9780000000009");
        Book book10 = new Book(10, "历史", "历史是什么？", "爱德华·霍列特·卡尔", "商务印书馆", 19.00, 9, "9780000000010");
        Book book11 = new Book(11, "历史", "中国史学史", "金毓黻", "商务印书馆", 19.00, 9, "9780000000011");
        Book book12 = new Book(12, "历史", "史记选", "[清] 储欣", "商务印书馆", 48.00, 9, "9780000000012");
        Book book13 = new Book(13, "计算机", "Java从入门到精通 ", "明日科技", "清华大学出版社", 69.00, 9, "9780000000013");
        Book book14 = new Book(14, "计算机", "C++从入门到精通", "李伟明", "清华大学出版社", 49.00, 9, "9780000000014");
        Book book15 = new Book(15, "计算机", "PHP从入门到精通", "千锋教育高教产品研发部", "清华大学出版社", 59.00, 9, "9780000000015");
        
        bookArrayList.addAll(Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12, book13, book14, book15));
        SqlList.setBookListId(bookArrayList.size());
    }

    /**
     * 设置用户信息
     */
    private void setUserInfo() {
        // 用户信息
        User admin = new User(1, "admin", "管理员", "admin", "123456", '无', "A000001", "13700000000", "管理员", 999, 1);
        User user1 = new User(2, "user", "张三", "张三", "123456", '男', "A000002", "13700000001", "学生", 3, 1);
        User user2 = new User(3, "user", "李四", "李四", "123456", '男', "A000003", "13700000002", "教师", 5, 1);

        userArrayList.add(admin);
        userArrayList.add(user1);
        userArrayList.add(user2);

        User temp = new User(4, "user", "和泉纱雾", "KSaMar", "123456", '女', "A000004", "13700000003", "学生", 3, 1);
        userArrayList.add(temp);

        SqlList.setUserListId(userArrayList.size());
    }

    /**
     * 设置借出图书数量信息
     */
    private void setBorrowInfo() {
        Borrow borrow1 = new Borrow(1, "房龙地理", "9780000000001", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-22 15:00:00");
        Borrow borrow2 = new Borrow(2, "地理学与生活", "9780000000002", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-22 15:00:00");
        Borrow borrow3 = new Borrow(3, "古老阳光的末日", "9780000000003", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-22 15:00:00");
        Borrow borrow4 = new Borrow(4, "洞穴奇案", "9780000000004", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-22 15:00:00");
        Borrow borrow5 = new Borrow(5, "西窗法雨", "9780000000005", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-22 15:00:00");
        Borrow borrow6 = new Borrow(6, "审判为什么不公正", "9780000000006", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow7 = new Borrow(7, "亮剑（舒适阅读版）", "9780000000007", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow8 = new Borrow(8, "二战记忆 安妮日记", "9780000000008", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow9 = new Borrow(9, "亮剑", "9780000000009", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow10 = new Borrow(10, "历史是什么？", "9780000000010", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow11 = new Borrow(11, "中国史学史", "9780000000011", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow12 = new Borrow(12, "史记选", "9780000000012", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow13 = new Borrow(13, "Java从入门到精通 ", "9780000000013", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow14 = new Borrow(14, "C++从入门到精通", "9780000000014", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");
        Borrow borrow15 = new Borrow(15, "PHP从入门到精通", "9780000000015", "张三", "A000002", "13700000001", "2023-03-15 10:00:00", "2023-03-31 15:00:00");

        borrowArrayList.addAll(Arrays.asList(borrow1, borrow2, borrow3, borrow4, borrow5, borrow6, borrow7, borrow8, borrow9, borrow10, borrow11, borrow12, borrow13, borrow14, borrow15));
        SqlList.setBorrowListId(borrowArrayList.size());
    }

    /**
     * 设置日志信息
     */
    private void setOperateInfo() {
        Operation operation1 = new Operation(1, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation2 = new Operation(2, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation3 = new Operation(3, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation4 = new Operation(4, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation5 = new Operation(5, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation6 = new Operation(6, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation7 = new Operation(7, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation8 = new Operation(8, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation9 = new Operation(9, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");
        Operation operation10 = new Operation(10, "2023-03-15 10:00:00", "张三", "房龙地理", "借走了一本房龙地理");

        operationArrayList.addAll(Arrays.asList(operation1, operation2, operation3, operation4, operation5, operation6, operation7, operation8, operation9, operation10));
        SqlList.setOperationListId(operationArrayList.size());
    }
}
