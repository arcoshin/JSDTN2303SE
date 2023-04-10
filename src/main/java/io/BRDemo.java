package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 緩衝字符讀取流
 * BufferedReaderStream
 */
public class BRDemo {
    public static void main(String[] args) {
        /**
         * 將當前代碼複製到控制台上
         */
        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("src/main/java/io/BRDemo.java"), StandardCharsets.UTF_8));
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("輸出完畢");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
