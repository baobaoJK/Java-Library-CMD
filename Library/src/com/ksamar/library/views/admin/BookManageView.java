package com.ksamar.library.views.admin;

import com.ksamar.library.controllers.book.BookController;
import com.ksamar.library.controllers.book.BorrowController;
import com.ksamar.library.entitys.Book;
import com.ksamar.library.tools.HorizontalRule;
import com.ksamar.library.views.View;
import com.ksamar.library.views.share.SearchBookView;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 图书管理试图
 */
public class BookManageView extends View {

    /**
     * 控制器
     */
    BookController bookController;
    BorrowController borrowController;

    /**
     * 属性
     */
    int operation;
    HorizontalRule horizontalRule;

    public BookManageView() {
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
        bookController = new BookController();
        borrowController = new BorrowController();

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
            System.out.println("1.查询图书");
            System.out.println("2.增加图书");
            System.out.println("3.删除图书");
            System.out.println("4.编辑图书");
            System.out.println("5.借阅图书");
            System.out.println("6.归还图书");
            System.out.println("7.查询借阅图书");
            System.out.println("8.超时未归还图书查询");
            System.out.println("0.返回");
            System.out.println("==================================================");

            operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    new SearchBookView().searchBook(); break;
                case 2:
                    addBook(); break;
                case 3:
                    deleteBook(); break;
                case 4:
                    editBook(); break;
                case 5:
                    new BorrowView().borrowBook(); break;
                case 6:
                    new BorrowView().returnBook(); break;
                case 7:
                    new BorrowView().searchBorrowBook(); break;
                case 8:
                    new BorrowView().searchOverTimeBook(); break;
                case 0:
                    visible = false; break;
                default:
                    break;
            }
        }
    }

    /**
     * 添加图书
     */
    public void addBook() {
        System.out.println("==================================================");
        System.out.print("请输入组别：");
        String groups = scanner.next();
        System.out.print("请输入书名：");
        String name = scanner.next();
        System.out.print("请输入作者：");
        String author = scanner.next();
        System.out.print("请输入出版社：");
        String press = scanner.next();
        System.out.print("请输入价格：");
        double price = scanner.nextDouble();
        System.out.print("请输入数量：");
        int quantity = scanner.nextInt();
        System.out.print("请输入ISBN号码：");
        String isbn = scanner.next();
        System.out.println("==================================================");

        ArrayList<Object> objectArrayList = bookController.addBook(groups, name, author, press, price, quantity, isbn);

        if (objectArrayList.get(0).equals(true)) {
            System.out.println("添加成功");
        }
        else {
            System.out.println("ISBN重复，添加失败");
        }
    }

    /**
     * 删除图书
     */
    public void deleteBook() {
        System.out.println("==================================================");
        System.out.print("请输入要删除的图书ID：");
        int id = scanner.nextInt();
        System.out.println("==================================================");

        int delete = bookController.deleteBook(id);

        if (delete == 1) {
            System.out.println("删除成功");
        }
        else {
            System.out.println("删除失败");
        }
    }

    /**
     * 编辑图书
     */
    public void editBook() {
        System.out.println("==================================================");
        System.out.print("请输入要编辑的图书ID：");
        int id = scanner.nextInt();
        System.out.println("==================================================");

        Book book = bookController.searchId(String.valueOf(id));
        if (book != null) {
            boolean editState = true;
            while (editState) {
                System.out.println("==================================================");
                System.out.println(book);
                System.out.println("==================================================");
                System.out.println("1.编辑组别");
                System.out.println("2.编辑书名");
                System.out.println("3.编辑作者");
                System.out.println("4.编辑出版社");
                System.out.println("5.编辑价格");
                System.out.println("6.编辑数量");
                System.out.println("7.编辑ISBN");
                System.out.println("0.退出编辑");
                System.out.println("==================================================");
                System.out.print("请输入操作选项：");
                int options = scanner.nextInt();

                if (options > 0 && options < 8) {
                    String[] editType = {"groups", "name", "author", "press", "price", "quantity", "isbn"};

                    System.out.print("请输入编辑信息：");
                    String text = scanner.next();

                    ArrayList<Object> objectArrayList = bookController.editBook(book, editType[options - 1], text);
                    if (objectArrayList.get(0).equals(false)) {
                        System.out.println("编辑失败，ISBN已存在");
                    }
                }
                else if (options == 0) {
                    editState = false;
                }
            }
        }
        else {
            System.out.println("此书不存在，无法编辑");
        }
    }
}
