package thread;

/**
 * 守護線程類
 * <p>
 * 守護線程與普通的用戶線程區別在於進程結束時
 * 當一個JAVA進程中所有的用戶線程結束時，進程就會結束
 * 而此時會殺死強行所有還在運行的守護線程
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Rose : Let me go!!");
                    Thread.sleep(1000);
                }
                System.out.println("r......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Jack : U Jump I Jump!!");
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        /**
         * 設置守護線程之前，必須在啟用線程之前
         */
        t2.setDaemon(true);
        t2.start();
//        while (true);//卡死主線程，永遠不會結束
        /**
         * 此處可發現Rose喊完之後Jack卻不被中斷了
         * 因為此處main方法本身也是一條線程，而這個主線程還沒執行完畢
         * 所以進程還不回結束，也因此守護線程都會繼續運行
         */


    }
}
