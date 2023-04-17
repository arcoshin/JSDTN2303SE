package thread;

/**
 * 多線程使用Lambda的創建方式
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("t1執行體");
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("t2執行體");
            }
        });
        t2.start();

    }
}
