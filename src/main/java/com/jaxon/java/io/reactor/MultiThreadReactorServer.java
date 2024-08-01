package com.jaxon.java.io.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author rukawa
 */
public class MultiThreadReactorServer {

    Selector[] selectors = new Selector[2];

    SubReactor[] subReactors;

    ServerSocketChannel serverSocketChannel;

    public MultiThreadReactorServer() {

        try {
            // 用于监听连接事件
            selectors[0] = Selector.open();
            // 用于监听传输事件
            selectors[1] = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8088));
            // 服务端设置非阻塞
            serverSocketChannel.configureBlocking(false);

            // 将通道注册到选择器
            SelectionKey selectionKey = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
            selectionKey.attach(new AcceptHandler(serverSocketChannel, selectors[1]));

            // 第一个反应器，用于处理第一个选择器的连接处理
            SubReactor subReactor1 = new SubReactor(selectors[0]);
            SubReactor subReactor2 = new SubReactor(selectors[1]);
            subReactors = new SubReactor[]{subReactor1, subReactor2};

            System.out.println("服务端已启动...");
        } catch (IOException e) {
            System.out.println("服务端启动失败");
            e.printStackTrace();
        }
    }

    public void startServer() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }

    /**
     * 子反应器，用于分发事件
     */
    class SubReactor implements Runnable {
        Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            // 分发事件
            try {
                doSelect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void doSelect() throws IOException {
            // 内部类调用外部类的方式
            //MultiThreadReactorServer.this.startServer();
            while (!Thread.interrupted()) {
                selector.select();
                //事件集
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //selectionKeys.iterator()
                for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {
                    SelectionKey key = it.next();
                    dispatch(key);
                    //处理完成后移除选择器
                    selectionKeys.remove(key);
                }
            }
            System.out.println("Thread:" + Thread.currentThread().getName() + "is interrupted.");
//            for(;;) {
//                selector.select();
//                //事件集
//                Set<SelectionKey> selectionKeys = selector.selectedKeys();
//                //selectionKeys.iterator()
//                for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {
//                    SelectionKey key = it.next();
//                    dispatch(key);
//                    //处理完成后移除选择器
//                    selectionKeys.remove(key);
//                }
//            }
        }

        private void dispatch(SelectionKey selectionKey) {
            Runnable handler = (Runnable) selectionKey.attachment();
            if (null != handler) {
                handler.run();
            }
        }
    }
}
