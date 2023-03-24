package com.ksamar.library.views.share;

import com.ksamar.library.controllers.book.BookController;
import com.ksamar.library.entitys.Book;
import com.ksamar.library.tools.HorizontalRule;
import com.ksamar.library.views.View;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 搜索图书界面
 */
public class SearchBookView extends View {

    /**
     * 控制器
     */
    BookController bookController;

    /**
     * 属性
     */
    HorizontalRule horizontalRule;
    int operation;

    public SearchBookView() {
        bookController = new BookController();

        horizontalRule = new HorizontalRule();
    }

    /**
     * 搜索图书
     */
    public void searchBook() {
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
                bookType();
                String[] searchType = {"id", "groups", "name", "author", "press", "isbn"};
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
        System.out.println("ID\t组别\t书名\t作者\t出版社\t价格\t数量\tISBN号码");
        horizontalRule.boldHorizon();
    }

    /**
     * 搜索全部图书
     */
    public void searchAll() {
        header();

        ArrayList<Book> bookArrayList = bookController.searchAll();
        for (Book book : bookArrayList) {
            System.out.println(book);
            horizontalRule.thinHorizon();
        }
    }

    /**
     * 搜索类型
     */
    public void bookType() {
        System.out.println("==================================================");
        System.out.println("1.ID搜索");
        System.out.println("2.组别搜索");
        System.out.println("3.书名搜索");
        System.out.println("4.作者搜索");
        System.out.println("5.出版社搜索");
        System.out.println("6.ISBN号码搜索");
        System.out.println("0.返回");
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

        Book book = null;
        ArrayList<Book> bookArrayList = new ArrayList<>();

        switch (type) {
            case "id":
                book = bookController.searchId(text); break;
            case "groups":
                bookArrayList = bookController.searchGroups(text); break;
            case "name":
                bookArrayList = bookController.searchName(text); break;
            case "author":
                bookArrayList = bookController.searchAuthor(text); break;
            case "press":
                bookArrayList = bookController.searchPress(text); break;
            case "isbn":
                book = bookController.searchIsbn(text); break;
            default:
                break;
        }

        if (book != null) {
            System.out.println(book);
            horizontalRule.thinHorizon();
        }

        if (!bookArrayList.isEmpty()) {
            for (Book tempBook : bookArrayList) {
                System.out.println(tempBook);
                horizontalRule.thinHorizon();
            }
        }
    }
}
