package com.jaxon.java.io.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Author rukawa
 * @Create 2022/9/12 0012 16:07
 * @Description 连接处理器
 * @Version V1.0
 */
public class AcceptHandler implements Runnable {
    ServerSocketChannel serverSocketChannel;
    Selector selector;

    public AcceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            handleAccept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleAccept() throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        if (socketChannel != null) {
            new IoHandler(socketChannel, selector);
            socketChannel.configureBlocking(false);
            // 客户端注册读事件，会后续收消息做准备
            String str = "receive client from " + socketChannel.getRemoteAddress() + "...";
            System.out.println(str);
            socketChannel.write(ByteBuffer.wrap("hello, client,i am server.".getBytes(StandardCharsets.UTF_8)));
        }

    }
}
