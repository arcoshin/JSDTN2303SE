package file;

import java.io.File;
import java.io.IOException;

/**
 * 刪除在當前目錄下的100個文件
 * 名字為test1.txt------test100.txt
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 100; i++) {
            String name = "test" + i + ".txt";
            File file = new File("./" + name);

            boolean success = file.delete();
            if (success) {
                System.out.println(file.getName() + "刪除成功......");
            } else {
                System.out.println(file.getName() + "刪除失敗......");
            }

        }

    }
}
