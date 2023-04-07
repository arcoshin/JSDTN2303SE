package homework.day01;

import java.io.File;
import java.io.FileFilter;

class Test2 {
    public static void main(String[] args) {
        File file = new File("src/main/java/homework/day01/demo");//綁定文件地址
        /**
         * 匿名類部類 : new接口(){匿名內部類的類體} ------創建一個繼承接口的匿名子類，{}為其類體
         */
        File[] subs1 = file.listFiles(new FileFilter() {//以File數組接收過濾結果
            @Override
            public boolean accept(File pathname) {//重寫的方法:pathname代表當前的過濾對象名稱
                /**
                 * return定義過濾條件
                 * pathname當前過濾對象.getName取檔名.contains("s")是否包含s
                 */
                return pathname.getName().contains("s");
            }
        });
        System.out.println("匿名內部類的過濾結果:共有" + subs1.length + "筆");//美化拼接
        for (File sub : subs1) {//增強型for循環遍歷數組
            System.out.println(sub.getName() + " ");
        }

        /**
         * Lambda表達式 : (參數列表) -> {重寫的方法體}
         * 函數式接口名稱已被當前方法的形參限定，故可省略
         * 函數式接口只有一個需要重寫的抽象方法:方法名可省略
         * 參數類型已被方法所規定，而需重寫的方法只有一個，因而也可省略
         * 參數若只有一個，省略()
         * 方法體若只有一句，省略{}，若無{}則return即無意義故也可省略
         */
        File[] subs2 = file.listFiles(f -> f.getName().contains("s"));
        System.out.println("Lambda表達式的過濾結果:共有" + subs2.length + "筆");
        for (File sub : subs2) {//增強型for循環遍歷數組
            System.out.println(sub.getName() + " ");
        }
    }
}
