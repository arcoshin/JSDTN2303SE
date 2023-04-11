package exception;

/**
 * 自定義異常 : 非法年齡異常
 *
 * 1.類名要見名知義
 * 2.繼承Exception(直接或間接)
 * 3.提供超類異常所提供的所有構造器
 *
 * @author XCX
 */
public class IllegalAgeException extends Exception {
    public IllegalAgeException() {
    }

    public IllegalAgeException(String message) {
        super(message);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }

    public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
