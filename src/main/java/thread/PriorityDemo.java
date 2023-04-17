package thread;

/**
 * 線程的優先級(1~10)
 *
 * 當一個線程調用start()方法後，該線程就會納入線程調度器中統一管理
 * 此時線程只能等待被分配到時間片，而不能主動索取時間片
 * 而線程的優先級可以最大程度的改變獲取時間片的概率
 * 優先級越高的線程可以獲得越高的機率獲取時間片
 */
public class PriorityDemo {
    public static void main(String[] args) {
        /**
         * NORM_PRIORITY
         */
        Thread mid = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("MID");
            }
            System.out.println("MID Finish........!");

        });
        mid.setPriority(Thread.NORM_PRIORITY);//默認優先級
        mid.start();

        /**
         * MAX_PRIORITY
         */
        Thread max = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("MAX");
            }
            System.out.println("MAX Finish........!");

        });
        max.setPriority(Thread.MAX_PRIORITY);//最高優先級
        max.start();

        /**
         * MIN_PRIORITY
         */
        Thread min = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("MIN");
            }
            System.out.println("MIN Finish........!");

        });
        min.setPriority(Thread.MIN_PRIORITY);//最低優先級
        min.start();
    }
}
