package com.jaxon.jvm;

import com.jaxon.jvm.service.Driver;

import java.util.ServiceLoader;

/**
 * @author WJX
 * @title: SPILoader
 * @projectName ubss
 * @description: TODO
 * @date 2021/10/8 0008
 */
public class SPILoader {
    public static void main(String[] args) {
//        ConfigManager configManager = new ConfigManager();
        ServiceLoader<Driver> objects = ServiceLoader.load(Driver.class);
        for (Driver driver : objects) {
            driver.connect();
        }
    }
}
