package com.asiainfo.java.io.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author rukawa
 * @Create 2022/9/12 0012 13:34
 * @Description todo
 * @Version V1.0
 */
public class EchoReactorClient {
    private static Selector selector;
    public static void main(String[] args) {
        establish();
    }

    private static void establish() {
        try {
            selector = Selector.open();
            SocketChannel client = SocketChannel.open(new InetSocketAddress(8088));
            // 如下两句必须同时设置，通道为非阻塞才能向Selector注册
            client.configureBlocking(false);
            SelectionKey sk = client.register(selector, SelectionKey.OP_READ);
            doSend(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doSend(SocketChannel client) throws IOException {
        if (client.isConnected()) {
            String str = new String("hello, server. i am connecting to you.".getBytes(StandardCharsets.UTF_8));
            // todo put为什么写不了？
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            buffer.put(str.getBytes());
//            client.write(buffer);
            client.write(ByteBuffer.wrap(str.getBytes()));

            // 监听输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String sendMsg = scanner.nextLine();
                //buffer.put(sendMsg.getBytes());
                client.write(StandardCharsets.UTF_8.encode(sendMsg));
            }
        }
    }

    private void doRead(SelectionKey sk) throws IOException {
        while (selector.select() > 0) {
            selector.select();
            //事件集
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //selectionKeys.iterator()
            for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                // todo
                //处理完成后移除选择器
                selectionKeys.remove(key);
            }
        }
    }
}
