package com.ksamar.library.views;

import java.util.Scanner;

/**
 * @author KSaMar
 * 图书管理系统 - 默认视图
 */
public class View {
    public Scanner scanner;

    public String name;
    public boolean visible;

    /**
     * 初始化
     */
    public View() {
        scanner = new Scanner(System.in);
        visible = true;
    }

    public View(String name) {
        this();
        this.name = name;
    }

    public void init() { }

    public void header() { }

    public void options() { }
}
