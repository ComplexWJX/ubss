package com.asiainfo.java.io.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Author rukawa
 * @Create 2022/9/12 0012 13:34
 * @Description todo
 * @Version V1.0
 */
public class EchoReactorClient {
    public static void main(String[] args) {
        establish();
    }

    private static void establish() {
        try {
            SocketChannel client = SocketChannel.open(new InetSocketAddress(8088));
            // 如下两句必须同时设置，通道为非阻塞才能向Selector注册
            client.configureBlocking(false);
            doSend(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doSend(SocketChannel client) throws IOException {
        if (client.isConnected()) {
            String str = new String("hello, server. i am connecting to you.".getBytes(StandardCharsets.UTF_8));
            client.write(ByteBuffer.wrap(str.getBytes()));
            // 监听输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String sendMsg = scanner.nextLine();
                client.write(StandardCharsets.UTF_8.encode(sendMsg));
            }
        }
    }
}
