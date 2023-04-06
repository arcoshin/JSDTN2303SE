package file;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * 方法引用(Lambda表達式擴展)
 *
 * 語法------
 * 類名::靜態方法名
 * 對象::實例方法名
 *
 * 當Lambda表達式中傳入的參數與調用的參數相同時才可以使用
 */
public class Test4 {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");

        /**
         * 重寫函數式接口遍歷
         */
        Consumer<String> consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                out.println(o);
            }
        };
        c.forEach(consumer);
//
//        /**
//         * Lambda簡寫
//         */
//        c.forEach(s -> System.out.println(s));//lambda表達式簡寫
//
//        /**
//         * 方法引用改寫
//         */
//        c.forEach(System.out::println);//方法引用
//
//        /**
//         * 搭配靜態導入改寫
//         */
//        c.forEach(out::println);//靜態導入+方法引用

    }
}
