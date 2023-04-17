package thread;

/**
 * 互斥鎖
 * 當使用多個synchronized鎖定多個代碼
 */
public class SyncDemo4 {
    public static void main(String[] args) {
        Boo boo = new Boo();
        /**
         * 識別對象相同，形成互斥
         * AB兩方法無法同時被執行
         */
        new Thread(boo::methodA, "1號線程").start();
        new Thread(boo::methodB, "2號線程").start();
    }
}

class Boo {

    public void methodA() {
        /**
         * 此處上鎖的對象是調用的當前對象因此仍然與方法B形成互斥
         */
        synchronized (this) {
            try {
                Thread t = Thread.currentThread();
                System.out.println(t.getName() + ":正在執行A方法......");
                Thread.sleep(5000);
                System.out.println(t.getName() + ":A方法執行完畢");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 調用此方法時加鎖的是調用的當前對象，故與方法A形成互斥
     */
    public synchronized void methodB() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ":正在執行B方法......");
            Thread.sleep(5000);
            System.out.println(t.getName() + ":B方法執行完畢");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
