package com.jaxon.jvm;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class CasFoo {
    public static void main(String[] args) {
        //openjdk.jol工具，查看属性偏移量，java的内存布局情况
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(ConfigManager.class).toPrintable());
    }
}
