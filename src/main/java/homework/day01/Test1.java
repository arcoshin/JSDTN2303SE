package homework.day01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Test1 {
    public static void main(String[] args) {
        /**
         * 1.接收使用者輸入的題示:
         * 1-1提示使用者輸入
         * 1-2聲明一個Scanner對象接收使用者輸入的數據
         */
        System.out.println("請輸入要創建的文件名，若要退出則輸入EXIT(不分大小寫)");//1-1
        String user;//先聲明後使用，用以停滯死循環

        /**
         * 2.針對輸入數據進行判定:
         *  2-1分析輸入的數據:
         *      2-1-1接收到Exit就退出程序
         *      2-1-2沒有後綴默認補上txt
         *  2-2綁定File地址比對文件名
         *      2-2-1遍歷結果為重複時，則提示用戶更改用戶名
         *      2-2-2遍歷結果非重複時，則實際生成該文件
         */
        while (true) {//不斷重複，直到特定條件才解除循環
            try {
                user = new Scanner(System.in).nextLine();//1-2，放在這才可以滯留死循環的連續執行
            } catch (Exception e) {//保證程序得以運行
                throw new RuntimeException("輸入錯誤，請重新輸入");
            }

            /**
             * 預處理用戶輸入的資訊
             */
            if ("EXIT".equals(user.trim().toUpperCase())) {//不分大小寫出現exit即退出循環
                return;//應用戶要求退出程序
            }

            if (user.indexOf(".") == -1) {//文件名無後綴時，則自動補txt
                user = user + ".txt";//無後綴默認拼接.txt，要記得以原變量接收(作用域)
            }

            /**
             * 比對有無重複，若無則生成，若重複則要求用戶更改文件名
             */
            File file = new File("src/main/java/homework/day01/demo/" + user);//綁定要生成的對象
            if (file.exists()) {//如果發現文件已存在
                System.out.println("警告:文件名已重複，請重新輸入");
            } else {//如果發現文件不存在
                boolean result = false;//作用域調整
                try {//main一般不拋異常
                    result = file.createNewFile();//創建新文件並接收返回值
                } catch (IOException e) {}//捕獲異常後不處理即可
                if (result) {//創建成功走此分支
                    System.out.println(file.getName() + "創建成功");
                    System.out.println("程序即將關閉");
                    try {
                        Thread.sleep(1000);//模擬系統關閉中
                    } catch (InterruptedException e) {}//被叫醒的異常捕獲，不處理即可
                    return;//創建成功退出程序
                }

            }

        }


    }
}
