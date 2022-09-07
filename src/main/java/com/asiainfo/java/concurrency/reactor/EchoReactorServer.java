package com.asiainfo.java.concurrency.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoReactorServer implements Runnable {
    Selector selector;
    public EchoReactorServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8088));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);

        selector = Selector.open();
        // 将通道注册到选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.accept();
    }

    @Override
    public void run() {
        try {
            channelFire();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void channelFire() throws IOException {
        while (selector.select() > 0) {
            // 事件集
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //selectionKeys.iterator()
            for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext();) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    System.out.println("new client established...");
                } else if (key.isConnectable()) {
                    System.out.println("channel connected...");
                } else if (key.isReadable()) {
                    // todo do read
                    System.out.println("channel can read...");
                } else if (key.isWritable()) {
                    // todo do write
                    System.out.println("channel can write...");
                }
                //处理完成后移除选择器
                selectionKeys.remove(key);
            }
        }
    }
}
