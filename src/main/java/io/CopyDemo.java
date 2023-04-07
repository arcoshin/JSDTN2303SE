package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 利用FIS、FOS複製圖片
 */
public class CopyDemo {
    public static void main(String[] args) {
        try (
                FileInputStream fis = new FileInputStream("demo/1.jpg");
                FileOutputStream fos = new FileOutputStream("demo/2.jpg");
        ) {
            System.out.println("正在複製......");
            long start = System.currentTimeMillis();
            int data;
            while ((data = fis.read()) != -1){
                fos.write(data);
            }
            System.out.println("複製完成");
            long end = System.currentTimeMillis();

            System.out.println("共費時" + (end - start) +  "毫秒");//約49398毫秒，約49秒
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
