package com.jaxon.util;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * @author WJX
 * @title: JarClassLoader
 * @projectName ubss
 * @description: TODO
 * @date 2020/8/25 0025
 */
public class JarClassLoader extends URLClassLoader {

    public JarClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public JarClassLoader(URL[] urls) {
        super(urls);
    }

    public JarClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
}
