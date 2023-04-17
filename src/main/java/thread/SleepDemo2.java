package thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 線程提供了一個靜態方法
 * static void sleep(long ms)
 * 該方法可以讓執行該方法的線程處於阻塞狀態並指定毫秒，師兼到時會再次讓線程回到Runnable狀態，等待時間碎片
 */
public class SleepDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                /**
                 * 睡幾秒
                 */
                int sec = 10;
                for (; sec > 0; sec--) {
                    System.out.println("有人睡死zzz......");
                    Thread.sleep(1000 * sec);
                }

            } catch (InterruptedException e) {
                System.out.println("有人被叫醒了......");

            }
        });

        Thread t2 = new Thread(() -> {
            try {
                /**
                 * 幾秒的鬧鐘
                 */
                int alarmSec = 5;
                for (; 0 < alarmSec; alarmSec--) {
                    System.out.println("剩餘" + alarmSec + "秒鬧鐘:......");
                    Thread.sleep(1000);
                }
                System.out.println("鬧鐘響了!!!!!!");
                t1.interrupt();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.setDaemon(true);

        t1.start();
        t2.start();

        /**
         * sleep方法可以做到精準的秒數延遲嗎? 不能!!
         * sleep可以依照毫秒數確實遲滯一定時間
         * 但遲滯完畢後，線程是回到"Runnable"狀態而非"Running"狀態
         * 此時等待CPU時間碎片的時間即為多出來的誤差時間
         * 因此是不能做到絕對精確的誤差時間的
         */


    }
}
