package thread;

/**
 * 多線程使用接口的創建方式
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        /**
         * 此種聲明方式
         * 優點:不占用繼承，重用性較高，推薦使用
         */
        new Thread(new MyRunnable1()).start();
        new Thread(new MyRunnable2()).start();


    }
}


class MyRunnable1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("你是誰呀?");
        }
    }
}

class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("開門，查水表的!");
        }

    }
}
