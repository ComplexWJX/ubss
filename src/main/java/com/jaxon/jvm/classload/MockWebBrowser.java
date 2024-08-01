package com.jaxon.jvm.classload;

public class MockWebBrowser {
    private static String msg;

    public static void main(String[] args) {
//        new AbstractMessageQuene().reentrantLock.getHoldCount();
        new MockWebBrowser().call();
    }

    int call() {
        try {
//            msg.toString();
            return 10;
        } catch (Exception e) {
            System.out.println("catch exception");
        } finally {
            System.out.println("finally do");
        }
        return -1;
    }

}
