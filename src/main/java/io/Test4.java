package io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 記事本工具
 */
class Test4 {
    public static void main(String[] args) {
        try (
                PrintWriter pw = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(
                                                "demo/pw2.test",
                                                true), StandardCharsets.UTF_8))
                        ,true);
        ) {
            pw.println("我祈禱擁有一顆透明的心靈，和會流淚的眼睛。");
            pw.println("給我再去相信的勇氣，oh越過黃昏去擁抱你。");
            System.out.println("輸出完畢");

            while (true) {
                /**
                 * 接收使用者輸入
                 */
                System.out.println("請輸入要寫入的內容，輸入EXIT(不分大小寫)則退出");
                String line = new Scanner(System.in).nextLine();
//                if ("EXIT".equals(line.trim().toUpperCase())) {
                if ("EXIT".equalsIgnoreCase(line.trim())) {//更優雅的寫法，無視大小寫
                    System.out.println("程序正在結束......");
                    return;
                } else {
                    pw.println(line);
                    pw.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
