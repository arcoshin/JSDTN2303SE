package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 聊天室服務器端
 */
public class ServerV2 {
    /**
     * java.net.ServerSocket
     * 運行在服務器端的ServerSocket主要有兩個工作
     * 1.打開服務器端口，客戶端就是透過這個端口與服務器建立連結的
     * 2.
     */
    private ServerSocket serverSocket;
    private String host;

    public ServerV2() {
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
        ServerV2 server = new ServerV2();
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
            try {
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(
                                        /**
                                         * 此時socket即為serverSocket.accept()所傳入的socket實例
                                         */
                                        socket.getInputStream(), StandardCharsets.UTF_8)
                        );

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(host + ">>>" + line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
