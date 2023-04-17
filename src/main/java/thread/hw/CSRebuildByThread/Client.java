package thread.hw.CSRebuildByThread;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class Client {
    private Socket socket;

    public Client() {
        try {
            System.out.println("正在連接服務器......");
            socket = new Socket("localHost",9188);
            System.out.println("已連接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        try {
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(
//                            socket.getInputStream(), StandardCharsets.UTF_8
//                    )
//            );

            PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream(), StandardCharsets.UTF_8
                    )
            ,true);

            while (true) {
                String line = new Scanner(System.in).nextLine();
                if ("EXIT".equalsIgnoreCase(line)) {
                    return;
                }
                pw.println(line);
                System.err.println(".....OK");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
