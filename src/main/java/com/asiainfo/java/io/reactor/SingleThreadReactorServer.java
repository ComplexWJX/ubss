package com.asiainfo.java.io.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author rukawa
 */
public class SingleThreadReactorServer implements Runnable {

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public SingleThreadReactorServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8088));
            // 设置非阻塞
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            // 将通道注册到选择器
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new AcceptHandler(serverSocketChannel, selector));

            System.out.println("服务端已启动...");
        } catch (IOException e) {
            System.out.println("服务端启动失败");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            doSelect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doSelect() throws IOException {
        while (!Thread.interrupted()) {
            selector.select();
            // 事件集
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //selectionKeys.iterator()
            for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                dispatch(key);
                //处理完成后移除选择器
                selectionKeys.remove(key);
            }
        }
    }

    @SuppressWarnings("unused")
    private void dispatchAndHandle(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            new AcceptHandler(serverSocketChannel, selector).handleAccept();
        } else if (selectionKey.isConnectable()) {
            System.out.println("channel connected...");
        } else if (selectionKey.isReadable()) {
            // todo do read
            new IoHandler(serverSocketChannel.accept(), selector).handleRead();
        } else if (selectionKey.isWritable()) {
            // todo do write
            System.out.println("channel can write...");
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable handler = (Runnable) selectionKey.attachment();
        if (null != handler) {
            handler.run();
        }
    }

}
