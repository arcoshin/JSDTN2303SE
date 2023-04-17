package thread;

/**
 * 獲取線程相關訊息
 */
public class ThreadInfoDemo {
    public static void main(String[] args) {
        Thread main = Thread.currentThread();//獲取主線程
        /**
         * ID:唯一標識
         * 特點是非空且唯一
         */
        long id = main.getId();
        System.out.println("當前線程的ID : " + id);

        /**
         * 獲取線程的名字
         */
        String name = main.getName();
        System.out.println("當前線程的名稱 : " + name);

        /**
         * 獲取線程優先級(1~10)
         */
        int priority = main.getPriority();
        System.out.println("當前線程的優先級 : " + priority);

        /**
         * 線程是否存活
         */
        boolean alive = main.isAlive();
        System.out.println("當前線程是否存活 : " + alive);

        /**
         * 線程是否為守護者線程
         */
        boolean daemon = main.isDaemon();
        System.out.println("當前線程是否為守護線程 : " + daemon);

        /**
         * 線程是否被中斷
         */
        boolean interrupted = main.isInterrupted();
        System.out.println("當前線程是否被中斷 : " + interrupted);


    }
}
