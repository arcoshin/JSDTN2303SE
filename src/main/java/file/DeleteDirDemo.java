package file;

import java.io.File;

/**
 * 刪除目錄
 */
public class DeleteDirDemo {
    public static void main(String[] args) {
        /**
         * 刪除當前項目目錄下的demo目錄
         */
        File file = new File("demo");
        if (file.exists()) {
            boolean delete = file.delete();
            if (delete) {
                System.out.println(file.getName() + "目錄已刪除");
            } else {
                System.out.println(file.getName() + "目錄刪除失敗");
            }
        } else {
            System.out.println(file.getName() + "目錄不存在");
        }
    }
}
