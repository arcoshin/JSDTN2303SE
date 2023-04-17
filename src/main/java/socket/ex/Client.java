package socket.ex;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 聊天室的客戶端
 */
class Client {
    /**
     * 初始化客戶端
     * java.net.Socket套接字，原意表插座
     * 封裝了基於TCP協議的通訊細節
     * 使得我們可以簡單利用Socket完成與服務器複雜的連結
     * 可以形象比喻Socket就是電話:
     * 先撥號->通過話筒說話->通過聽筒接受訊息
     */
    private Socket socket;
    private static String name;

    public Client() {
        System.out.println("正在連接服務器...");
        /**
         * 實例化一個Socket需要傳入兩個參數
         * 參數一: 遠端計算機IP地址，當前項目服務器也在本機
         * 代表本機的方式有兩種 : 127.0.0.1 或者 localhost
         *
         * 參數二: 遠端計算器中服務器程序中占用的端口號
         * 依靠這兩個參數實現與遠端計算機建立連結
         * 通常0-1023默認被系統占用
         */
        try {
            socket = new Socket("localhost", 9188);//服務器也在本機
            System.out.println("已完成連接主機端");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 客戶端開始工作的方法
     */
    public void start() {
        try {
            //啟動線程讀取服務器端發送的訊息
            ServerHandler serverHandler = new ServerHandler();
            Thread t = new Thread(serverHandler);
            t.setDaemon(true);
            /**
             * 當前線程的任務是接收服務器的內容，當客戶輸入exit時
             * main的任務就結束了，但當前線程沒有結束
             * 故設置成守護線程
             */
            t.start();

            OutputStream out = socket.getOutputStream();
            //創建輸出轉換流
            OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            //創建緩衝字符流
            BufferedWriter bw = new BufferedWriter(osw);
            //創建按行輸出字符流，並開啟自動行刷新
            PrintWriter pw = new PrintWriter(bw, true);


            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("請輸入訊息內容");
                String line = scanner.nextLine();
                if ("exit".equals(line)) {
                    System.out.println("客戶端已退出");
                    break;
                }
                pw.println(name + ">>>" + line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 當客戶端退出時，必須對服務器示意
             * 否則服務器會報錯異常，並且客戶端也需關流
             * 即便以上措施仍無法百分之百解決服務器報錯問題
             * 如客戶端強制退出，我們無法掌控客戶端的操作
             */
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * main方法是程序的主入口
     * 因為程序啟動時，JVM會從main方法開始
     * 所以main方法中要盡可能乾淨
     */
    public static void main(String[] args) {
        System.out.println("請輸入用戶名稱");
        name = new Scanner(System.in).nextLine();
        Client client = new Client();
        client.start();
    }

    /**
     * 線程任務類，負責讀取服務器端發送的消息
     */
    private class ServerHandler implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(), StandardCharsets.UTF_8
                        )
                );
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {//客戶端非預測退出時產生的異常，可刪除即可不打印
                //e.printStackTrace();
            }
        }
    }
}
