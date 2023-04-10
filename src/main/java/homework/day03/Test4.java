package homework.day03;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 簡易記事本工具
 */
public class Test4 {
    public static void main(String[] args) {
        /**
         * 確認生成記事本的目標地址
         */
        String path = "src/demo/pw.txt";

        /**
         * 綁定文件對象
         */
        File file = new File(path);

        /**
         * 建立輸出流:pw其實已經內建一整套流，此處純展示
         * 直接建立在try塊之前的小括號:autoClose
         */
        try (
                PrintWriter pw = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(//既是記事本，當然開啟追加模式
                                                file,true), StandardCharsets.UTF_8
                                )
                        )
                ,true);//自動將緩衝區內的數據沖出，達到即時寫入的視覺效果
        ) {
            /**
             * 給予提示輸入訊息
             * 創建一對象接收使用者輸入的數據
             */
            System.out.println("請輸入要寫入的數據，輸入exit退出程序");

            /**
             * 不斷循環接收數據
             */
            while (true) {
                String line = new Scanner(System.in).nextLine();

                /**
                 * 判定數據--->line無視大小寫時是否包含退出的指令:line
                 */
                if (line.equalsIgnoreCase("EXIT")){
                    System.err.println("正在關閉程序......");
                    /**
                     * 如果包含退出指令就退出程序
                     */
                    return;
                }

                /**
                 * 通過分支則代表是要輸入的數據，寫入
                 * println():自動換行並且追加
                 */
                pw.println(line);
                System.err.println("......ok");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
