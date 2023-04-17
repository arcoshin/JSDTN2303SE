package thread;

/**
 * 多線程使用繼承的創建方式
 * extends Thread
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        /**
         * 此種聲明方式
         * 優點:可讀，整體簡單好理解
         * 缺點:占用繼承、重用性較低(new一個線程對象之後，執行體run中的動作是固定的)
         */
        new MyThread1().start();
        new MyThread2().start();
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("你是誰呀?");
        }

    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("開門，查水表的!");
        }

    }
}
