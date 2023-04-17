package thread.hw.CSRebuildBySyncronized;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 用戶端
 */
public class Client {
    private Socket socket;

    public Client() {
        try {
            System.out.println("正在連接服務器......");
            socket = new Socket("localHost", 9188);
            System.out.println("服務器連接已完成");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {


        try {
            /**
             * 另建一線程任務類執行寫出任務
             */
            Thread t = new Thread(new ServerHandler(socket));
            //設置為守護者線程
            t.setDaemon(true);
            t.start();

            PrintWriter pw =
                    new PrintWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream(), StandardCharsets.UTF_8
                            ), true);

            System.out.println("請輸入訊息");
            while (true) {
                String line = new Scanner(System.in).nextLine();
                if ("EXIT".equalsIgnoreCase(line)){
                    break;
                }
                pw.println(line);
                System.err.println("......push OK!");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }

    private class ServerHandler implements Runnable {
        private Socket socket;

        public ServerHandler(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {
            try {
                /**
                 * 主線程負責讀入數據
                 */
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream(), StandardCharsets.UTF_8
                                )
                        );

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
