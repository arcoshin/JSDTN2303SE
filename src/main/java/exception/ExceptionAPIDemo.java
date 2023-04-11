package exception;

public class ExceptionAPIDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了");

        try {
            String str = "abc";
            System.out.println(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            e.printStackTrace();//輸出錯誤訊息

            String message = e.getMessage();//獲取錯誤訊息
            System.out.println("e : " + e);
            System.out.println("message : " + message);

        }

        System.out.println("程序結束了");
    }
}
