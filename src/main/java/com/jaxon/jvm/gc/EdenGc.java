package com.jaxon.jvm.gc;

import org.junit.Test;

/**
 * @ClassName EdenGc
 * @Description TODO
 * @Author QQ
 * @Date Create in 2020/5/31 18:15
 * @Version 1.0
 */
public class EdenGc {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        EdenGc _this = new EdenGc();
//        while (true) {
//            _this.testEdenGc();
//        }
        _this.testSlotRelease();
    }

    /**
     * jvm 参数 -Xmn10m -Xms20m -Xmx20m -XX:+PrintGCDetails
     */
    @Test
    public void testEdenGc() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; //新生代最大10MB,内存分配失败，出现一次Minor GC
//        System.gc();
    }

    @Test
    public void testPretenureSizeThreshold() {
        //设置pretenureSizeThreshold，直接将大对象分配到老年代
        byte[] allocation = new byte[4 * _1MB];
    }

    private void testSlotRelease() {
        {
            byte[] bytes = new byte[1024];
        }
        int a = 0;
        System.gc(); // 在bytes作用域外调用gc，slot才会释放
    }
}
