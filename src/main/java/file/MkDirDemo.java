package file;

import java.io.File;

/**
 * 創建一個目錄
 */
public class MkDirDemo {
    public static void main(String[] args) {
        /**
         * 在當前項目目錄下，新建一個名為demo的目錄
         */
        File dir = new File("demo");

        /**
         * 確認File所綁定的對象(文件、目錄)是否已存在
         */
        if (dir.exists()) {
            System.out.println(dir.getName() + "該目錄已存在");
        } else {//排除已存在的可能後走此分支
            boolean mkdir = dir.mkdir();
            if (mkdir) {
                System.out.println(dir.getName() + "目錄創建成功");
            } else {
                System.out.println(dir.getName() + "目錄創建失敗");
            }
        }
    }
}
