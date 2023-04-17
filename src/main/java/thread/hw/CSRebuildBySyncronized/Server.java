package thread.hw.CSRebuildBySyncronized;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 服務器端
 */
public class Server {
    private ServerSocket server;
    private List<PrintWriter> allOut = new ArrayList<>();

    /**
     * 構造器
     */
    public Server() {
        /**
         * 開啟服務器端口
         */
        try {
            System.out.println("正在開啟服務器......");
            server = new ServerSocket(9188);
            System.out.println("服務器已完成開啟!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服務器端業務
     */
    public void start() {
        try {
            /**
             * 不斷等待用戶連接
             */
            while (true) {
                System.out.println("正在等待用戶端連接......");
                Socket socket = server.accept();
                System.out.println("一個用戶端已連接!");

                /**
                 * 將socket傳入線程任務類執行任務，並啟動線程
                 */
                new Thread(new ClientHandler(socket)).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        new Server().start();
    }


    /**
     * 線程任務類
     */
    private class ClientHandler implements Runnable {
        /**
         * Socket實例
         */
        private Socket socket;

        /**
         * 用戶地址相關資訊
         */
        private String host;

        /**
         * 利用構造器傳參賦值成員變量
         */
        public ClientHandler(Socket socket) {
            this.socket = socket;
            host = socket.getInetAddress().getHostAddress();
        }

        /**
         * 作用域提出
         */
        PrintWriter pw;
        @Override
        public void run() {
            try {
                /**
                 * 建立輸入流:讀入是輸出在控制台
                 */
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream(), StandardCharsets.UTF_8
                                )
                        );
                /**
                 * 建立輸出流:寫出是廣播給所有用戶端 --->共享集合+廣播方法
                 */
                pw = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream(), StandardCharsets.UTF_8
                        )
                        , true);

                /**
                 * 創建完成後，加入共享集合(上線了)
                 */
                synchronized (allOut) {
                    allOut.add(pw);
                }

                /**
                 * 公告上線了
                 */
                sendMessage(host + "上線了，當前在線人數為" + allOut.size() + "人");

                /**
                 * 不斷偵測
                 * 一旦有訊息就立刻輸出在服務器控制台以及寫出給所有在線用戶
                 */
                String line;
                while ((line = br.readLine()) != null) {
                    if ("EXIT".equalsIgnoreCase(line)) {
                        break;
                    }
                    sendMessage(host + ">>>" + line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                /**
                 * 用戶下線相關手續
                 * finally塊不論程序是否報異常，必定會執行
                 */
                try {
                    /**
                     * 自共享集合移除
                     */
                    synchronized (allOut) {
                        allOut.remove(pw);
                    }

                    /**
                     * 關閉輸出與輸入流
                     */
                    pw.close();
                    socket.close();

                    /**
                     * 公告用戶已離線
                     */
                    sendMessage(host + "已經離線了，當前在線人數為" + allOut.size() + "人");
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

        /**
         * 廣播給所有在線用戶的方法
         */
        public void sendMessage(String message) {
            System.out.println(message);
            synchronized (allOut) {
                for (PrintWriter pw : allOut) {
                    pw.println(message);
                }
            }
        }
    }

}
