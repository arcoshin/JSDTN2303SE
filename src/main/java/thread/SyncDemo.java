package thread;

/**
 * 多線程併發安全問題
 * 多個線程併發操作一個臨界資源時
 * 由於線程切換的時間不固定，因此有可能造成併發安全問題
 */
public class SyncDemo {
    public static void main(String[] args) {
        table table = new table();
        Thread t1 = new Thread("A") {
            @Override
            public void run() {
                while (true) {
                    int bean = table.getBean();
                    Thread.yield();
                    System.out.println(getName() + ":" + bean);
                }
            }
        };
        t1.start();

        Thread t2 = new Thread("B") {
            @Override
            public void run() {
                while (true) {
                    int bean = table.getBean();
                    Thread.yield();
                    System.out.println(getName() + ":" + bean);
                }
            }
        };
        t2.start();
    }
}

class table {
    private int bean = 20;//桌子上有20個豆子

    public synchronized int getBean() {
        if (bean == 0) {
            throw new RuntimeException("沒有豆子了");
        }
        /**
         * static yield() 該方法可以讓運行該方法線程主動放棄本次剩餘的時間片
         * 不加也會有併發安全問題!!!
         * 此處只是增加發生併發安全問題的機率，便於觀察與探討
         */
        Thread.yield();
        return bean--;
    }
}