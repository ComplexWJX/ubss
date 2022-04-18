package com.asiainfo.java.runtime;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author Koala
 * @description
 * @date 2020/3/17 0017
 */
public class RuntimeExcutor {
    private static Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        try {
            String command = "cmd /c ipconfig /all";
            Process exec = runtime.exec(command);
            InputStreamReader in = new InputStreamReader(exec.getInputStream(),Charset.forName("GBK"));
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
