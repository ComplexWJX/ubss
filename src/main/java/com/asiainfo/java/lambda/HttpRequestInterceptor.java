package com.asiainfo.java.lambda;

/**
 * @Author rukawa
 * @Create 2022/9/28 0028 22:21
 * @Description todo
 * @Version V1.0
 */
public class HttpRequestInterceptor implements RequestInterceptor {
    @Override
    public void service() {
        System.out.println("http request.");
        // 调用RequestInterceptor的default方法
        RequestInterceptor.super.service();
    }
}
