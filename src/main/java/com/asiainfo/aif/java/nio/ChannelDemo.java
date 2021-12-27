package com.asiainfo.aif.java.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName ChannelDemo
 * @Description TODO
 * @Author QQ
 * @Date Create in 2020/3/17 23:27
 * @Version 1.0
 * java 新IO 特性 通道（Channel）和缓冲器（ByteBuffer）
 * 学习netty的基础
 */
public class ChannelDemo {
    private final static int BSIZE = 1024;

    public static void main(String[] args) {
//         bufferRead();
        bufferWrite();
    }

    private static void bufferRead() {
        try {
            FileChannel channel = new FileInputStream("1.txt").getChannel();
            ByteBuffer bf = ByteBuffer.allocate(BSIZE);
            channel.read(bf);
            bf.flip();
            System.out.println(bf.capacity()); //缓冲区的容量
            System.out.println((char) bf.get(0)); //读取指定位置
            while (bf.hasRemaining()) {
                System.out.println((char) bf.get());
            }
            while (channel.read(bf) != -1) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bufferWrite() {
        try {
            FileChannel inChannel = new FileInputStream("1.txt").getChannel();
            ByteBuffer bf = ByteBuffer.allocate(BSIZE);
            FileOutputStream out = new FileOutputStream("output.jdbc");
            while (inChannel.read(bf) != -1) {
                bf.flip();
                FileChannel outChannel = out.getChannel();
                outChannel.write(bf);
                bf.clear();
                outChannel.close();
            }
            inChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
