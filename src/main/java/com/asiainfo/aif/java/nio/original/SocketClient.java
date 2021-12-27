package com.asiainfo.aif.java.nio.original;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName SocketClient
 * @Description TODO
 * @Author QQ
 * @Date Create in 2020/6/14 22:45
 * @Version 1.0
 */
public class SocketClient {
    private Socket client;

    public static void main(String[] args) {
        try {
            //����
            new SocketClient().connectServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectServer() throws Exception {
        client = new Socket("localhost", 8088);
        OutputStream outputStream = client.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStream);
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        // �ͻ���д
        while (!exit) {
            String content = input.nextLine();
            if ("bye".equals(content)) {
                exit = true;
            }
            pw.println(content);
            pw.flush();
        }
        client.shutdownOutput();
        pw.close();
        outputStream.close();
        client.close();
    }
}
