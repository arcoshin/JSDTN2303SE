package homework.day00;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTest {
    public static void main(String[] args) {
        try (
                ZipOutputStream z = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("src/demo/HomeworkChecker.zip")));
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/java/homework/day00/HomeworkChecker.java")))
        ) {
            /**
             * innerName
             */

            //建立一檔案:自定義新檔名
            z.putNextEntry(new ZipEntry("HomeworkChecker99.java"));

            //寫入
            String line;
            while ((line = br.readLine())!=null) {
                z.write(line.getBytes());
            }

            //再建立一檔案
            z.putNextEntry(new ZipEntry("HomeworkChecker98.java"));

            //寫入
            while ((line = br.readLine())!=null) {
                z.write(line.getBytes());
            }

            System.out.println("FINISH!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
