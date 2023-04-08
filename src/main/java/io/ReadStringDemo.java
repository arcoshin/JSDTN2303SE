package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ReadStringDemo {
    public static void main(String[] args) {
        File file = new File("demo/fos.test");

        try (
                FileInputStream fis = new FileInputStream(file);
        ) {
            byte[] data = new byte[(int) file.length()];//一次塊讀文件大小的長度
            fis.read(data);


            String line = new String(data,StandardCharsets.UTF_8);
            System.out.println(line);





        } catch (Exception e) {
        }
    }
}
