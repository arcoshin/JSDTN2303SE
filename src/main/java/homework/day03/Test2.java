package homework.day03;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Test2 {
    /**
     * 程序代碼整段應放入方法內，否則會出現多處無法成功調用對象的錯誤--->這裡直接放main方法
     */
    public static void main(String[] args) {

        /**
         * 將所有流放入try塊前的小括號內
         */
        try (
                /**
                 * 可以放於此塊的代碼對象，必須實現AutoCloseable接口
                 * OutputStream、InputStream、Writer、Reader這些流的超類都實現了Closeable接口
                 * 而Closeable接口又繼承了AutoCloseable接口
                 * 因此可知道所有的流一定都符合這個規定!
                 */


                /**
                 * 稍微修改一下路徑與檔名(小駝峰)
                 * FileOutputStream(file,boolean append)追加模式默認false
                 */
                FileOutputStream fos = new FileOutputStream("src/demo/testPW.test",true);

                /**
                 * PrintWriter()有兩個構造器
                 * 1.PrintWriter(file,"UTF-8")
                 * 2.PrintWriter(io,boolean autoFlash)
                 * 只有1的情形才會使用"UTF-8"形式來表達"csn"
                 *
                 * 其餘的流通常都是於構造器中生聲明編碼格式為StandardCharsets.UTF_8
                 */
//            OutputStreamWriter osw = new OutputStreamWriter(fos, "UFT-8");
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);


                /**
                 * 沒毛病
                 */
                BufferedWriter bw = new BufferedWriter(osw);


                /**
                 * PrintWriter(io,boolean autoFlash)
                 * pw連續書寫時應於構造器中開始自動刷新功能
                 */
//            PrintWriter pw = new PrintWriter(bw);
                PrintWriter pw = new PrintWriter(bw, true);

        ) {


            /**
             * 說實話，沒毛病
             */
            pw.println("你好!我喜歡JAVA!");


            /**
             * 輸出語句的工具方法是System類中的方法
             * 類名應遵循大駝峰命名法，否則無法成功調用System.out.println()方法
             */
//            system.out.println("写出完毕!");
            System.out.println("寫出完成!");


        } catch (Exception e) {

            /**
             * 輸出捕獲的異常，打樁語句
             */
            e.printStackTrace();

            /**
             * 捕獲異常時走此分支
             */
            System.out.println("出錯了!");
        }
    }


}
