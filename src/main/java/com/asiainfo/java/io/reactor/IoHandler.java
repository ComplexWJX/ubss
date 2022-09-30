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

    /**
     * 1.register前调用wakeup 目的就是向事件通道写入一个字节，将 doSelect 从阻塞中唤醒，
     *   从而继续向下运行释放 publicKeys 锁，给 register 方法运行的机会；
     * 2.因为处理客户端连接之前，IO事件轮询的selector(selectors[1])已经开始循环调用 select 产生阻塞，所以此处必须调用 wakeup 去唤醒
     * 3.单线程reactor不需要调用wakeup，因为是共用一个 selector ，客户端socket注册的时候，selector 并没有阻塞，在同一个线程中
     */
    public IoHandler(SocketChannel socketChannel, Selector selector) throws IOException {
        this.clientChannel = socketChannel;
        this.clientChannel.configureBlocking(false);
        // 客户端注册AcceptHandle连接时传过来的选择器，后面轮询事件保证是从同一个选择获取事件
        selector.wakeup();
        sk = this.clientChannel.register(selector, 0);
        // 单独设置感兴趣事件
        selector.wakeup();
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
