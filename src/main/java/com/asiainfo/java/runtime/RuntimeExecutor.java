package com.asiainfo.java.runtime;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author Koala
 * @description
 * @date 2020/3/17 0017
 */
public class RuntimeExecutor {

    public static void main(String[] args) {
        getAvailableProcessors();
    }

    private static void getAvailableProcessors() {
        System.out.printf("availableProcessors is %d", Runtime.getRuntime().availableProcessors());
    }

    private static void execCmd() {
        try {
            String command = "cmd /c ipconfig /all";
            Process exec = Runtime.getRuntime().exec(command);
            InputStreamReader in = new InputStreamReader(exec.getInputStream(), Charset.forName("GBK"));
            String line;
            BufferedReader br = new BufferedReader(in);
            while(null != (line = br.readLine())) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
