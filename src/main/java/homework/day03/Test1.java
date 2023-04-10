package homework.day03;

import java.io.*;
import java.util.Arrays;

/**
 * 獲取users中所有人的數據
 */
class Test1 {
    public static void main(String[] args) {
        /**
         * 綁定、定位到目標目錄
         */
        String path = "src/demo/users";
        File pathName = new File(path);

        /**
         * 獲取目標目錄中所有後綴為obj的檔案(過濾器:lambda->以.obj結尾的檔案名)
         */
        File[] lists = pathName.listFiles(f -> f.getName().endsWith(".obj"));
        /**
         * 利用遍歷+拼接定位每個列表中的檔案
         */
        for (File list : lists) {
            String fileName = path + "/" + list.getName();//遍歷拼接
            try (
                    /**
                     * 建立對象輸入流:
                     */
                    ObjectInputStream ois = new ObjectInputStream(
                            new FileInputStream(fileName)
                    )
            ) {

                /**
                 * 讀取目標目錄中所有的obj檔案
                 * 輸出o就會會直接調用o.toString
                 * toString是由Object類中所定義所以每個對象都有
                 * 又toString已被重寫過，故可直接調用
                 */
                Object o = ois.readObject();
                System.out.println(o);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}

