package exception;

/**
 * 異常處理機制
 */
public class TryCatchDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了");

        try {
            String line = null;
            System.out.println(line.length());
            System.out.println(line.charAt(0));
            System.out.println(Integer.parseInt(line));
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("出現的所有異常已排除");
        }

        System.out.println("程序結束了");
    }
}
