package com.asiainfo.base;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Koala
 * @description
 * @date 2020/1/8 0008
 */
public class Regex {
    public static void main(String[] args) {
//        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z\\d-]{0,28}[a-zA-Z\\d]$");
        // TODO 带分组的正则如何匹配总长度？？
        Pattern p = Pattern.compile("^([a-z]{1,30})([a-z\\d]|(-*[a-z\\d]){0,30}){1,30}$");
        Matcher m = p.matcher("abc-1abc-1abc-1abc-1abc-1abc-11");
        System.out.println(m.find());
    }

    @Test
    public void test1(){
        Pattern p1 = Pattern.compile("^\\s.+$");
        Pattern p2 = Pattern.compile("^.+\\s$");
        Pattern p3 = Pattern.compile("^\\S.+\\S$");
//        Matcher m1 = p1.matcher(" 12345");
//        Matcher m2 = p2.matcher(" 12345 ");
        Matcher m3 = p3.matcher(" 12345 ");
        System.out.println(m3.find());
    }

    @Test
    public void test2(){
        AtomicInteger failTimes = new AtomicInteger(0);
        failTimes = new AtomicInteger(failTimes.incrementAndGet());
        System.out.println(failTimes);
    }
}
