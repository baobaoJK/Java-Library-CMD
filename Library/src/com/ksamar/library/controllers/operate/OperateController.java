package com.ksamar.library.controllers.operate;

import com.ksamar.library.entitys.Operation;
import com.ksamar.library.tools.SqlList;

import java.util.ArrayList;

/**
 * @author KSaMar
 * 图书管理系统 - 日志操作控制器
 */
public class OperateController {

    ArrayList<Operation> operationArrayList;

    public OperateController() {
        operationArrayList = SqlList.getOperationList();
    }

    /**
     * 搜索所有日志信息
     * @return ArrayList
     * 返回日志信息
     */
    public ArrayList<Operation> searchAll() {
        return operationArrayList;
    }
}
