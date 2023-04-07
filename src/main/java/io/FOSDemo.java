package io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件輸出流
 * FileOutPutStream(String path)
 * FileOutPutStream(File file)
 */
public class FOSDemo {
    public static void main(String[] args) {
        //向當前目錄下的文件fos.dat中寫入數據
        /**
         * 使用file傳入也可，兩種方式擇一，此處使用後者較簡潔
         */
//        File file = new File("demo/fos.dat");
//        FileOutputStream fos = new FileOutputStream(file);

        try {
            FileOutputStream fos = new FileOutputStream("demo/fos.dat");

            /**
             * write 會將給定的int值對應的二進制的"低八位"寫出
             */
            fos.write(97);//fos.dat文件中若是點開看會自動使用ASCII編碼可視化，因此我們會看到a
            fos.write('a');//char類性'a'底層其實就是(char)97，因此可視化依然是看見a
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
             */
            fos.write(255);
            fos.write(256);
            fos.write(0);
            fos.write(-1);
        } catch (IOException e) {

        }



    }
}
