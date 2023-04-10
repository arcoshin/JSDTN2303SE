package exception;

/**
 * 面試題
 */
import java.io.FileOutputStream;

public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (
                /**
                 * 自動關閉------程序底層會自動於finally塊調用close關閉
                 */
                FileOutputStream fos = new FileOutputStream("demo/fos.dat")

                /**
                 * 可以放於此塊的代碼對象，必須實現AutoCloseable接口
                 * OutputStream、InputStream、Writer、Reader這些流的超類都實現了Closeable接口
                 * 而Closeable接口又繼承了AutoCloseable接口
                 * 因此可知道所有的流一定都符合這個規定!
                 */
        ){
            fos.write(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
