package exception;

import java.io.FileOutputStream;
import java.io.IOException;

public class FinallyDemo2 {
    public static void main(String[] args) {
        System.out.println("程序開始了");

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("./demo/fos.dat");
            fos.write(1);
            return;
        } catch (Exception e) {
            System.out.println("出錯了");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("程序結束了");

        }
    }
}
