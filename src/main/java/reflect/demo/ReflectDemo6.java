package reflect.demo;

import java.lang.reflect.Method;

/**
 * 透過反射機制訪問私有成員
 */
public class ReflectDemo6 {
    public static void main(String[] args) throws Exception {
        //綁定類對象並實例化
        Class cls = Class.forName("reflect.demo.Person");
        Object obj = cls.newInstance();

        //獲取所有公開即從超類繼承而來的方法
//        Method m1 = cls.getMethod("hey");
//        m1.invoke(obj);//NoSuchMethodException : 沒有這個類的異常(調用getMethod()看不見)

        //獲取本類中定義的所有方法的方法(包含私有)
        Method m2 = cls.getDeclaredMethod("hey");
//        m2.invoke(obj);//IllegalAccessException : 非法訪問許可異常(沒有權限調用私有方法)

        m2.setAccessible(true);//打開權限
        m2.invoke(obj);
        m2.setAccessible(false);//使用完畢記得關閉，以維護程序的封裝性

    }
}
