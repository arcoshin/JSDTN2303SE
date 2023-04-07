package homework.day00;

import java.util.Scanner;

/**
 * weekendHomework 2023.0X.XX
 * 作業類/周末作業類
 * HomeworkChecker.ver3.7
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
 * 20230328_XCX
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
        data[0] = new Question(1, "", "");
        data[1] = new Question(2, "", "");
        data[2] = new Question(3, "", "");
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

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
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

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
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

        //=======================================================
        willShowURun();
        //背景代碼存放區開始----------------------------------------
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


