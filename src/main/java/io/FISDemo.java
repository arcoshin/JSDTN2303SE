package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件輸入流FileInputStream
 */
public class FISDemo {
    public static void main(String[] args) {
        try (
                FileInputStream fis = new FileInputStream("./demo/fos.dat");
        ) {

            /**
             * int 255 的二進制 00000000 00000000 00000000 11111111
             * for.write.寫入的數據                        ^^^^^^^^
             *
             * int 256 的二進制 00000000 00000000 00000001 00000000
             * for.write.寫入的數據                        ^^^^^^^^
             *
             * int 0 的二進制 00000000 00000000 00000000 00000000
             * for.write寫入的數據                       ^^^^^^^^
             *
             * int -1 的二進制 11111111 11111111 11111111 11111111
             * for.write.寫入的數據                       ^^^^^^^^
             *
             * fos.dat中的數據
             * 11111111 00000000 00000000 11111111
             */

            /**
             * fis讀取到的數據11111111 00000000 00000000 11111111
             * 自動補碼
             * 00000000 00000000 00000000 11111111 (原int 255)
             * 00000000 00000000 00000000 00000000 (原int 256)
             * 00000000 00000000 00000000 00000000 (原int  0)
             * 00000000 00000000 00000000 11111111 (原int -1)
             *
             * fis解析到的數據    只取末八位     自動補碼
             * (原int 255) ---> 11111111 ---> 00000000 00000000 00000000 11111111 ---> 還原255
             * (原int 256) ---> 00000000 ---> 00000000 00000000 00000000 00000000 ---> 還原0
             * (原int  0)  ---> 00000000 ---> 00000000 00000000 00000000 00000000 ---> 還原0
             * (原int -1)  ---> 11111111 ---> 00000000 00000000 00000000 11111111 ---> 還原255
             */

            /**
             * 所以原文件-1是不可能打印出來的，可以排除衝突問題
             */
            int read = fis.read();
            System.out.println(read);
            while (read != -1) {
                read = fis.read();
                System.out.println(read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
