package thread;

/**
 * 靜態方法鎖
 * 當一個靜態方法使用synchronized，則該方法一定具有效果:靜態資源在類中就是只有一份!
 * 永遠都會有效果因此不適合!!
 * 合適的監視對象，應於應該同步時有效果，不該同步時失去效果，應有區別
 * <p>
 * 所以應避免使用如靜態資源、字符串等作為監視對象，因為一定會有效果
 * 也應避免使用如new關鍵字的對象，因為一定沒有效果
 * this則是不錯的推薦對象，但依然不是每個場景都適用!
 */
public class SyncDemo3 {
    public static void main(String[] args) {


    }
}

class Foo {
    public static void doSome() {
        /**
         * 靜態方法中使用同步塊時，同步監視器對象還是使用當前類的類對象
         * 獲取對象的方式: 類明.class
         * 例如獲取Foo的類對象就是: Foo.class
         */
        synchronized (Foo.class) {//使用Foo這個類作為對象
            try {
                Thread t = Thread.currentThread();
                System.out.println(t.getName() + "正在執行doSome方法");
                Thread.sleep(5000);
                System.out.println(t.getName() + "doSome方法執行完畢");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
