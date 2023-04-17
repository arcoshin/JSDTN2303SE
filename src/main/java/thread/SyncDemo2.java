package thread;

/**
 * 多線程併發安全問題
 * 多個線程併發操作一個臨界資源時
 * 由於線程切換的時間不固定，因此有可能造成併發安全問題
 * <p>
 * 臨界資源可以於方法名使用synchronized修飾，
 * 使的這個方法一次只有一個線程可以訪問
 * 可以有效解決線程的併發安全問題
 * <p>
 * 但是被修飾的代碼範圍應該越精準越小越好
 * 否則會造成程序整體效率大幅下降
 * (如方法完全上鎖，就是兩倍執行時間 ------> 一個線程方法完全執行完畢才會換下一個再次執行)
 * <p>
 * 我們可以使用synchronized修飾其中的幾句代碼，可以大幅提高效率
 * 但要注意，synchronized本身並非公平鎖
 * JAVA當中另外有公平鎖API------>AQS以後學習
 */
public class SyncDemo2 {
    public static void main(String[] args) {
        Shop shop = new Shop();

        /**
         * 最土的寫法
         */
        //聲明一線程任務匿名內部類
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                shop.buy();

            }
        };

        //使用lambda表達式
        Runnable r2 = () -> shop.buy();

        //聲明一線程執行該線程任務類
        Thread t1 = new Thread(r2,"threadName");
//        t1.start();//啟動線程

        /**
         * 使用匿名內部類替換
         */
        //使用匿名內部類替換線程任務r
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                shop.buy();
            }
        },"threadName");
//        t2.start();//啟動線程

        /**
         * 使用Lambda表達式再次改寫
         */
        //使用lambda表達式上述的線程任務匿名內部類
        Thread t3 = new Thread(()->shop.buy(),"threadName");
//        t3.start();//啟動線程

        /**
         * 省略聲明線程的操作直接啟動
         */
        new Thread(()->shop.buy(),"threadName").start();

        /**
         * 導入方法引用改寫
         * 當傳入的參數與輸出的參數相同可以使用方法引用
         * 對象::方法
         *
         * 由於lambda表達的run方法返回值為void，參數列表為無參
         * 而run中調用的方法buy方法返回值也為void，參數列表也是無參
         * 因此可以理解為:
         * lambda表達式表達的run方法其返回值與參數列表與內部調用shop.buy方法一致
         * 因此()->shop.buy 就可以寫成 shop::buy
         */
        new Thread(shop::buy,"A").start();
        new Thread(shop::buy,"B").start();

    }
}

class Shop {
    public void buy() {
        try {
            Thread t_name = Thread.currentThread();
            System.out.println(t_name.getName() + "正在挑衣服.....");
            Thread.sleep(5000);

            synchronized (Shop.this) {
                System.out.println(t_name.getName() + "正在試衣服.....");
                Thread.sleep(5000);
            }

            System.out.println(t_name.getName() + "結帳離開");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}