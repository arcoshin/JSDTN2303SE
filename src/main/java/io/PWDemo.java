package io;

import java.io.PrintWriter;

/**
 * 緩衝字符輸出流
 * PrintWriter
 */
public class PWDemo {
    public static void main(String[] args) {
        try (
                /**
                 * PrintWriter構造器底層的運作邏輯:還是接在字節流上，所以也是高級流
                 * public PrintWriter(String fileName) throws FileNotFoundException {
                 *    this(new BufferedWriter(
                 *          new OutputStreamWriter(
                 *              new FileOutputStream(fileName)
                 *              )
                 *          ),false
                 *    );
                 * }
                 */
                PrintWriter pw = new PrintWriter("demo/pw.txt", "UTF-8");
        ) {
            pw.println("我祈禱擁有一顆透明的心靈，和會流淚的眼睛。");
            pw.println("給我再去相信的勇氣，oh越過黃昏去擁抱你。");
            System.out.println("輸出完畢");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
