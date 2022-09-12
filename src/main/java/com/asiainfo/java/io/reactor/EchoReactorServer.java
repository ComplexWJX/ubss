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
public class EchoReactorServer implements Runnable {

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public EchoReactorServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8088));
            // 设置非阻塞
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            // 将通道注册到选择器
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new AcceptHandler(serverSocketChannel, selector));
            //selectionKey.attach(new ReadHandler(serverSocketChannel.accept(), selector));

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
        while (selector.select() > 0) {
            // 事件集
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //selectionKeys.iterator()
            for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext();) {
                SelectionKey key = it.next();
                dispatch(key);
                //处理完成后移除选择器
                selectionKeys.remove(key);
            }
        }
    }

    private void dispatch(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            AcceptHandler handler = (AcceptHandler) selectionKey.attachment();
            handler.handleAccept();
        } else if (selectionKey.isConnectable()) {
            System.out.println("channel connected...");
        } else if (selectionKey.isReadable()) {
            // todo do read
            ReadHandler handler = new ReadHandler((SocketChannel) selectionKey.channel(), selector);
            handler.run();
        } else if (selectionKey.isWritable()) {
            // todo do write
            System.out.println("channel can write...");
        }
    }


    private static class ReadHandler implements Runnable {
        SocketChannel clientChannel;

        SelectionKey sk;

        public ReadHandler(SocketChannel socketChannel, Selector selector) throws IOException {
            this.clientChannel = socketChannel;
            socketChannel.configureBlocking(false);
            sk = socketChannel.register(selector, 0);
            // 单独设置感兴趣事件
            sk.interestOps(SelectionKey.OP_READ);
            sk.attach(this);
        }

        @Override
        public void run() {
            handleRead();
        }

        private void handleRead() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            if (byteBuffer.hasRemaining()) {
                try {
                    clientChannel.read(byteBuffer);
                    System.out.println("client sent message: { " + new String(byteBuffer.array()) + " } from client");
                } catch (IOException e) {
                    // 剔除关闭socket客户端
                    try {
                        System.out.println(clientChannel.getRemoteAddress() + "下线");
                        clientChannel.close();
                    } catch (IOException ioe) {
                        //ioe.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }
    }
}
