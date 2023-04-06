package file;

import java.io.File;
import java.io.IOException;

/**
 * 定義一個過濾器，獲取當前目錄下子項中明字還有a的所有子項
 */
public class Test3 {
    public static void main(String[] args) {
        File file = new File(".");

        File[] subs = file.listFiles(f -> f.getName().contains("a"));
        System.out.println("一共有" + subs.length + "個結果");
        for (File sub : subs) {
            System.out.println(sub);
        }
    }
}
