package com.jaxon.java.proxy;

import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WJX
 * @date 2022-04-22
 */
public class ProxyHandlerFactory {
    private final ConcurrentHashMap<Method, ProxyHandler> proxyMap = new ConcurrentHashMap<>();
    void init () {
        Class<?>[] interfaces = ProxyHandler.class.getInterfaces();
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
//        System.out.println(ProxyHandler.class.isAssignableFrom(AbstratProxyHandler.class));// true
//        System.out.println(AbstratProxyHandler.class.isAssignableFrom(ProxyHandler.class));// false
        List<Class<?>> classList = null;
        try {
            classList = getAllClassesByInterfaces(ProxyHandler.class);
            for (Class<?> aClass : classList) {
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (method.getAnnotation(Async.class) != null) {
                        method.invoke(aClass.newInstance(), new Object[]{new String[]{"hello boy"}});
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(classList);
    }

    public static List<Class<?>> getAllClassesByInterfaces(Class<?> clazz) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (clazz.isInterface()) {
            List<Class<?>> allClasses = getAllClasses(clazz.getPackage().getName());
            for (Class<?> aclass : allClasses) {
                if (clazz.isAssignableFrom(aclass)) {
                    // 除去自身
                    if (!aclass.equals(clazz)) {
                        classes.add(aclass);
                    }
                }
            }
        }
        return classes;
    }

    public static List<Class<?>> getAllClasses(String packageName) throws ClassNotFoundException {
        String packagePath = packageName.replace(".", "/");
        List<Class<?>> classes = new ArrayList<>();
        URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath);
        if (null != url) {
            List<String> classNames = getClassNameByFile(url.getPath());
            for (String className : classNames) {
                classes.add(Class.forName(className));
            }
            return classes;
        }
        return Collections.emptyList();
    }

    /**
     * 从项目文件获取某包下所有类
     * @param filePath 文件路径
     * @return 类的完整名称
     */
    private static List<String> getClassNameByFile(String filePath) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassNameByFile(childFile.getPath()));
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                    childFilePath = childFilePath.replace("\\", ".");
                    myClassName.add(childFilePath);
                }
            }
        }

        return myClassName;
    }
}
