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
            for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                dispatch(key);
                //处理完成后移除选择器
                selectionKeys.remove(key);
            }
        }
    }

    private void dispatch(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            handleAccept();
        } else if (selectionKey.isConnectable()) {
            System.out.println("channel connected...");
        } else if (selectionKey.isReadable()) {
            // todo do read
            handleRead(selectionKey);
        } else if (selectionKey.isWritable()) {
            // todo do write
            System.out.println("channel can write...");
        }
    }

    private void dispatch1(SelectionKey selectionKey) {
        Runnable handler = (Runnable) selectionKey.attachment();
        handler.run();
    }

    private void handleAccept() throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        // 客户端注册读事件，会后续收消息做准备
        socketChannel.register(selector, SelectionKey.OP_READ);
        String str = "receive client from " + socketChannel.getRemoteAddress() + "...";
        System.out.println(str);
        socketChannel.write(ByteBuffer.wrap("hello, client,i am server.".getBytes(StandardCharsets.UTF_8)));
    }

    private void handleRead(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        if (byteBuffer.hasRemaining()) {
            try {
                socketChannel.read(byteBuffer);
                System.out.println("client sent message: { " + new String(byteBuffer.array()) + " } from client");
            } catch (IOException e) {
                // 剔除关闭socket客户端
                try {
                    System.out.println(socketChannel.getRemoteAddress() + "下线");
                    socketChannel.close();
                } catch (IOException ioe) {
                    //ioe.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

}
