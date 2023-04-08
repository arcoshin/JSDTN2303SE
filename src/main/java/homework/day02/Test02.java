package homework.day02;

import java.io.*;

class Test02 {
    public static void main(String[] args) {
        try (//main方法一般不拋異常，此處亦可自動關流釋放資源
                /**
                 * FileInputStream:文件輸入流
                 * 須導包方可使用----->java.io.FileInputStream
                 * FileOutputStream:文件輸出流
                 * 須導包方可使用----->java.io.FileOutputStream
                 */
                FileInputStream fis = new FileInputStream("demo/test.txt");

                /**
                 * 文件輸出流 : FileOutputStream
                 * 且JAVA嚴格區分大小寫，故此處仍無法正確調用FileInputStream類
                 */
//        FileInputStream fos = new FIleInputStream("test_cp.txt");
                FileOutputStream fos = new FileOutputStream("demo/test_cp.txt");

        ) {//try?

            int d;
            while ((d = fis.read()) != -1) {
                /**
                 * fos.write()方法中又再度調用fis.read()方法
                 * 會造成讀取的字節先用以while判別boolean後，再調用後一個字節用以輸出
                 * 變成只會輸出偶數字節的錯誤拼寫方式，因此需要用d綁定，不可於同一方法內多次調用fis.read()
                 */
//            fos.write(fis.read());
                fos.write(d);
            }


            /**
             * 沒毛病
             */
//        System.out.println("复制完毕!");
//        fis.close();//try()自動關流
//        fos.close();

            /**
             * 打樁驗證
             */
            File file1 = new File("demo/test.txt");
            File file2 = new File("demo/test_cp.txt");
            long len1 = file1.length();
            long len2 = file2.length();
            if (len1 == len2) {
                System.out.println("複製的檔案與原檔案大小一致------>複製成功");
            } else {
                System.out.println("複製成功，但複製的檔案與原檔案大小不一致------>需要稍加修正");
            }





        } catch (Exception e) {//捕獲所有異常
            e.printStackTrace();//但不處理
        }
    }
}




