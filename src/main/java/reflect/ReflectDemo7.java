package reflect;

import reflect.annotations.AutoRunClass;

/**
 * 反射機制訪問註解
 * 判斷一個類是否被某個註解標住了
 */
public class ReflectDemo7 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("reflect.Person");

        boolean mark = cls.isAnnotationPresent(AutoRunClass.class);
        System.out.println("Person是否被@AutoRunClass所標注 : " + mark);
    }
}
