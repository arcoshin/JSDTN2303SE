package file;

import java.io.File;
import java.util.Arrays;

/**
 * File[] list()
 * 獲取一個目錄的所有子項
 */
public class ListFilesDemo1 {
    public static void main(String[] args) {
        File dir = new File(".");//當前項目目錄本身

        if (dir.isDirectory()) {
            File[] subs = dir.listFiles();
            System.out.println("一共有" + subs.length + "個子項");
            System.out.println("包含" + Arrays.toString(subs));

            for (File sub : subs) {
                System.out.println(sub.getName());
            }
        }


    }
}
