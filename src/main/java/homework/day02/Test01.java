package homework.day02;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Test01 {
    public static void main(String[] args) throws IOException {
        /**
         * 獲取當前目錄所有文件清單
         */
        File path = new File("src/main/java/homework/day02");
        File[] list = path.listFiles();
        System.out.println("目標位置共有:" + list.length + "個檔案:");
        for (int i = 0; i < list.length; i++) {
//            System.out.println(list[i].getName());//打樁

            /**
             * 接收原檔名
             */
            String oldName = list[i].getName();
            System.out.println("oldName:" + oldName);

            /**
             * 以"."為切割點，將檔名(coreName)與後綴(suffix)分開
             */
            String[] frags = oldName.split("\\.");
//            System.out.println(frags[0]+ "+" + frags[1]);//打樁
            String coreName = frags[0];//接收純檔名
            String suffix = frags[1];//接受副檔名
            String copySign = "_cp";

            /**
             * 排除連續生成
             */
            for (int j = 0; j < frags.length; j++) {
                if (frags[j].contains(copySign)) {//拼接前就已包含_cp後綴則已重複

                    try {//報錯但確保運行
                        throw new RuntimeException("不可連續創建");
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }

                    /**
                     * 報錯後選單
                     */
                    System.out.println("是否需要協助刪除已創建的檔案?選擇後程序執行完成即關閉程序");
                    System.out.println("1.是的，請幫我刪除，並關閉程序.............");
                    System.out.println("2.不用，請幫我關閉程序，我會手動刪除........");
                    int chosen = 0;

                    /**
                     * 接收使用者訊息
                     */
                    while (true) {
                        try {
                            chosen = new Scanner(System.in).nextInt();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        /**
                         * 判定使用者訊息
                         */
                        switch (chosen) {
                            case 1:
                                for (int k = 0; k < list.length; k++) {
                                    if (list[k].getName().contains("_cp")) {
                                        list[k].delete();
                                    }
                                }
                                return;

                            case 2:
                                return;

                            default:
                                try {
                                    throw new RuntimeException("輸入錯誤，請重新輸入");
                                } catch (RuntimeException e) {
                                    e.printStackTrace();
                                }
                        }
                    }
                }
            }


            /**
             * 依照要求拼接新檔名:透過打樁語句可得知"."作為切點已被切出，應補上
             */
            String newName = coreName + copySign + "." + suffix;
            System.out.println("newName:" + newName);//打樁

            /**
             * 依照新檔名創建新文件
             */
            File file = new File(path + "/" + newName);//文件活綁定

            boolean newFile = file.createNewFile();

            if (newFile) {
                System.out.println("創建完成!");
            }


        }


    }
}


