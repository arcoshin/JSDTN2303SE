package io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 緩衝字符輸出流
 * PrintWriter
 */
public class PWDemo2 {
    public static void main(String[] args) {
        try (
                PrintWriter pw = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream("demo/pw2.txt",true),StandardCharsets.UTF_8)));
        ) {
            pw.println("我祈禱擁有一顆透明的心靈，和會流淚的眼睛。");
            pw.println("給我再去相信的勇氣，oh越過黃昏去擁抱你。");
            System.out.println("輸出完畢");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
