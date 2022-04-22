package com.asiainfo.java.proxy;

import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName DefaultProxyhandler
 * @Description TODO
 * @Author QQ
 * @Date Create in 2021/8/8 16:28
 * @Version 1.0
 */
public class DefaultProxyHandler implements ProxyHandler {
    @Override
    @Async
    public void handle(String[] args) {
        System.out.println("default");
    }
}
