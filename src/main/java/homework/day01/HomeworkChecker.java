package homework.day01;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Scanner;

/**
 * weekendHomework 2023.04.06
 * File作業類
 * HomeworkChecker.ver3.9
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
 * =====================
 * Next Goal:
 * =====================
 * 1.Semi-automatic press question class;(trying io)
 * 2.Trying set another container to replace ArraySystem, it maybe...
 * (1.)static Question[]...(Now)
 * (2.)static Collection
 * (3.)static Map...(Goal)
 * 3.Thinking about using Reflection would make Systems better ?
 * 4.Whether if building a "KeyAdapter" system to replace some Scanners ?
 * <p>
 * <p>
 * 20230406_XCX
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
            System.out.println(questionList[i].questionContent);
            System.out.println();
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
        Question[] data = new Question[3];//<<---------------------------------手動修改
        data[0] = new Question(1, "Test1:命名題",
                "程序運行時，要求用戶輸入一文件名，如果已存在則要求重新輸入，直到不重複為止，並實際創建該文件於當前目錄");
        data[1] = new Question(2, "Test2:應用題",
                "使用匿名內部類及Lambda兩種寫法列出當前目錄中所有名字中包含s的子項");
        data[2] = new Question(3, "Test3:改錯練習", "");
//        data[3] = new Question(4, "", "");
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
                "class Test1 {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        /**\n" +
                        "         * 1.接收使用者輸入的題示:\n" +
                        "         * 1-1提示使用者輸入\n" +
                        "         * 1-2聲明一個Scanner對象接收使用者輸入的數據\n" +
                        "         */\n" +
                        "        System.out.println(\"請輸入要創建的文件名，若要退出則輸入EXIT(不分大小寫)\");//1-1\n" +
                        "        String user;//先聲明後使用，用以停滯死循環\n" +
                        "\n" +
                        "        /**\n" +
                        "         * 2.針對輸入數據進行判定:\n" +
                        "         *  2-1分析輸入的數據:\n" +
                        "         *      2-1-1接收到Exit就退出程序\n" +
                        "         *      2-1-2沒有後綴默認補上txt\n" +
                        "         *  2-2綁定File地址比對文件名\n" +
                        "         *      2-2-1遍歷結果為重複時，則提示用戶更改用戶名\n" +
                        "         *      2-2-2遍歷結果非重複時，則實際生成該文件\n" +
                        "         */\n" +
                        "        while (true) {//不斷重複，直到特定條件才解除循環\n" +
                        "            try {\n" +
                        "                user = new Scanner(System.in).nextLine();//1-2，放在這才可以滯留死循環的連續執行\n" +
                        "            } catch (Exception e) {//保證程序得以運行\n" +
                        "                throw new RuntimeException(\"輸入錯誤，請重新輸入\");\n" +
                        "            }\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 預處理用戶輸入的資訊\n" +
                        "             */\n" +
                        "            if (\"EXIT\".equals(user.trim().toUpperCase())) {//不分大小寫出現exit即退出循環\n" +
                        "                return;//應用戶要求退出程序\n" +
                        "            }\n" +
                        "\n" +
                        "            if (user.indexOf(\".\") == -1) {//文件名無後綴時，則自動補txt\n" +
                        "                user = user + \".txt\";//無後綴默認拼接.txt，要記得以原變量接收(作用域)\n" +
                        "            }\n" +
                        "\n" +
                        "            /**\n" +
                        "             * 比對有無重複，若無則生成，若重複則要求用戶更改文件名\n" +
                        "             */\n" +
                        "            File file = new File(\"src/main/java/homework/day01/demo/\" + user);//綁定要生成的對象\n" +
                        "            if (file.exists()) {//如果發現文件已存在\n" +
                        "                System.out.println(\"警告:文件名已重複，請重新輸入\");\n" +
                        "            } else {//如果發現文件不存在\n" +
                        "                boolean result = false;//作用域調整\n" +
                        "                try {//main一般不拋異常\n" +
                        "                    result = file.createNewFile();//創建新文件並接收返回值\n" +
                        "                } catch (IOException e) {}//捕獲異常後不處理即可\n" +
                        "                if (result) {//創建成功走此分支\n" +
                        "                    System.out.println(file.getName() + \"創建成功\");\n" +
                        "                    System.out.println(\"程序即將關閉\");\n" +
                        "                    try {\n" +
                        "                        Thread.sleep(1000);//模擬系統關閉中\n" +
                        "                    } catch (InterruptedException e) {}//被叫醒的異常捕獲，不處理即可\n" +
                        "                    return;//創建成功退出程序\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n"
        );
        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        System.out.println("請輸入要創建的文件名，若要退出則輸入EXIT(不分大小寫)");
        String user;

        while (true) {
            try {
                user = new Scanner(System.in).nextLine();
            } catch (Exception e) {//保證程序得以運行
                throw new RuntimeException("輸入錯誤，請重新輸入");
            }

            //預處理，排他
            if ("EXIT".equals(user.trim().toUpperCase())) {//不分大小寫出現exit即退出循環
                break;//應用戶要求退出程序，此處應改為退出循環並非結束方法(不同於Test1.main())
            }

            //預處理，補綴
            if (user.indexOf(".") == -1) {
                user = user + ".txt";
            }

            //比對
            File file = new File("src/main/java/homework/day01/demo/" + user);//綁定對象
            if (file.exists()) {//已存在
                System.out.println("警告:文件名已重複，請重新輸入");
            } else {//不存在
                boolean result = false;
                try {
                    result = file.createNewFile();
                } catch (IOException e) {
                }
                if (result) {//創建成功
                    System.out.println(file.getName() + "創建成功");
                    System.out.println("程序即將關閉");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    break;//此處應改為退出循環並非結束方法(不同於Test1.main())
                }

            }

        }
        //背景代碼存放區結束----------------------------------------
        backToMenu();
    }

    /**
     * 第二題演示
     */
    static void question2() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();
        System.out.println(
                "class Test2 {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        File file = new File(\"src/main/java/homework/day01/demo\");//綁定文件地址\n" +
                        "        /**\n" +
                        "         * 匿名類部類 : new接口(){匿名內部類的類體} ------創建一個繼承接口的匿名子類，{}為其類體\n" +
                        "         */\n" +
                        "        File[] subs1 = file.listFiles(new FileFilter() {//以File數組接收過濾結果\n" +
                        "            @Override\n" +
                        "            public boolean accept(File pathname) {//重寫的方法:pathname代表當前的過濾對象名稱\n" +
                        "                /**\n" +
                        "                 * return定義過濾條件\n" +
                        "                 * pathname當前過濾對象.getName取檔名.contains(\"s\")是否包含s\n" +
                        "                 */\n" +
                        "                return pathname.getName().contains(\"s\");\n" +
                        "            }\n" +
                        "        });\n" +
                        "        System.out.print(\"匿名內部類的過濾結果:共有\" + subs1.length + \"筆\");//美化拼接\n" +
                        "        for (File sub : subs1) {//增強型for循環遍歷數組\n" +
                        "            System.out.println(sub.getName() +\" \");\n" +
                        "        }\n" +
                        "\n" +
                        "        /**\n" +
                        "         * Lambda表達式 : (參數列表) -> {重寫的方法體}\n" +
                        "         * 函數式接口名稱已被當前方法的形參限定，故可省略\n" +
                        "         * 函數式接口只有一個需要重寫的抽象方法:方法名可省略\n" +
                        "         * 參數類型已被方法所規定，而需重寫的方法只有一個，因而也可省略\n" +
                        "         * 參數若只有一個，省略()\n" +
                        "         * 方法體若只有一句，省略{}，若無{}則return即無意義故也可省略\n" +
                        "         */\n" +
                        "        File[] subs2 = file.listFiles(f -> f.getName().contains(\"s\"));\n" +
                        "        System.out.print(\"Lambda表達式的過濾結果:共有\" + subs2.length + \"筆\");\n" +
                        "        for (File sub : subs2) {//增強型for循環遍歷數組\n" +
                        "            System.out.println(sub.getName()+\" \");\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n"
        );
        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        File file = new File("src/main/java/homework/day01/demo");//綁定地址
        //inner
        File[] subs1 = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().contains("s");
            }
        });
        System.out.println("匿名內部類的過濾結果:共有" + subs1.length + "筆");
        for (File sub : subs1) {
            System.out.println(sub.getName() + " ");
        }

        //Lambda
        File[] subs2 = file.listFiles(f -> f.getName().contains("s"));
        System.out.println("Lambda表達式的過濾結果:共有" + subs2.length + "筆");
        for (File sub : subs2) {
            System.out.println(sub.getName() + " ");
        }
        //背景代碼存放區結束----------------------------------------
        backToMenu();
    }

    /**
     * 第三題演示
     */
    static void question3() throws InterruptedException {
        waitForPreparing();
        //=======================================================
        willShowUCode();
        System.out.println(
                "class Test3 {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        /**\n" +
                        "         * Scanner:類名應遵循大駝峰命名法，否則無法調用java.util中的Scanner掃描器工具類\n" +
                        "         */\n" +
                        "//\t\tscanner s = new scanner(System.in);\n" +
                        "        Scanner s = new Scanner(System.in);\n" +
                        "\n" +
                        "        /**\n" +
                        "         * 沒毛病\n" +
                        "         */\n" +
                        "        System.out.println(\"请输入关键字:\");\n" +
                        "\n" +
                        "        /**\n" +
                        "         * 首具代碼中已聲明Scanner對象名為s，故此處無正確實例對象可調用Scanner工具類中的實例方法nextLine()---->改回s\n" +
                        "         * 方法名應遵循小駝峰命名法，否則此處無法正確調用Scanner類中的實例方法nextLine()\n" +
                        "         */\n" +
                        "//        String key = s.nextline();\n" +
                        "        String key = s.nextLine();\n" +
                        "\n" +
                        "        /**\n" +
                        "         * 相對路徑中\".\"代表的是當前項目\n" +
                        "         * \"./\"代表的是當前項目目錄下，一般為默認可省略\n" +
                        "         * \"./src/main/java/homework/day01\"才是表示當前目錄\n" +
                        "         */\n" +
                        "//\t\t  File dir = new File(\".\");//定位当前目录\n" +
                        "        File dir = new File(\"src/main/java/homework/day01\");\n" +
                        "\n" +
                        "        /**\n" +
                        "         * 沒毛病\n" +
                        "         */\n" +
                        "        if (dir.isDirectory()) {//判断是否为目录\n" +
                        "            File files[] = dir.listFiles();//获取该目录下所有子项\n" +
                        "            /**\n" +
                        "             * 字符串類型中，獲取字符串string長度的方法為:string.length()\n" +
                        "             * 數組中，獲取數組array長度的方法為:array.length\n" +
                        "             *\n" +
                        "             * 另外，數組下標由0開始，故運行中可訪問的最末元素下標為length-1或者<length\n" +
                        "             * 此為運行中異常，非編譯錯誤，故能仍可啟動程序，但會回報異常\n" +
                        "             * ArrayIndexOutOfBoundsException : 數組下標越界異常\n" +
                        "             */\n" +
                        "//\t\t\t  for(int i=0;i<=files.length();i++) {\n" +
                        "            for (int i = 0; i < files.length; i++) {\n" +
                        "\n" +
                        "                /**\n" +
                        "                 * String:類名應遵循大駝峰命名法，否則無法正確聲明String類型變量\n" +
                        "                 */\n" +
                        "//                string fileName = files[i].getName();//获取每一个子项的名字\n" +
                        "                String fileName = files[i].getName();\n" +
                        "\n" +
                        "                /**\n" +
                        "                 * fileName本身為String類型，可以調用String提供的實例方法contains()，\n" +
                        "                 * boolean contains(String str)用以返回該字符串調用對象中是否包含給定元素str的方法\n" +
                        "                 *\n" +
                        "                 * Java中嚴格區分大小寫，變量名應遵循小駝峰命名法\n" +
                        "                 * 前行代碼已正確聲明接收對象為fileName，此處大小寫應一致，否則視filename為不同變量\n" +
                        "                 * 又filename為此前未正確聲明之變量，故此處會發生編譯錯誤\n" +
                        "                 *\n" +
                        "                 * 輸出語句中或者傳參時，若使用字符串類型，則傳入的是字符串的字面量本身，而並非變量本身\n" +
                        "                 */\n" +
                        "//                if (filename.不知道什么方法) {//判断名字中是否包含key的内容\n" +
                        "                if (fileName.contains(key)) {\n" +
                        "\n" +
                        "                    /**\n" +
                        "                     * 前段代碼已正確聲明接收對象為fileName，此處拼寫應一致，否則視f1leNeme為不同變量\n" +
                        "                     * 又f1leNeme為此前未正確聲明之變量，故此處會發生編譯錯誤\n" +
                        "                     */\n" +
                        "//                    System.out.println(f1leNeme);//包含就输出这个名字\n" +
                        "                    System.out.println(fileName);\n" +
                        "\n" +
                        "                }//if end\n" +
                        "            }//循環體外\n" +
                        "        }//如果非目錄走此分支\n" +
                        "    }//main end\n" +
                        "}//class end\n"
        );
        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
        Scanner s = new Scanner(System.in);
        System.out.println("请输入关键字:");
        String key = s.nextLine();
        File dir = new File("src/main/java/homework/day01");
        if (dir.isDirectory()) {
            File files[] = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (fileName.contains(key)) {
                    System.out.println(fileName);
                }
            }
        }
        //背景代碼存放區結束----------------------------------------
        backToMenu();
    }

    /**
     * 第四題演示
     */
    static void question4() throws InterruptedException {
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
        backToMenu();
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


