package homework.day02;

import java.io.*;
import java.util.Scanner;

/**
 * weekendHomework 2023.04.07
 * File與OOS相關練習作業類
 * HomeworkChecker.ver3.8
 * <p>
 * <p>
 * <p>
 * =====================
 * Update Information:
 * =====================
 * 1.Question Method->StaticInnerClass(Class.method():...)
 * 2.HomeworkChecker extends HomeworkCheckerTools(method():...)
 * 3.AutoEntering Array.length causing by a new Array System : Question[]
 * 4.Taking lots of programs into Methods making system looks clearer.
 * 5.New Class : database (design......)
 * 6.Semi-automatic press question class;(trying io)
 * =====================
 * Next Goal:
 * =====================
 * 1.Trying set another container to replace ArraySystem, it maybe...
 * (1.)static Question[]...(Now)
 * (2.)static Collection
 * (3.)static Map...(Goal)
 * 2.Thinking about using Reflection would make Systems better ?
 * 3.Whether if building a "KeyAdapter" system to replace some Scanners ?
 * <p>
 * <p>
 * 20230408_XCX
 */
class HomeworkChecker extends HomeworkCheckerTools {
    /**
     * 加載題目資料庫中的數據:不用每次進到系統就加載一次，所以放系統外
     */
    static Question[] questionList = new QuestionDatabase().downloadQuestion();

