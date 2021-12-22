package com.itany.utils;

import java.io.*;
import java.net.URL;

/**
 * @author Koala
 * @description util for common fileRead and write
 * @date 2020/1/31 0031
 */
public class FileUtil {
    static void fileLineCopy (String fName){
        FileReader reader;
        PrintWriter pw = null;
        BufferedReader br = null;

        //java获取classpath下资源
        ClassLoader cl = FileUtil.class.getClassLoader();
        URL url = cl.getResource(fName);
        File f = new File(url.getFile());
        try {
            if (f.exists()) {
                reader = new FileReader(f);
                br = new BufferedReader(reader);
                pw = new PrintWriter("e://"+f.getName());
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    pw.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取任意classpath路径下的文件
     * @param path
     */
    private static void readFile(String path) {
        Reader read = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        String line;
        String absolutePath = FileUtil.class.getResource("/").getPath();

        try {
            read = new FileReader(new File(absolutePath+path));
            br = new BufferedReader(read);
            while ((line = br.readLine())!= null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println(sb.toString());
                br.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void FileCopy(String srcFile,String desFile) {
        File f = new File(srcFile);
        InputStream in = null;
        OutputStream out = null;
        if (f.exists()) {
            try {
                in = new FileInputStream(f);
                out = new FileOutputStream(new File(desFile));
                byte[] b = new byte[1024];
                while (in.read(b) != -1) {
                    out.write(b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        fileLineCopy("jdbc.properties");
        readFile("jdbc.properties");
    }
}
