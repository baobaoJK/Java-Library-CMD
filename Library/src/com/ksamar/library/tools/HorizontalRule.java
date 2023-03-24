package com.ksamar.library.tools;

/**
 * @author KSaMar
 * 图书管理系统 - 生成水平线
 */
public class HorizontalRule {
    public void boldHorizon() {
        horizon("=", 100);
    }
    public void boldHorizon(int n) {
        horizon("=", n);
    }

    public void thinHorizon() {
        horizon("-", 100);
    }
    public void thinHorizon(int n) {
        horizon("-", n);
    }

    public void horizon(String str, int n) {
        for(int i = 0; i < n; i++) {
            System.out.print(str);
        }
        System.out.println();
    }
}
