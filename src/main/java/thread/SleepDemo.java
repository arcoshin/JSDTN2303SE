package thread;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 線程提供了一個靜態方法
 * static void sleep(long ms)
 * 該方法可以讓執行該方法的線程處於阻塞狀態並指定毫秒，師兼到時會再次讓線程回到Runnable狀態，等待時間碎片
 */
public class SleepDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了......");

        try {
            /**
             * 簡易倒數計時程序
             * 於中控台輸入一整數，從該整數開始每秒遞減，直至歸零
             */
            System.out.println("請輸入秒數");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String num = br.readLine();
            int count = Integer.parseInt(num);
            for (; count > 0; count--) {
                System.out.println("倒數" + (count) + "秒");
                Thread.sleep(1000);
                if (count == 0) {
                    System.out.println("時間到!");
                }
            }
        } catch (Exception e) {
            System.err.println("輸入錯誤，請重新輸入");
        }

        System.out.println("程序結束了......!");

    }
}
