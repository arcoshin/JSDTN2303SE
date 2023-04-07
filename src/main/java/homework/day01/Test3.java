package homework.day01;

import java.io.File;
import java.util.Scanner;

class Test3 {
    public static void main(String[] args) {
        /**
         * Scanner:類名應遵循大駝峰命名法，否則無法調用java.util中的Scanner掃描器工具類
         */
//		scanner s = new scanner(System.in);
        Scanner s = new Scanner(System.in);

        /**
         * 沒毛病
         */
        System.out.println("请输入关键字:");

        /**
         * 首具代碼中已聲明Scanner對象名為s，故此處無正確實例對象可調用Scanner工具類中的實例方法nextLine()---->改回s
         * 方法名應遵循小駝峰命名法，否則此處無法正確調用Scanner類中的實例方法nextLine()
         */
//        String key = s.nextline();
        String key = s.nextLine();

        /**
         * 相對路徑中"."代表的是當前項目
         * "./"代表的是當前項目目錄下，一般為默認可省略
         * "./src/main/java/homework/day01"才是表示當前目錄
         */
//		  File dir = new File(".");//定位当前目录
        File dir = new File("src/main/java/homework/day01");

        /**
         * 沒毛病
         */
        if (dir.isDirectory()) {//判断是否为目录
            File files[] = dir.listFiles();//获取该目录下所有子项
            /**
             * 字符串類型中，獲取字符串string長度的方法為:string.length()
             * 數組中，獲取數組array長度的方法為:array.length
             *
             * 另外，數組下標由0開始，故運行中可訪問的最末元素下標為length-1或者<length
             * 此為運行中異常，非編譯錯誤，故能仍可啟動程序，但會回報異常
             * ArrayIndexOutOfBoundsException : 數組下標越界異常
             */
//			  for(int i=0;i<=files.length();i++) {
            for (int i = 0; i < files.length; i++) {

                /**
                 * String:類名應遵循大駝峰命名法，否則無法正確聲明String類型變量
                 */
//                string fileName = files[i].getName();//获取每一个子项的名字
                String fileName = files[i].getName();

                /**
                 * fileName本身為String類型，可以調用String提供的實例方法contains()，
                 * boolean contains(String str)用以返回該字符串調用對象中是否包含給定元素str的方法
                 *
                 * Java中嚴格區分大小寫，變量名應遵循小駝峰命名法
                 * 前行代碼已正確聲明接收對象為fileName，此處大小寫應一致，否則視filename為不同變量
                 * 又filename為此前未正確聲明之變量，故此處會發生編譯錯誤
                 *
                 * 輸出語句中或者傳參時，若使用字符串類型，則傳入的是字符串的字面量本身，而並非變量本身
                 */
//                if (filename.不知道什么方法) {//判断名字中是否包含key的内容
                if (fileName.contains(key)) {

                    /**
                     * 前段代碼已正確聲明接收對象為fileName，此處拼寫應一致，否則視f1leNeme為不同變量
                     * 又f1leNeme為此前未正確聲明之變量，故此處會發生編譯錯誤
                     */
//                    System.out.println(f1leNeme);//包含就输出这个名字
                    System.out.println(fileName);

                }//if end
            }//循環體外
        }//如果非目錄走此分支
    }//main end
}//class end
