package exception;

/**
 * 異常的拋出
 */
public class ThrowDemo {
    public static void main(String[] args) {
        Person person = new Person();

        /**
         * 當方法中有可能出現非運行中異常時
         * 編譯器會要求所在代碼必須處理異常
         * 1.繼續拋出異常
         * 2.try&catch捕獲
         * 但要注意，永遠不要在main方法中拋異常
         */
        try {
            person.setAge(1050);
        } catch (IllegalAgeException e) {
            e.printStackTrace();
        }
    }
}
