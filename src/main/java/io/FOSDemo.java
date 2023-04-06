package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
            fos.write("中文字\n".getBytes());
            fos.write("哈哈哈".getBytes());
            System.out.println("寫入完畢");

        } catch (IOException e) {

        }


    }
}
