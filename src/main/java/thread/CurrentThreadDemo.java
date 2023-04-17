package thread;

/**
 * static currentThread獲取當前線程調用者的方法
 */
public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        System.out.println(main);
        /**
         * main中調用方法
         */
        domeSome();

        /**
         * 匿名內部類調用方法
         */
        new Thread(() -> domeSome()).start();
    }


    public static void domeSome() {
        Thread t = Thread.currentThread();
        System.out.println("當前調用domeSome方法的線程對象是" + t);
    }
}
