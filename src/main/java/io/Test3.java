package io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 記事本工具
 */
class Test3 {
    public static void main(String[] args) {
        try (
                OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("demo/note.test", true)));
        ) {

            while (true) {
                /**
                 * 接收使用者輸入
                 */
                System.out.println("請輸入要寫入的內容");
                String line = new Scanner(System.in).nextLine();
                if ("EXIT".equals(line.trim().toUpperCase())) {
                    return;
                } else {
                    osw.write(line);
                    osw.write("\r\n");
                    System.out.println("寫入完畢");
                    osw.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
