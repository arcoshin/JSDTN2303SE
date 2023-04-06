package file;

import java.io.File;

/**
 * java.io.File
 * File的每一個實例用於表示一個文件或目錄
 * 使用File可以:
 * 1.訪問文件或目錄的屬性
 * 2.創建/刪除文件或目錄
 * 3.訪問一個目錄中的所有子巷
 * <p>
 * 但是File不能訪問文件數據
 */
public class FileDemo {
    public static void main(String[] args) {
        /**
         * 創建File對象用來表示當前項目目錄下的demo.txt文件
         *
         * 當前文件是在IDEA中運行的
         * 因此IDEA寄售當前成程序的運行環境
         * 相對路徑就是根據不同的運行環境而定
         * "./"稱為"當前目錄"，而IDEA中當前目錄指的就是當前項目目錄(JSD2303SE)
         *
         */
        //File file = new File("C:/Users/User/IdeaProjects/JSD2303SE");//絕對路徑會影響跨平台使用
        File file = new File("./demo.txt");//相對路徑

        //獲取文件名
        String name = file.getName();
        System.out.println("文件名:" + name);

        //獲取文件長度(單位是字節，int會溢出)
        long length = file.length();
        System.out.println("文件長度:" + length + "(字節)");

        //是否可讀
        boolean canRead = file.canRead();
        System.out.println("是否可讀:" + canRead);

        //是否可寫
        boolean canWrite = file.canWrite();
        System.out.println("是否可寫:" + canWrite);

        //是否隱藏
        boolean isHidden = file.isHidden();
        System.out.println("是否隱藏" + isHidden);


    }
}
