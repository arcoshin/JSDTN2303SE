package thread.hw.CSRebuildByThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class Server {
    /**
     * 獲取客戶端名稱
     */
    private ServerSocket serverSocket;

    public Server() {
        try {
            /**
             * 啟動服務器
             */
            System.out.println("正在啟動服務器......");
            serverSocket = new ServerSocket(9188);
            System.out.println("服務器已開啟");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服務器業務
     */
    public void start() {
        try {
            /**
             * 不斷等待用戶端連接
             */
            while (true) {
                /**
                 * 服務器ServerSocket自帶accept方法，用以接收用戶端連接
                 * 並生成一socket實例用以專門與之交互
                 */
                System.out.println("正在等待用戶連接...");
                Socket socket = serverSocket.accept();
                System.out.println("一個用戶已連接");

                /**
                 * 生成的socket直接傳入ClientHandler執行與每個客戶端的線程任務
                 */
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();//啟動線程
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /**
     * 線程任務類，專責與客戶交互
     */
    public class ClientHandler implements Runnable {
        /**
         * 聲明一空白socket實例變量，全類可用
         */
        private Socket socket;
        private String host;


        /**
         * 線程任務類構造器，順便將服務器產生用來與客戶交互的socket實例傳入
         */
        public ClientHandler(Socket socket) {
            this.socket = socket;

            /**
             * 取得用戶名
             */
            host = socket.getInetAddress().getHostAddress();

        }

        @Override
        public void run() {
            /**
             * 建立流連接
             */
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(), StandardCharsets.UTF_8
                        )
                );



                /**
                 * 輸出用戶所言
                 */
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(host + ">>>" + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
