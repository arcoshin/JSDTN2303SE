package file;

import java.io.File;

/**
 * 創建一個目錄
 */
public class MkDirsDemo {
    public static void main(String[] args) {
        /**
         * 在當前項目目錄下，新建一系列a/b/c/d/e/f目錄
         */
        File dir = new File("demo/a/b/c/d/e/f");
        if (dir.exists()) {
            System.out.println(dir.getName() + "目錄已存在");
        }else {
            /**
             * mkdir方法在創建目錄時要求"該目錄所在的父目錄必須真實存在"，否則創建失敗
             */
            boolean mkdir = dir.mkdirs();
            if (mkdir) {
                System.out.println(dir.getName() + "目錄已成功創建");
            }

            /**
             * mkdirs則沒有上述限制，會連同不存在的父目錄一同創建出來，實際開發中推薦使用此方法
             */
            boolean mkdirs = dir.mkdirs();
            if (mkdirs) {
                System.out.println(dir.getName() + "目錄已成功創建");
            }
        }

    }
}