    /**
     * 作業檢測系統入口
     */
    static void homeworkChecker() throws InterruptedException {

        /**
         * 進入選單主頁
         */
        showMenuTip(questionList);//選單主頁的輸入提示
        showQuestionList(questionList, questionList.length);//遍歷所有題目


        /**
         * 聲明scanner對象接收檢測員輸入的選擇
         */
        int checker = 0;
        try {
            checker = new Scanner(System.in).nextInt();//接收檢測者的選擇
        } catch (Exception e) {//保證任何異常都能繼續執行

        }

        /**
         * 作業查找分支系統
         */
        whatUChooseFromMenu(checker, questionList);//將選擇派入選題分支

        /**
         * 防止多重跳轉間重複執行本方法，添加return讓執行至此時，一定結束本次方法。
         */
        return;
    }

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        try {
            homeworkChecker();//進入系統
            ending();//退出系統
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

/**
 * 題目模板類
 */
class Question {
    /**
     * 題目屬性
     */
    int questionNum;//題目序號
    String questionName;//題目名稱
    String questionContent;//題目要求內文

    /**
     * @param questionNum     題目序號
     * @param questionName    題目名稱
     * @param questionContent 題目要求內文
     */
    Question(int questionNum, String questionName, String questionContent) {
        this.questionNum = questionNum;
        this.questionName = questionName;
        this.questionContent = questionContent;
    }
}

/**
 * 本系統工具類
 */
class HomeworkCheckerTools {

    /**
     * 主選單分支業務
     *
     * @param checker      跳轉目標的代碼
     * @param questionList 跳轉目標的名稱
     */
    static void whatUChooseFromMenu(int checker, Question[] questionList) throws InterruptedException {
        if (checker == 99) {//選擇99則直接結束本方法
            return;
        } else if (checker > 0 && checker <= questionList.length) {//homeworkCheck != 99 but 10>=homeworkCheck>=max
            for (int i = 1; i <= questionList.length; i++) {//題數指針
                if (checker == i) {
                    whatYouChoose(questionList[i - 1].questionName);//數組中，下標(i-1)的內容 == 第i題的內容
                    goToQuestion(i);//去到第i題，但該業務結束後會自動回到本系統入口
                    return;
                }
            }
        } else {//others->Enter again!
            uRWrong();//錯了，重來
            HomeworkChecker.homeworkChecker();//回選單重選
            return;
        }
    }

    /**
     * 選題首頁的輸入提示方法
     */
    static void showMenuTip(Question[] questionList) {
        System.out.println("======================================================================");
        System.out.println("Hint : 請輸入要檢查的題數(如第一題請輸入1，本次作業共" + questionList.length + "題，若想結束檢查請輸入99)");
        System.out.println("======================================================================");
    }

    /**
     * 遍歷所有題目的方法
     *
     * @param max 本次開放題數
     */
    static void showQuestionList(Question[] questionList, int max) {
        for (int i = 0; i < HomeworkChecker.questionList.length; i++) {//遍歷題目列表
            System.out.println(questionList[i].questionNum + "." + questionList[i].questionName);
        }
    }

    /**
     * 執行question?的方法
     */
    static void goToQuestion(int num) throws InterruptedException {
        switch (num) {
            case 1:
                QuestionDatabase.question1();
                break;
            case 2:
                QuestionDatabase.question2();
                break;
            case 3:
                QuestionDatabase.question3();
                break;
            case 4:
                QuestionDatabase.question4();
                break;
            case 5:
                QuestionDatabase.question5();
                break;
            case 6:
                QuestionDatabase.question6();
                break;
            case 7:
                QuestionDatabase.question7();
                break;
            case 8:
                QuestionDatabase.question8();
                break;
            case 9:
                QuestionDatabase.question9();
                break;
            case 10:
                QuestionDatabase.question10();
                break;
        }
    }

    /**
     * 回到首頁的方法
     */
    static void backToMenu() throws InterruptedException {
        System.out.println("本題展示完成，即將返回大題選單......");
        Thread.sleep(1000);
        HomeworkChecker.homeworkChecker();
    }

    /**
     * 告知用戶輸入不合法的方法
     */
    static void uRWrong() {
        try {
            throw new RuntimeException("輸入錯誤，請重新輸入");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 預告即將為您展示代碼運行結果的方法
     */
    static void willShowURun() {
        System.out.println("以下為您展示運行結果......");
    }

    /**
     * 預告即將為您展示代碼運行結果的方法
     */
    static void willShowUCode() {
        System.out.println("以下為您展示代碼......");
    }

    /**
     * 廣播說明客戶所選擇的業務
     */
    static void whatYouChoose(String doSomething) {
        System.out.println("您所選擇的是" + doSomething);
    }

    /**
     * 開始演示question的方法
     */
    static void waitForPreparing() throws InterruptedException {
        System.out.println("請稍候...正在為您生成本題的演示過程...");
        Thread.sleep(1000);
    }

    /**
     * 結束的方法
     */
    static void ending() throws InterruptedException {
        System.out.println("感謝您的耐心檢查，系統正在關閉......");
        Thread.sleep(1000);
        System.out.println("ByeBye......");
        System.out.println("END");
    }

}

/**
 * 資料庫類(尚須手動更新)
 */
class QuestionDatabase extends HomeworkCheckerTools {
    /**
     * 將題目存入並生成題目列表(data)的方法:QB->HWC
     */
    Question[] downloadQuestion() {
        Question[] data = new Question[4];//<<---------------------------------手動修改
        data[0] = new Question(1, "Test1", "");
        data[1] = new Question(2, "Test2", "");
        data[2] = new Question(3, "Test3", "");
        data[3] = new Question(4, "Test4", "");
//        data[4] = new Question(5, "", "");
//        data[5] = new Question(6, "", "");
//        data[6] = new Question(7, "", "");
//        data[7] = new Question(8, "", "");
//        data[8] = new Question(9, "", "");
//        data[9] = new Question(10, "", "");
        return data;
    }

    /**
     * 第一題演示
     */
    static void question1() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();
        System.out.println(
                "class Test01 {\n" +
                        "    public static void main(String[] args) throws IOException {\n" +
                        "        /**\n" +
                        "         * 獲取當前目錄所有文件清單\n" +
                        "         */\n" +
                        "        File path = new File(\"src/main/java/homework/day02\");\n" +
                        "        File[] list = path.listFiles();\n" +
                        "        System.out.println(\"目標位置共有:\" + list.length + \"個檔案:\");\n" +
                        "        for (int i = 0; i < list.length; i++) {\n" +
                        "//            System.out.println(list[i].getName());//打樁\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 接收原檔名\n" +
                        "             */\n" +
                        "            String oldName = list[i].getName();\n" +
                        "            System.out.println(\"oldName:\" + oldName);\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 以\".\"為切割點，將檔名(coreName)與後綴(suffix)分開\n" +
                        "             */\n" +
                        "            String[] frags = oldName.split(\"\\\\.\");\n" +
                        "//            System.out.println(frags[0]+ \"+\" + frags[1]);//打樁\n" +
                        "            String coreName = frags[0];//接收純檔名\n" +
                        "            String suffix = frags[1];//接受副檔名\n" +
                        "            String copySign = \"_cp\";\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 排除連續生成\n" +
                        "             */\n" +
                        "            for (int j = 0; j < frags.length; j++) {\n" +
                        "                if (frags[j].contains(copySign)) {//拼接前就已包含_cp後綴則已重複\n" +
                        "\n" +
                        "                    try {//報錯但確保運行\n" +
                        "                        throw new RuntimeException(\"不可連續創建\");\n" +
                        "                    } catch (RuntimeException e) {\n" +
                        "                        e.printStackTrace();\n" +
                        "                    }\n" +
                        "\n" +
                        "                    /**\n" +
                        "                     * 報錯後選單\n" +
                        "                     */\n" +
                        "                    System.out.println(\"是否需要協助刪除已創建的檔案?選擇後程序執行完成即關閉程序\");\n" +
                        "                    System.out.println(\"1.是的，請幫我刪除，並關閉程序.............\");\n" +
                        "                    System.out.println(\"2.不用，請幫我關閉程序，我會手動刪除........\");\n" +
                        "                    int chosen = 0;\n" +
                        "\n" +
                        "                    /**\n" +
                        "                     * 接收使用者訊息\n" +
                        "                     */\n" +
                        "                    while (true) {\n" +
                        "                        try {\n" +
                        "                            chosen = new Scanner(System.in).nextInt();\n" +
                        "                        } catch (Exception e) {\n" +
                        "                            e.printStackTrace();\n" +
                        "                        }\n" +
                        "\n" +
                        "                        /**\n" +
                        "                         * 判定使用者訊息\n" +
                        "                         */\n" +
                        "                        switch (chosen) {\n" +
                        "                            case 1:\n" +
                        "                                for (int k = 0; k < list.length; k++) {\n" +
                        "                                    if (list[k].getName().contains(\"_cp\")) {\n" +
                        "                                        list[k].delete();\n" +
                        "                                    }\n" +
                        "                                }\n" +
                        "                                return;\n" +
                        "\n" +
                        "                            case 2:\n" +
                        "                                return;\n" +
                        "\n" +
                        "                            default:\n" +
                        "                                try {\n" +
                        "                                    throw new RuntimeException(\"輸入錯誤，請重新輸入\");\n" +
                        "                                } catch (RuntimeException e) {\n" +
                        "                                    e.printStackTrace();\n" +
                        "                                }\n" +
                        "                        }\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            }\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 依照要求拼接新檔名:透過打樁語句可得知\".\"作為切點已被切出，應補上\n" +
                        "             */\n" +
                        "            String newName = coreName + copySign + \".\" + suffix;\n" +
                        "            System.out.println(\"newName:\" + newName);//打樁\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 依照新檔名創建新文件\n" +
                        "             */\n" +
                        "            File file = new File(path + \"/\" + newName);//文件活綁定\n" +
                        "\n" +
                        "            boolean newFile = file.createNewFile();\n" +
                        "\n" +
                        "            if (newFile) {\n" +
                        "                System.out.println(\"創建完成!\");\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n"
        );

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        /**
         * 獲取當前目錄所有文件清單
         */
        File path = new File("src/main/java/homework/day02");
        File[] list = path.listFiles();
        System.out.println("目標位置共有:" + list.length + "個檔案:");
        for (int i = 0; i < list.length; i++) {

            /**
             * 接收原檔名
             */
            String oldName = list[i].getName();
            System.out.println("oldName:" + oldName);

            /**
             * 以"."為切割點，將檔名(coreName)與後綴(suffix)分開
             */
            String[] frags = oldName.split("\\.");
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

            boolean newFile = false;
            try {
                newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (newFile) {
                System.out.println("創建完成!");
            }
        }

        //背景代碼存放區結束----------------------------------------
        return;
    }

    /**
     * 第二題演示
     */
    static void question2() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();
        System.out.println(
                "class Test02 {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        try (//main方法一般不拋異常，此處亦可自動關流釋放資源\n" +
                        "                /**\n" +
                        "                 * FileInputStream:文件輸入流\n" +
                        "                 * 須導包方可使用----->java.io.FileInputStream\n" +
                        "                 * FileOutputStream:文件輸出流\n" +
                        "                 * 須導包方可使用----->java.io.FileOutputStream\n" +
                        "                 */\n" +
                        "                FileInputStream fis = new FileInputStream(\"demo/test.txt\");\n" +
                        "\n" +
                        "                /**\n" +
                        "                 * 文件輸出流 : FileOutputStream\n" +
                        "                 * 且JAVA嚴格區分大小寫，故此處仍無法正確調用FileInputStream類\n" +
                        "                 */\n" +
                        "//        FileInputStream fos = new FIleInputStream(\"test_cp.txt\");\n" +
                        "                FileOutputStream fos = new FileOutputStream(\"demo/test_cp.txt\");\n" +
                        "\n" +
                        "        ) {//try?\n" +
                        "\n" +
                        "            int d;\n" +
                        "            while ((d = fis.read()) != -1) {\n" +
                        "                /**\n" +
                        "                 * fos.write()方法中又再度調用fis.read()方法\n" +
                        "                 * 會造成讀取的字節先用以while判別boolean後，再調用後一個字節用以輸出\n" +
                        "                 * 變成只會輸出偶數字節的錯誤拼寫方式，因此需要用d綁定，不可於同一方法內多次調用fis.read()\n" +
                        "                 */\n" +
                        "//            fos.write(fis.read());\n" +
                        "                fos.write(d);\n" +
                        "            }\n" +
                        "\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 沒毛病\n" +
                        "             */\n" +
                        "//        System.out.println(\"复制完毕!\");\n" +
                        "//        fis.close();//try()自動關流\n" +
                        "//        fos.close();\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 打樁驗證\n" +
                        "             */\n" +
                        "            File file1 = new File(\"demo/test.txt\");\n" +
                        "            File file2 = new File(\"demo/test_cp.txt\");\n" +
                        "            long len1 = file1.length();\n" +
                        "            long len2 = file2.length();\n" +
                        "            if (len1 == len2) {\n" +
                        "                System.out.println(\"複製的檔案與原檔案大小一致------>複製成功\");\n" +
                        "            } else {\n" +
                        "                System.out.println(\"複製成功，但複製的檔案與原檔案大小不一致------>需要稍加修正\");\n" +
                        "            }\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "        } catch (Exception e) {//捕獲所有異常\n" +
                        "            e.printStackTrace();//但不處理\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n"
        );
        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------

        try (//main方法一般不拋異常，此處亦可自動關流釋放資源

             FileInputStream fis = new FileInputStream("demo/test.txt");
             FileOutputStream fos = new FileOutputStream("demo/test_cp.txt");

        ) {

            int d;
            while ((d = fis.read()) != -1) {
                fos.write(d);
            }

            /**
             * 打樁驗證
             */
            File file1 = new File("demo/test.txt");
            File file2 = new File("demo/test_cp.txt");
            long len1 = file1.length();
            long len2 = file2.length();
            if (len1 == len2) {
                System.out.println("複製的檔案與原檔案大小一致------>複製成功");
            } else {
                System.out.println("複製成功，但複製的檔案與原檔案大小不一致------>需要稍加修正");
            }


        } catch (Exception e) {//捕獲所有異常
            e.printStackTrace();//但不處理
        }

        //背景代碼存放區結束----------------------------------------
        return;
    }

    /**
     * 第三題演示
     */
    static void question3() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();
        System.out.println(
                        "class Test03 {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        /**\n" +
                        "         * FileInputStream:文件輸入流\n" +
                        "         * 須導包方可使用----->java.io.FileInputStream\n" +
                        "         * FileOutputStream:文件輸出流\n" +
                        "         * 須導包方可使用----->java.io.FileOutputStream\n" +
                        "         *\n" +
                        "         * BufferedInputStream:緩衝輸入流\n" +
                        "         * 須導包方可使用----->java.io.BufferedInputStream\n" +
                        "         * BufferedOutputStream:緩衝輸出流\n" +
                        "         * 須導包方可使用----->java.io.BufferedOutputStream\n" +
                        "         */\n" +
                        "\n" +
                        "        try (//main方法一般不拋異常，此處亦可自動關流釋放資源\n" +
                        "                FileInputStream fis = new FileInputStream(\"demo/test.txt\");\n" +
                        "\n" +
                        "                /**\n" +
                        "                 * 此處BuffereddInputStream拼寫錯誤，故無法正確聲明BufferedInputStream類對象，因而後續報錯\n" +
                        "                 */\n" +
                        "//                BufferedInputStream bis = new BuffereddInputStream(fis);\n" +
                        "                BufferedInputStream bis = new BufferedInputStream(fis);\n" +
                        "\n" +
                        "                FileOutputStream fos = new FileOutputStream(\"demo/test_cp2.txt\");\n" +
                        "\n" +
                        "                /**\n" +
                        "                 * 接收類型應與聲明類型有關聯(一致或向上造型)\n" +
                        "                 * 此處BufferedInputStream類與BufferedOutputStream類並無相互繼承、實現關係，故編譯錯誤\n" +
                        "                 */\n" +
                        "//                BufferedInputStream bos = new BufferedOutputStream(fos);\n" +
                        "                BufferedOutputStream bos = new BufferedOutputStream(fos);\n" +
                        "        ) {\n" +
                        "            int d = 0;\n" +
                        "            /**\n" +
                        "             * 當fis.read()方法讀至文件最末行時，會返回-1\n" +
                        "             * 當bis.read()方法讀至文件最末行時，會返回-1\n" +
                        "             * 因此在這個if分支中(bis.read() == -1)即是最末行，屬於邏輯錯誤\n" +
                        "             * 又if只判斷一次即停止，此處應不斷讀取至文件最末行(返回-1)，應使用while循環\n" +
                        "             */\n" +
                        "//            if ((d = bis.read()) == -1) {\n" +
                        "            while ((d = bis.read()) != -1) {\n" +
                        "                /**\n" +
                        "                 * 此處wirte為拼寫錯誤，故無法正確調用BufferedOutputStream中的實例方法write()\n" +
                        "                 */\n" +
                        "//                bos.wirte(d);\n" +
                        "                bos.write(d);\n" +
                        "\n" +
                        "                /**\n" +
                        "                 * 緩出書出流BufferedOutputStream底層邏輯其實就是塊讀\n" +
                        "                 * 然而不能保證每次讀寫的文件大小都是塊讀大小的整數倍\n" +
                        "                 * 因此再最末次讀寫時，未滿塊讀容量的部分須要透過flush()方法釋放\n" +
                        "                 * 一般而言，關流方法close()中會自帶一個flush()方法\n" +
                        "                 *\n" +
                        "                 * 若不使用flush仍可複製成功，但無法通過打樁驗證\n" +
                        "                 */\n" +
                        "                bos.flush();\n" +
                        "            }\n" +
                        "//            System.out.println(\"複製完畢!\");\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 打樁驗證\n" +
                        "             */\n" +
                        "            File file1 = new File(\"demo/test.txt\");\n" +
                        "            File file2 = new File(\"demo/test_cp2.txt\");\n" +
                        "            long len1 = file1.length();\n" +
                        "            long len2 = file2.length();\n" +
                        "            if (len1 == len2) {\n" +
                        "                System.out.println(\"複製的檔案與原檔案大小一致------>複製成功\");\n" +
                        "            } else {\n" +
                        "                System.out.println(\"複製成功，但複製的檔案與原檔案大小不一致------>需要稍加修正\");\n" +
                        "            }\n" +
                        "\n" +
                        "        } catch (Exception e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }\n" +
                        "\n" +
                        "    }\n" +
                        "}"
        );
        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
                try (//main方法一般不拋異常，此處亦可自動關流釋放資源
                     FileInputStream fis = new FileInputStream("demo/test.txt");

                     BufferedInputStream bis = new BufferedInputStream(fis);

                     FileOutputStream fos = new FileOutputStream("demo/test_cp2.txt");

                     BufferedOutputStream bos = new BufferedOutputStream(fos);
                ) {
                    int d = 0;

                    while ((d = bis.read()) != -1) {

                        bos.write(d);
                        bos.flush();
                    }

                    /**
                     * 打樁驗證
                     */
                    File file1 = new File("demo/test.txt");
                    File file2 = new File("demo/test_cp2.txt");
                    long len1 = file1.length();
                    long len2 = file2.length();
                    if (len1 == len2) {
                        System.out.println("複製的檔案與原檔案大小一致------>複製成功");
                    } else {
                        System.out.println("複製成功，但複製的檔案與原檔案大小不一致------>需要稍加修正");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


        //背景代碼存放區結束----------------------------------------
        return;
    }

    /**
     * 第四題演示
     */
    static void question4() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();
        System.out.println(
                "\n" +
                        "class Test04 {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        home();\n" +
                        "        return;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 首頁\n" +
                        "     */\n" +
                        "    private static void home() {\n" +
                        "        while (true) {\n" +
                        "            System.out.println(\"請輸入您要辦理的業務: 1.新增使用者 2.使用者清單一覽 99.退出程序\");\n" +
                        "            int chosen = 0;\n" +
                        "            try {\n" +
                        "                chosen = new Scanner(System.in).nextInt();//接收選擇\n" +
                        "\n" +
                        "                switch (chosen) {\n" +
                        "                    case 1:\n" +
                        "                        createNewAccount();\n" +
                        "                        break;\n" +
                        "                    case 2:\n" +
                        "                        showList();\n" +
                        "                        break;\n" +
                        "                    case 99:\n" +
                        "                        System.out.println(\"正在關閉程序......\");\n" +
                        "                        return;\n" +
                        "                    default:\n" +
                        "                        throw new RuntimeException();\n" +
                        "                }\n" +
                        "            } catch (Exception e) {\n" +
                        "                System.err.println(\"輸入錯誤:您的選擇可能包含無法辨識的字符或查無此業務，請重新輸入\");\n" +
                        "                home();\n" +
                        "            }\n" +
                        "\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 查看使用者列表業務\n" +
                        "     */\n" +
                        "    private static void showList() {\n" +
                        "        File path = new File(\"demo/users\");\n" +
                        "        File[] lists = path.listFiles();\n" +
                        "        for (File list : lists) {\n" +
                        "            System.out.println(list.getName());\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 帳戶新增業務\n" +
                        "     */\n" +
                        "    private static void createNewAccount() {\n" +
                        "        /**\n" +
                        "         * 接收數據並生成對象\n" +
                        "         */\n" +
                        "        User user = howToCreateUser();\n" +
                        "\n" +
                        "        /**\n" +
                        "         * 使用對象輸出流執行序列化並輸出對象生成名稱.obj作保存\n" +
                        "         */\n" +
                        "        File path = new File(\"demo/users\");//定位模擬人資數據庫\n" +
                        "        createUser_obj(path, user);//傳入所需資料自動生成\n" +
                        "\n" +
                        "        home();//結束後回到選單\n" +
                        "        return;//防止貫穿執行\n" +
                        "\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 引導使用者生成一份對象資料的方法\n" +
                        "     */\n" +
                        "    private static User howToCreateUser() {\n" +
                        "        /**\n" +
                        "         * 接收並生成對象\n" +
                        "         */\n" +
                        "        System.out.println(\"以下將引導您建立一位新使用者......\");\n" +
                        "        String name = enterName();\n" +
                        "        String pwd = enterPwd();\n" +
                        "        String nick = enterNick();\n" +
                        "        int age = enterAge();\n" +
                        "\n" +
                        "        /**\n" +
                        "         * 資料收集完畢--->創建對象\n" +
                        "         */\n" +
                        "        User user = new User(name, pwd, nick, age);\n" +
                        "//        System.out.println(user);//打樁\n" +
                        "\n" +
                        "        return user;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 接收使用者輸入的姓名並驗證是否合法的方法\n" +
                        "     */\n" +
                        "    private static String enterName() {\n" +
                        "        System.out.println(\"請輸入名稱，限使用英文大小寫字母、數字、下底線，1~32字，不可為空\");\n" +
                        "        String nameByUser = new Scanner(System.in).nextLine();//接收使用者數據\n" +
                        "        if (isNothing(nameByUser)) {//空了，重來\n" +
                        "            enterName();\n" +
                        "        }\n" +
                        "        /**\n" +
                        "         * 正則比對\n" +
                        "         */\n" +
                        "        String regexName = \"[\\\\w_]{1,32}\";\n" +
                        "        if (!nameByUser.matches(regexName)) {\n" +
                        "            try {\n" +
                        "                if (nameByUser.length() < 1) {//排除過短\n" +
                        "                    throw new RuntimeException(\"名稱過短，請重新輸入\");\n" +
                        "                } else if (nameByUser.length() > 32) {//排除過長\n" +
                        "                    throw new RuntimeException(\"名稱過長，請重新輸入\");\n" +
                        "                } else {//排除剩餘不合法(不合法字元)\n" +
                        "                    throw new RuntimeException(\"名稱包含不合法字元，請重新輸入\");\n" +
                        "                }\n" +
                        "            } catch (RuntimeException e) {\n" +
                        "                e.printStackTrace();\n" +
                        "            }\n" +
                        "            enterName();//重新再來\n" +
                        "        }\n" +
                        "\n" +
                        "        return nameByUser;\n" +
                        "\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 接收使用者輸入的密碼並驗證是否合法的方法\n" +
                        "     */\n" +
                        "    private static String enterPwd() {\n" +
                        "        System.out.println(\"請設定密碼，限使用英文大小寫字母、數字，8~20字元，最少需要一個大寫、一個小寫、一個數字\");\n" +
                        "\n" +
                        "        String pwdByUser = new Scanner(System.in).nextLine();//接收使用者數據\n" +
                        "        if (isNothing(pwdByUser)) {//空了，重來\n" +
                        "            enterPwd();\n" +
                        "        }\n" +
                        "        /**\n" +
                        "         * 正則比對\n" +
                        "         */\n" +
                        "        String regexPwd = \"[\\\\w]{8,20}\";\n" +
                        "        String regex0_9 = \"[^0-9]+\";//無數字\n" +
                        "        String regexA_Z = \"[^A-Z]+\";//無大寫\n" +
                        "        String regexa_z = \"[^a-z]+\";//無小寫\n" +
                        "\n" +
                        "        if (pwdByUser.matches(regexPwd)//符合字數與非符號正則&&不可無數字&&不可無大寫&&不可無小寫\n" +
                        "                && !pwdByUser.matches(regex0_9) && !pwdByUser.matches(regexA_Z) && !pwdByUser.matches(regexa_z)) {\n" +
                        "            return pwdByUser;\n" +
                        "\n" +
                        "        } else {//無法全部符合的解析\n" +
                        "\n" +
                        "            try {\n" +
                        "                if (pwdByUser.length() < 8) {//排除過短\n" +
                        "                    throw new RuntimeException(\"密碼過短，請重新輸入\");\n" +
                        "//                    System.err.println(\"\\\"密碼過短，請重新輸入\\\"\");//打樁\n" +
                        "\n" +
                        "                } else if (pwdByUser.length() > 20) {//排除過長\n" +
                        "                    throw new RuntimeException(\"密碼過長，請重新輸入\");\n" +
                        "//                    System.err.println(\"\\\"密碼過短，請重新輸入\\\"\");//打樁\n" +
                        "\n" +
                        "                } else if (pwdByUser.matches(regex0_9)) {//排除不含數字\n" +
                        "                    throw new RuntimeException(\"密碼未包含數字，請重新輸入\");\n" +
                        "//                    System.err.println(\"\\\"密碼未包含數字，請重新輸入\\\"\");//打樁\n" +
                        "\n" +
                        "                } else if (pwdByUser.matches(regexA_Z)) {//排除不含大寫\n" +
                        "                    throw new RuntimeException(\"密碼未包含大寫字母，請重新輸入\");\n" +
                        "//                    System.err.println(\"\\\"密碼未包含大寫字母，請重新輸入\\\"\");//打樁\n" +
                        "\n" +
                        "                } else if (pwdByUser.matches(regexa_z)) {//排除不含小寫\n" +
                        "                    throw new RuntimeException(\"密碼未包含小寫字母，請重新輸入\");\n" +
                        "//                    System.err.println(\"\\\"密碼未包含小寫字母，請重新輸入\\\"\");//打樁\n" +
                        "\n" +
                        "                } else {//排除剩餘不合法(不合法字元)\n" +
                        "                    throw new RuntimeException(\"名稱包含不合法字元，請重新輸入\");\n" +
                        "//                    System.err.println(\"\\\"名稱包含不合法字元，請重新輸入\\\"\");//打樁\n" +
                        "                }\n" +
                        "            } catch (RuntimeException e) {//捕獲任何異常一律重來\n" +
                        "                e.printStackTrace();\n" +
                        "                return enterPwd();//重新再來\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 接收使用者輸入的暱稱並驗證是否合法的方法\n" +
                        "     */\n" +
                        "\n" +
                        "    private static String enterNick() {\n" +
                        "        System.out.println(\"請設定暱稱，不可為空\");\n" +
                        "        String nickByUser = new Scanner(System.in).nextLine();//接收使用者數據\n" +
                        "        if (isNothing(nickByUser)) {//空了，重來\n" +
                        "            enterNick();\n" +
                        "        }\n" +
                        "        return nickByUser;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 接收使用者輸入的年紀並驗證是否合法的方法\n" +
                        "     */\n" +
                        "    private static int enterAge() {\n" +
                        "        System.out.println(\"請輸入年齡，只能數字且不可為空\");\n" +
                        "        int ageByUser = 0;//接收使用者數據\n" +
                        "        try {\n" +
                        "            ageByUser = new Scanner(System.in).nextInt();\n" +
                        "        } catch (Exception e) {\n" +
                        "            System.err.println(\"年紀包含不合法字元，請重新輸入\");\n" +
                        "            return enterAge();\n" +
                        "        }\n" +
                        "\n" +
                        "        /**\n" +
                        "         * 合法與否比對\n" +
                        "         */\n" +
                        "        if (ageByUser <= 0 || ageByUser > 130) {//判定數字是否合理\n" +
                        "            try {\n" +
                        "                throw new RuntimeException(\"輸入年紀不合法，請重新輸入\");\n" +
                        "            } catch (RuntimeException e) {\n" +
                        "                e.printStackTrace();\n" +
                        "                return enterAge();//重來\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            return ageByUser;\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 驗證使用者輸入字符串是否為空的方法\n" +
                        "     */\n" +
                        "    private static boolean isNothing(String strFromUser) {\n" +
                        "        if (strFromUser.trim().length() == 0) {//去白後為空\n" +
                        "            try {\n" +
                        "                throw new RuntimeException(\"使用者名稱不可空白\");\n" +
                        "            } catch (RuntimeException e) {\n" +
                        "                e.printStackTrace();\n" +
                        "            }\n" +
                        "            return true;//屬於空白輸入\n" +
                        "        } else {\n" +
                        "            return false;//非空\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 建立對象輸出流並生成給定 名稱.後綴 的檔案\n" +
                        "     *\n" +
                        "     * @param path 生成路徑(目錄)\n" +
                        "     * @param user 傳入數據對象名稱，會自動解析生成的人資檔名\n" +
                        "     */\n" +
                        "    private static void createUser_obj(File path, User user) {\n" +
                        "        try (//創建文件輸出流並接上對象輸出流\n" +
                        "             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + \"/\" + user.getName() + \".obj\"));\n" +
                        "        ) {\n" +
                        "            oos.writeObject(user);\n" +
                        "        } catch (IOException e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }\n" +
                        "\n" +
                        "        System.out.println(user.getName() + \".obj創建完成!!\");\n" +
                        "    }\n" +
                        "}\n"
        );
        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        String args[] = {};
        Test04.main(args);
        //背景代碼存放區結束----------------------------------------
        return;
    }
    //Test4背景代碼開始==============================================

    private static class Test04 {
        public static void main(String[] args) {
            home();
            return;
        }

        /**
         * 首頁
         */
        private static void home() {
            while (true) {
                System.out.println("請輸入您要辦理的業務: 1.新增使用者 2.使用者清單一覽 99.退出程序");
                int chosen = 0;
                try {
                    chosen = new Scanner(System.in).nextInt();//接收選擇

                    switch (chosen) {
                        case 1:
                            createNewAccount();
                            break;
                        case 2:
                            showList();
                            break;
                        case 99:
                            System.out.println("正在關閉程序......");
                            return;
                        default:
                            throw new RuntimeException();
                    }
                } catch (Exception e) {
                    System.err.println("輸入錯誤:您的選擇可能包含無法辨識的字符或查無此業務，請重新輸入");
                    home();
                }

            }
        }

        /**
         * 查看使用者列表業務
         */
        private static void showList() {
            File path = new File("demo/users");
            File[] lists = path.listFiles();
            for (File list : lists) {
                System.out.println(list.getName());
            }
        }

        /**
         * 帳戶新增業務
         */
        private static void createNewAccount() {
            /**
             * 接收數據並生成對象
             */
            User user = howToCreateUser();

            /**
             * 使用對象輸出流執行序列化並輸出對象生成名稱.obj作保存
             */
            File path = new File("demo/users");//定位模擬人資數據庫
            createUser_obj(path, user);//傳入所需資料自動生成

            home();//結束後回到選單
            return;//防止貫穿執行

        }

        /**
         * 引導使用者生成一份對象資料的方法
         */
        private static User howToCreateUser() {
            /**
             * 接收並生成對象
             */
            System.out.println("以下將引導您建立一位新使用者......");
            String name = enterName();
            String pwd = enterPwd();
            String nick = enterNick();
            int age = enterAge();

            /**
             * 資料收集完畢--->創建對象
             */
            User user = new User(name, pwd, nick, age);
//        System.out.println(user);//打樁

            return user;
        }

        /**
         * 接收使用者輸入的姓名並驗證是否合法的方法
         */
        private static String enterName() {
            System.out.println("請輸入名稱，限使用英文大小寫字母、數字、下底線，1~32字，不可為空");
            String nameByUser = new Scanner(System.in).nextLine();//接收使用者數據
            if (isNothing(nameByUser)) {//空了，重來
                enterName();
            }
            /**
             * 正則比對
             */
            String regexName = "[\\w_]{1,32}";
            if (!nameByUser.matches(regexName)) {
                try {
                    if (nameByUser.length() < 1) {//排除過短
                        throw new RuntimeException("名稱過短，請重新輸入");
                    } else if (nameByUser.length() > 32) {//排除過長
                        throw new RuntimeException("名稱過長，請重新輸入");
                    } else {//排除剩餘不合法(不合法字元)
                        throw new RuntimeException("名稱包含不合法字元，請重新輸入");
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                enterName();//重新再來
            }

            return nameByUser;

        }

        /**
         * 接收使用者輸入的密碼並驗證是否合法的方法
         */
        private static String enterPwd() {
            System.out.println("請設定密碼，限使用英文大小寫字母、數字，8~20字元，最少需要一個大寫、一個小寫、一個數字");

            String pwdByUser = new Scanner(System.in).nextLine();//接收使用者數據
            if (isNothing(pwdByUser)) {//空了，重來
                enterPwd();
            }
            /**
             * 正則比對
             */
            String regexPwd = "[\\w]{8,20}";
            String regex0_9 = "[^0-9]+";//無數字
            String regexA_Z = "[^A-Z]+";//無大寫
            String regexa_z = "[^a-z]+";//無小寫

            if (pwdByUser.matches(regexPwd)//符合字數與非符號正則&&不可無數字&&不可無大寫&&不可無小寫
                    && !pwdByUser.matches(regex0_9) && !pwdByUser.matches(regexA_Z) && !pwdByUser.matches(regexa_z)) {
                return pwdByUser;

            } else {//無法全部符合的解析

                try {
                    if (pwdByUser.length() < 8) {//排除過短
                        throw new RuntimeException("密碼過短，請重新輸入");
//                    System.err.println("\"密碼過短，請重新輸入\"");//打樁

                    } else if (pwdByUser.length() > 20) {//排除過長
                        throw new RuntimeException("密碼過長，請重新輸入");
//                    System.err.println("\"密碼過短，請重新輸入\"");//打樁

                    } else if (pwdByUser.matches(regex0_9)) {//排除不含數字
                        throw new RuntimeException("密碼未包含數字，請重新輸入");
//                    System.err.println("\"密碼未包含數字，請重新輸入\"");//打樁

                    } else if (pwdByUser.matches(regexA_Z)) {//排除不含大寫
                        throw new RuntimeException("密碼未包含大寫字母，請重新輸入");
//                    System.err.println("\"密碼未包含大寫字母，請重新輸入\"");//打樁

                    } else if (pwdByUser.matches(regexa_z)) {//排除不含小寫
                        throw new RuntimeException("密碼未包含小寫字母，請重新輸入");
//                    System.err.println("\"密碼未包含小寫字母，請重新輸入\"");//打樁

                    } else {//排除剩餘不合法(不合法字元)
                        throw new RuntimeException("名稱包含不合法字元，請重新輸入");
//                    System.err.println("\"名稱包含不合法字元，請重新輸入\"");//打樁
                    }
                } catch (RuntimeException e) {//捕獲任何異常一律重來
                    e.printStackTrace();
                    return enterPwd();//重新再來
                }
            }
        }

        /**
         * 接收使用者輸入的暱稱並驗證是否合法的方法
         */

        private static String enterNick() {
            System.out.println("請設定暱稱，不可為空");
            String nickByUser = new Scanner(System.in).nextLine();//接收使用者數據
            if (isNothing(nickByUser)) {//空了，重來
                enterNick();
            }
            return nickByUser;
        }

        /**
         * 接收使用者輸入的年紀並驗證是否合法的方法
         */
        private static int enterAge() {
            System.out.println("請輸入年齡，只能數字且不可為空");
            int ageByUser = 0;//接收使用者數據
            try {
                ageByUser = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.err.println("年紀包含不合法字元，請重新輸入");
                return enterAge();
            }

            /**
             * 合法與否比對
             */
            if (ageByUser <= 0 || ageByUser > 130) {//判定數字是否合理
                try {
                    throw new RuntimeException("輸入年紀不合法，請重新輸入");
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return enterAge();//重來
                }
            } else {
                return ageByUser;
            }
        }

        /**
         * 驗證使用者輸入字符串是否為空的方法
         */
        private static boolean isNothing(String strFromUser) {
            if (strFromUser.trim().length() == 0) {//去白後為空
                try {
                    throw new RuntimeException("使用者名稱不可空白");
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                return true;//屬於空白輸入
            } else {
                return false;//非空
            }
        }

        /**
         * 建立對象輸出流並生成給定 名稱.後綴 的檔案
         *
         * @param path 生成路徑(目錄)
         * @param user 傳入數據對象名稱，會自動解析生成的人資檔名
         */
        private static void createUser_obj(File path, User user) {
            try (//創建文件輸出流並接上對象輸出流
                 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "/" + user.getName() + ".obj"));
            ) {
                oos.writeObject(user);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(user.getName() + ".obj創建完成!!");
        }


    }

    //Test4背景代碼結束==============================================

    /**
     * 第五題演示
     */
    static void question5() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        //背景代碼存放區結束----------------------------------------
        return;
    }

    /**
     * 第六題演示
     */
    static void question6() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        //背景代碼存放區結束----------------------------------------
        backToMenu();
    }

    /**
     * 第七題演示
     */
    static void question7() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        //背景代碼存放區結束----------------------------------------
        backToMenu();
    }

    /**
     * 第八題演示
     */
    static void question8() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        //背景代碼存放區結束----------------------------------------
        backToMenu();
    }

    /**
     * 第九題演示
     */
    static void question9() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        //背景代碼存放區結束----------------------------------------
        backToMenu();
    }

    /**
     * 第十題演示
     */
    static void question10() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        //背景代碼存放區結束----------------------------------------
        backToMenu();
    }

}


