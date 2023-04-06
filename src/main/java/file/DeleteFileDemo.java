package file;

import java.io.File;

/**
 * 刪除一個文件
 */
public class DeleteFileDemo {
    public static void main(String[] args) {
        /**
         * 將當前目錄下的test1.txt-test100.txt刪除
         */
        //File file = new File("./test.txt");//相對路徑中"./"是可以忽略不寫的，默認就是"./"開始
        File file = new File("test.txt");

        boolean delete = file.delete();
        System.out.println(file.getName() + "是否成功刪除:" + delete);

    }
}
