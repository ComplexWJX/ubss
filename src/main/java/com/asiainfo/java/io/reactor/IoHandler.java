package com.asiainfo.java.io.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * [一句话描述该类的功能]
 *
 * @author : [rukawa]
 * @version : [v1.0]
 * @createTime : [2022/9/13 0013 13:29]
 */
public class IoHandler implements Runnable {
    SocketChannel clientChannel;

    SelectionKey sk;

    public IoHandler(SocketChannel socketChannel, Selector selector) throws IOException {
        this.clientChannel = socketChannel;
        socketChannel.configureBlocking(false);
        selector.wakeup();
        // 客户端注册AcceptHandle连接时传过来的选择器，后面轮询事件保证是从同一个选择获取事件
        sk = socketChannel.register(selector, 0);
        // 单独设置感兴趣事件
        sk.interestOps(SelectionKey.OP_READ);
        sk.attach(this);
    }

    @Override
    public void run() {
        handleRead();
    }

    public void handleRead() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        if (byteBuffer.hasRemaining()) {
            try {
                clientChannel.read(byteBuffer);
                System.out.println("client sent message: { " + new String(byteBuffer.array()) + " } from client");
            } catch (IOException e) {
                // 客户端断开连接会触发read事件，剔除关闭socket客户端
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
