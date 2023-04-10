package io;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 轉換字符輸入流
 * InputStreamReader
 */
public class ISRDemo {
    public static void main(String[] args) {
        try (
                InputStreamReader isr = new InputStreamReader(
                        new FileInputStream("demo/osw.obj"), StandardCharsets.UTF_8);
        ) {
            int d;
            while ((d = isr.read()) != -1) {
                System.out.print((char) d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
