package com.jaxon.jvm.gc;

/**
 * @ClassName FinalizeEscapeGC
 * @Description TODO finalize方法 进行二次标记
 * @Author QQ
 * @Date Create in 2020/5/17 22:38
 * @Version 1.0
 */
public class FinalizeEscapeGC {
    private static FinalizeEscapeGC SAVEHOOK = null;

    void isAlive() {
        System.out.println("i am still alive.");
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize execute.");
        SAVEHOOK = this;
    }

    public static void main(String[] args) {
        SAVEHOOK = new FinalizeEscapeGC();
        SAVEHOOK = null;
        System.gc();
        try {
            //finalize方法的优先级很低，需要等待
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (SAVEHOOK != null) {
            SAVEHOOK.isAlive();
        } else {
            System.out.println("i am died.");
        }

        SAVEHOOK = null;
        System.gc();
        //finalize方法的优先级很低，需要等待
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (SAVEHOOK != null) {
            SAVEHOOK.isAlive();
        } else {
            System.out.println("i am died.");
        }
    }
}
