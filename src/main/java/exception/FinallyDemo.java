package exception;

public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了");
        try {
            String str = null;
            System.out.println(str.length());
            return;
        } catch (Exception e) {
            System.out.println("catch塊已被執行");
        } finally {
            /**
             * catch塊要不要執行由try塊有無異常決定
             * finally塊不論有無異常，他都一定會走
             *
             * 如代碼中出現return，此時放在finally塊後的代碼將不會被執行
             * 但若放在finally塊中則保證一定會執行完畢後，才執行後一句的return
             */
            System.out.println("finally塊已被執行");
        }
        System.out.println("程序結束了");
    }
}
