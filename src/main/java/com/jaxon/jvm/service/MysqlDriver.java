package com.jaxon.jvm.service;

/**
 * @author WJX
 * @title: MysqlDriver
 * @projectName ubss
 * @description: TODO
 * @date 2021/10/12 0012
 */
public class MysqlDriver implements Driver {
    @Override
    public void connect() {
        System.out.println("connect to mysql db.");
    }
}
