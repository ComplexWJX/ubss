package com.asiainfo.jvm.classload;

import sun.misc.Launcher;

/**
 * @ClassName CustomClassLoader
 * @Description TODO
 * @Author QQ
 * @Date Create in 2020/7/5 14:48
 * @Version 1.0
 */
public class CustomClassLoader {
    public static void main(String[] args) {
        try {
            Launcher.getLauncher().getClassLoader().getResource("");
            Class<?> loadClass = Thread.currentThread().getContextClassLoader().loadClass("com.itany.jdbc.util.KeyHolder");
            Object instance = loadClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
