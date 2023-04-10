package homework.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 遍歷io包上課代碼
 */
public class Test3 {
    public static void main(String[] args) {
        /**
         * 路徑名稱
         */
        String pathName = "src/main/java/io";

        /**
         * 綁定目標目錄
         */
        File path = new File(pathName);

        /**
         * 遍歷目標目錄下文件列表:lambda->.java結尾的文件(純展示)
         */
        File[] list = path.listFiles(f -> f.getName().endsWith(".java"));
        for (File fileName : list) {
//            System.out.println(fileName.getName());//打樁語句

            /**
             * 路徑名拼接遍歷得到的每個文件名，形成每個子元素的完整路徑傳入File構造器各自綁定為新對象
             */
            File file = new File(pathName + "/" + fileName.getName());

            /**
             * 搭建輸入流對接每個完整路徑的對象
             */
            try (
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(file), StandardCharsets.UTF_8)
                    )
            ) {

                /**
                 * 用以綁定每次被讀到的行內容的對象:line
                 */
                String line;

                /**
                 * 不斷讀取，若讀取內容非空走進分支
                 */
                while ((line = br.readLine()) != null) {//非空時

                    /**
                     * 非空時如果讀取到類名標題就彩色表示
                     */
                    if (line.contains("class")) {
                        System.out.println(String.format("\033[%d;%dm%s\033[0m", 44, 3, line));
                    } else {

                        /**
                         * 否則讀什麼就輸出什麼
                         */
                        System.out.println(line);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }//for循環結束

        System.out.println(String.format("\033[%d;%d;%dm%s\033[0m", 1, 97, 45, "代碼已展示完成"));//打樁

    }
}
