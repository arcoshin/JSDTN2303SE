package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室服務器端
 */
public class ServerV3 {
    /**
     * java.net.ServerSocket
     * 運行在服務器端的ServerSocket主要有兩個工作
     * 1.打開服務器端口，客戶端就是透過這個端口與服務器建立連結的
     * 2.監聽服務器端口，一旦用戶端連接，則立即返回一個Socket實例
     */
    private ServerSocket serverSocket;
    /**
     * 存放所有用戶端的輸出劉，用以廣播數據
     */
    private List<PrintWriter> allOut = new ArrayList();


    public ServerV3() {
        try {
            /**
             * 啟動服務器
             * 端口號範圍0~65535
             */
            System.out.println("正在開啟服務器......");
            serverSocket = new ServerSocket(9188);
            System.out.println("服務器開啟完成!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服務器開始工作的方法
     */
    public void start() {
        /**
         * 接受客戶連接的方法
         */
        try {
            while (true) {
                System.out.println("正在等待用戶連接......");
                Socket socket = serverSocket.accept();
                System.out.println("一個用戶端已連接!");
                /**
                 * 當accept方法接收到一個客戶端連接時，我們就調用線程任務ClientHandler
                 * 使用一個線程調用該線程任務類並啟動，等待CPU分發碎片
                 * 此處藉由ClientHandler的構造器將用戶的socket訊息傳入方法
                 */
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        ServerV3 server = new ServerV3();
        server.start();
    }

    /**
     * 定義一個線程任務內部類
     * 這個線程任務寄售讓一個線程與指定的客戶端進行交互
     */
    private class ClientHandler implements Runnable {
        /**
         * 定義一個socket實例(此時為空白實例)
         */
        private Socket socket;
        private String host;//用戶端的地址訊息

        public ClientHandler(Socket socket) {
            /**
             * 藉由構造器賦值成員變量，而成員變量可被全局訪問
             */
            this.socket = socket;
            /**
             * InetAddress socket.getInetAddress()獲取遠端計算機位址
             * 再透過 String.getHostAddress 獲取位址訊息
             */
            host = socket.getInetAddress().getHostAddress();
        }

        @Override
        public void run() {
            /**
             * 因作用域問題而外提
             */
            PrintWriter pw = null;

            try {
                /**
                 * 顯示所有使用者輸入的數據
                 */
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(
                                        /**
                                         * 此時socket即為serverSocket.accept()所傳入的socket實例
                                         */
                                        socket.getInputStream(), StandardCharsets.UTF_8)
                        );

                /**
                 * 將數據寫出給所有客戶端(也就是讓客戶之間交流)
                 */
                pw = new PrintWriter(
                        new OutputStreamWriter(
                                /**
                                 * 此時socket即為serverSocket.accept()所傳入的socket實例
                                 */
                                socket.getOutputStream(), StandardCharsets.UTF_8
                        )
                        , true);//開啟自動刷新

                /**
                 * 將當前用戶端的輸出流存入共享集合allOut中
                 */
                synchronized (allOut) {
                    allOut.add(pw);
                }

                /**
                 * 通知所有用戶端有使用者上線了
                 */
                sendMessage(host + "上線了，當前在線人數" + allOut.size() + "人");


                /**
                 * 當讀取到的數據非空時，於服務器&&所有客戶端寫出數據
                 */
                String line;
                while ((line = br.readLine()) != null) {
                    //1.於服務器顯示
                    System.out.println(host + ">>>" + line);

                    //2.廣播給連線中的所有客戶端
                    sendMessage(line);
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                /**
                 * 下線等相關業務應放在此finally塊
                 * 不論是否異常離線，保證都能執行此步驟
                 */

                /**
                 * 用戶端下線，輸出流應剔除
                 */
                synchronized (allOut) {
                    allOut.remove(pw);
                }

                /**
                 * 剔除後公告玩家以下線
                 */
                sendMessage(host + "已下線，當前在線人數為" + allOut.size() + "人");

                try {
                    /**
                     * 關閉socket流
                     */
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 廣播的方法
         */
        public void sendMessage(String message) {
            System.out.println(message);
            /**
             * 搶誰鎖誰
             */
            synchronized (allOut) {
                for (PrintWriter p : allOut) {
                    p.println(host + ">>>" + message);
                }
            }
        }
    }

}
