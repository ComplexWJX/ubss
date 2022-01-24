package com.asiainfo.jvm;

import com.asiainfo.service.Driver;

import java.util.ServiceLoader;

/**
 * @author WJX
 * @title: JvmFoo
 * @projectName ubss
 * @description: TODO
 * @date 2021/10/8 0008
 */
public class JvmFoo {
    public static void main(String[] args) {
//        ConfigManager configManager = new ConfigManager();
        ServiceLoader<Driver> objects = ServiceLoader.load(Driver.class);
        for (Driver driver : objects) {
            driver.connect();
        }
    }
}
