package com.asiainfo.sync;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Koala
 * @description
 * @date 2020/1/8 0008
 */
public class FileProcessor implements Runnable{

    void write(){
        FileWriter fileWriter = null;
        FileReader fileReader=  null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            fileReader = new FileReader("jdbc.properties");
            fileWriter = new FileWriter("result.txt");
            br = new BufferedReader(fileReader);
            bw = new BufferedWriter(fileWriter);
            String line;
            while ((line = br.readLine())!=null){
                bw.write(line);
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if(fileReader!=null && fileWriter!=null){
                    fileWriter.close();
                    fileReader.close();
                }
                if(br!=null && bw!=null){
                    br.close();
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        write();
    }
}
