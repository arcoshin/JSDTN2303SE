package socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 聊天室客戶端
 */

public class Client {
    /**
     * java.net.Socket:套接字(原意插座)
     * Socket封裝了TCP協議的通訊細節，使用他就可以與遠端計算機進行TCP連接
     * 基於兩條流(輸入與輸出)與遠端計算機通訊
     * Socket可以想像為我們的手機
     */
    private Socket socket;

    /**
     * 構造器，用來初始化客戶端
     */
    public Client() {
        try {
            /**
             * 實例化Socket時需要傳入兩個參數用於與遠端計算機建立連接
             * 實例化的過程舊式與遠端計算機建立連接的過程，如果失敗就會拋出異常
             * 參數1:遠端計算機的IP位址
             * 參數2:遠端計算機上的服務端程序開啟的服務端口
             */
            System.out.println("正在連接服務器端......");
            /**
             * Windows
             * 查詢IP
             * CMD>config>IPv4
             *
             * Mac
             * 終端程序
             * /sbin/ifconfig
             */
//            socket = new Socket("192.168.0.17", 9188);
            socket = new Socket("localhost", 9188);
            System.out.println("連接成功......!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客戶端開始工作的方法
     */
    public void start() {
        try {
            PrintWriter pw =
                    new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            socket.getOutputStream(), StandardCharsets.UTF_8)
                            ), true
                    );
            System.out.println("請輸入訊息，輸入exit可退出");

            while (true) {
                String line = new Scanner(System.in).nextLine();
                if ("EXIT".equalsIgnoreCase(line)) {
                    break;
                } else {
                    pw.println(line);
                    System.err.println("......push ok!");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                //close方法內部會進行TCP的四次揮手操作
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

}
