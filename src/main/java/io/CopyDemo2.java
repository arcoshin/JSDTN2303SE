package io;

import java.io.*;

/**
 * 利用FIS、FOS複製圖片 : 提高每次讀寫的數據量可以減少讀寫次數，提高讀寫效率
 */
public class CopyDemo2 {
    public static void main(String[] args) {
        try (
                FileInputStream fis = new FileInputStream("demo/1.jpg");
                FileOutputStream fos = new FileOutputStream("demo/4.jpg");
        ) {
            System.out.println("正在複製......");
            long start = System.currentTimeMillis();
            byte[] data = new byte[1024*10];//塊讀10K
            /**
             * int read() 返回的是讀取內容
             * int read(byte[] data) 返回的是讀取的長度(字節數)
             */
            int len;
            while ((len = fis.read(data)) != -1){
                fos.write(data,0,len);//塊寫(讀多長寫多長)
            }
            System.out.println("複製完成");
            long end = System.currentTimeMillis();

            System.out.println("共費時" + (end - start) +  "毫秒");//約14毫秒
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
