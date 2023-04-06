package file;

import java.io.File;
import java.io.IOException;

/**
 * 在當前目錄下新建100個文件
 * 名字為test1.txt------test100.txt
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 100; i++) {
            String name = "test" + i + ".txt";
            File file = new File("./" + name);

            boolean success = file.createNewFile();
            if (success) {
                System.out.println(file.getName() + "創建成功......");
            } else {
                System.out.println(file.getName() + "創建失敗......");
            }

        }

    }
}
