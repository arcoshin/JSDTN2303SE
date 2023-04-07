package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteStringDemo {
    public static void main(String[] args) {
        try (
                FileOutputStream fos = new FileOutputStream("demo/fos.test",true);//append:追加模式
        ) {

            String line = "測試測試測試";
            byte[] data = line.getBytes();

            fos.write(data);

            fos.write("連續寫測試測試測試".getBytes(StandardCharsets.UTF_8));
            fos.write("abc".getBytes(StandardCharsets.UTF_8));

            System.out.println("FINISH!");
        } catch (IOException e) {

        }
    }
}
