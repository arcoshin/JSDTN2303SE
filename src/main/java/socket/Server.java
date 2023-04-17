package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 聊天室服務器端
 */
public class Server {
    /**
     * java.net.ServerSocket
     * 運行在服務器端的ServerSocket主要有兩個工作
     * 1.打開服務器端口，客戶端就是透過這個端口與服務器建立連結的
     * 2.
     */
    private ServerSocket serverSocket;

    public Server() {
        try {

            System.out.println("正在開啟服務器......");
            /**
             * 端口號範圍0~65535
             */
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

                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream(), StandardCharsets.UTF_8)
                        );

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

}
