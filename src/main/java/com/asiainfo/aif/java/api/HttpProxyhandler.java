package com.asiainfo.aif.java.api;

/**
 * @ClassName DefaultProxyhandler
 * @Description TODO
 * @Author QQ
 * @Date Create in 2021/8/8 16:28
 * @Version 1.0
 */
public class HttpProxyhandler implements ProxyHandler {
    @Override
    public void handle(String[] args) {
        System.out.println("http proxy");
    }
}
