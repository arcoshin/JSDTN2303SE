package io;

import java.io.*;

/**
 * 利用FIS、FOS複製圖片 : 提高每次讀寫的數據量可以減少讀寫次數，提高讀寫效率
 */
public class CopyDemo3 {
    public static void main(String[] args) {
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("demo/1.jpg"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("demo/5.jpg"));
        ) {

            System.out.println("正在複製......");
            long start = System.currentTimeMillis();
            byte[] data = new byte[1024 * 10];//塊讀10K
            /**
             * int read() 返回的是讀取內容
             * int read(byte[] data) 返回的是讀取的長度(字節數)
             */
            int len;
            while ((len = bis.read(data)) != -1) {
//                bos.write(data, 0, len);//塊寫(讀多長寫多長)
                bos.write(data);//底層其實就是塊讀[BufferedInputStream:size 8192....8K]
            }
            System.out.println("複製完成");
            long end = System.currentTimeMillis();

            System.out.println("共費時" + (end - start) + "毫秒");//約12毫秒
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
