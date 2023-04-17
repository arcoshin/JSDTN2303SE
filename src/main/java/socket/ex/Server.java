package socket.ex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 聊天室的服務端
 */
class Server {
    /**
     * java.net.ServerSocket
     * 是運行在服務器端的，主要工作是
     * 1.打開服務器程序中的端口(規定端口號)
     * 2.用以監聽該服務器端口，用戶訪問端口時會自動生成實例，並與客戶交互
     * 可簡單理解ServerSocket為一台總機
     */
    private ServerSocket server;
    private PrintWriter[] allOut = {};//該數組用於儲存所有的輸出流，用於廣播訊息給所有用戶端

    public Server() {

        /**
         * 實例ServerSocket的同時需要指定要打開的端口
         * 當端口號已被其他程序占用時，再次占用則報錯JVM_BIND
         * 不建議隨意刪除端口，因不確定程序內容，確認內容也很繁瑣
         * 此時建議直接更換Server端口，同時Client也須同步
         */
        try {
            System.out.println("正在啟動服務器端");
            server = new ServerSocket(9188);
            System.out.println("服務器端啟動完畢");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        try {
            while (true) {
                System.out.println("等待客戶端連接中...");
                /**
                 * ServerSocket提供的Socket accept方法
                 * 該方法是一種阻塞方法，程序執行該方法時會立即等待
                 * 當一客戶端訪問服務器時，accept方法就能監聽到，然後
                 * 返回一個socket實例
                 * 然後程序才會接著向下運行
                 */
                Socket socket = server.accept();
                System.out.println("有一個客戶端連接了!");
                /**
                 * 啟動一線程來處理當前客戶的交互
                 * 聯通並連接當前客戶端的socket
                 * 並一起傳參給ClientHandler
                 * (使用有參構造方法來傳參)
                 */
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread t = new Thread(clientHandler);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /**
     * 創建一個內部類，是線程的任務類，與特定客戶端進行交流
     */
    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            PrintWriter pw = null;
            try {
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                pw = new PrintWriter(//為了全域調用故提出方法外聲明
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream(), StandardCharsets.UTF_8
                                )
                        ), true);

                /**
                 * 每個線程對客戶端創建輸出流後，需要儲存到allOut中備用
                 * 數組特性，一旦創建長度不可改變
                 * 故先擴容再儲存
                 */
                synchronized (Server.this) {//不可使用this(當前線程調用者為 run > ClientHandler > Thread 都是自己)
                    //大多數情況下，多個數搶奪的資源可以當作監視器對象(但copyOf方法相當於new)
                    //可以使用Server類本體或者serve，全局不變。
                    allOut = Arrays.copyOf(allOut, allOut.length + 1);//先擴容
                    allOut[allOut.length - 1] = pw;//賦值於新增的位置，也意味著新增一位客戶端上線了
                } sendMessage("有一個客戶端上線了!當前在線人數:" + allOut.length);//調用方法廣播


                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
//                    pw.println(line);
                    sendMessage(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {//一定是對應客戶端下線了
                //從allOut數組中將輸出流取出
                synchronized (Server.this) {
                    for (int i = 0; i < allOut.length; i++) {
                        //將本次下線的對應客戶端的輸出流從數組中找到
                        if (allOut[i] == pw) {
                            allOut[i] = allOut[allOut.length - 1];
                            allOut = Arrays.copyOf(allOut, allOut.length - 1);
                            break;//比對完就可以跳出循環了
                        }
                    }
                }
                sendMessage("有一個用戶下線了!當前線上人數為:" + allOut.length);

                try {
                    socket.close();//服務器端也關流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 廣播給所有客戶的方法
         */
        private void sendMessage(String message) {
            synchronized (Server.this) {
                for (int i = 0; i < allOut.length; i++) {
                    allOut[i].println(message);
                }
            }
        }
    }
}
