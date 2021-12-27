package com.asiainfo.aif.java.nio.original;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

/**
 * @ClassName ThreadPerTaskWebServer
 * @Description TODO
 * @Author QQ
 * @Date Create in 2020/6/14 21:55
 * @Version 1.0
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws Exception {
        startServer();
    }


    private static void startServer() throws Exception {
        ServerSocket socket = new ServerSocket(8088);
        System.out.println("server started on port:" + socket.getLocalPort());
        while (true) {
            //ÿ����һ���ͻ��� ����һ���̸߳���
            final Socket connection = socket.accept();
            System.out.println("accept client:" + connection.getInetAddress().getHostAddress());
            InputStream input = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            new Thread(() -> {
                String line;
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println("revieve message:" + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
