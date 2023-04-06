package file;

import java.io.File;
import java.io.IOException;

/**
 * 使用File對象創建新文件
 */
public class CreateNewFileDemo {
    public static void main(String[] args) throws IOException {
        /**
         * 在當前目錄下新建一個名為test.txt的文件
         */
        File file = new File("./demo/test.txt");
        boolean success = file.createNewFile();
        if (success) {
            System.out.println(file.getName() + "文件創建成功");
        } else {
            System.out.println(file.getName() + "文件已存在");
        }


    }
}
