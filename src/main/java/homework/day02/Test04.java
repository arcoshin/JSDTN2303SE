package homework.day02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

class Test04 {
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

