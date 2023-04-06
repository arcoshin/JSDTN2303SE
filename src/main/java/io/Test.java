package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 向文件寫入a-z
 */
public class Test {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("demo/test.test");
            for (int i = 97; i <= 122; i++) {
                fos.write(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
