package homework.day02;

import java.io.*;

class Test03 {
    public static void main(String[] args) {
        /**
         * FileInputStream:文件輸入流
         * 須導包方可使用----->java.io.FileInputStream
         * FileOutputStream:文件輸出流
         * 須導包方可使用----->java.io.FileOutputStream
         *
         * BufferedInputStream:緩衝輸入流
         * 須導包方可使用----->java.io.BufferedInputStream
         * BufferedOutputStream:緩衝輸出流
         * 須導包方可使用----->java.io.BufferedOutputStream
         */

        try (//main方法一般不拋異常，此處亦可自動關流釋放資源
                FileInputStream fis = new FileInputStream("demo/test.txt");

                /**
                 * 此處BuffereddInputStream拼寫錯誤，故無法正確聲明BufferedInputStream類對象，因而後續報錯
                 */
//                BufferedInputStream bis = new BuffereddInputStream(fis);
                BufferedInputStream bis = new BufferedInputStream(fis);

                FileOutputStream fos = new FileOutputStream("demo/test_cp2.txt");

                /**
                 * 接收類型應與聲明類型有關聯(一致或向上造型)
                 * 此處BufferedInputStream類與BufferedOutputStream類並無相互繼承、實現關係，故編譯錯誤
                 */
//                BufferedInputStream bos = new BufferedOutputStream(fos);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int d = 0;
            /**
             * 當fis.read()方法讀至文件最末行時，會返回-1
             * 當bis.read()方法讀至文件最末行時，會返回-1
             * 因此在這個if分支中(bis.read() == -1)即是最末行，屬於邏輯錯誤
             * 又if只判斷一次即停止，此處應不斷讀取至文件最末行(返回-1)，應使用while循環
             */
//            if ((d = bis.read()) == -1) {
            while ((d = bis.read()) != -1) {
                /**
                 * 此處wirte為拼寫錯誤，故無法正確調用BufferedOutputStream中的實例方法write()
                 */
//                bos.wirte(d);
                bos.write(d);

                /**
                 * 緩出書出流BufferedOutputStream底層邏輯其實就是塊讀
                 * 然而不能保證每次讀寫的文件大小都是塊讀大小的整數倍
                 * 因此再最末次讀寫時，未滿塊讀容量的部分須要透過flush()方法釋放
                 * 一般而言，關流方法close()中會自帶一個flush()方法
                 *
                 * 若不使用flush仍可複製成功，但無法通過打樁驗證
                 */
                bos.flush();
            }
//            System.out.println("複製完畢!");

            /**
             * 打樁驗證
             */
            File file1 = new File("demo/test.txt");
            File file2 = new File("demo/test_cp2.txt");
            long len1 = file1.length();
            long len2 = file2.length();
            if (len1 == len2) {
                System.out.println("複製的檔案與原檔案大小一致------>複製成功");
            } else {
                System.out.println("複製成功，但複製的檔案與原檔案大小不一致------>需要稍加修正");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

