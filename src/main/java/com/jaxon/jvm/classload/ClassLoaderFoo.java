package com.jaxon.jvm.classload;

import com.jaxon.util.JarClassLoader;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @author WJX
 * @title: ClassLoaderFoo
 * @projectName ubss
 * @description: TODO
 * @date 2020/8/25 0025
 */
public class ClassLoaderFoo {

    public static void main(String[] args) {

        try {
            //方式一：
            String filepath = "lib/google/google-http-client.jar";
            File jarFile = new File(filepath);
            URL url = jarFile.toURI().toURL();

            //方式二：
//            String urlPath = "file:///E:/git_req/my_issue/ubss/lib/google/google-http-client.jar";
//            URL url = new URL(urlPath);

            JarClassLoader jarClassLoader = new JarClassLoader(new URL[]{url});
            Class<?> loadClass = jarClassLoader.loadClass("com.google.api.client.util.IOUtils");

            Object instance = loadClass.newInstance();
            Method method = loadClass.getMethod("copy", InputStream.class, OutputStream.class);
            InputStream in = ClassLoaderFoo.class.getClassLoader().getResourceAsStream("log4j.xml");
            OutputStream out = new FileOutputStream("log4j.copy.xml");
            method.invoke(instance,in,out);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
