package file;

import java.io.File;
import java.util.Arrays;

/**
 * File[] list()
 * 獲取一個目錄的所有子項
 */
public class ListFilesDemo2 {
    public static void main(String[] args) {
        /**
         * 獲取當前目錄中的所有文本文件
         */
        File file = new File(".");
        File[] subs = file.listFiles(fileName -> fileName.getName().contains("g"));//過濾器lambda
        System.out.println("符合結果一共有" + subs.length + "個");

        for (File sub : subs) {
            System.out.println(sub.getName());
        }




    }
}
