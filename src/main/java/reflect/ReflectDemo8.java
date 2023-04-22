package reflect;

import reflect.annotations.AutoRunMethod;
import java.lang.reflect.Method;

/**
 * 反射機制訪問註解
 * 判斷一個方法是否被某個註解標住了
 */
public class ReflectDemo8 {
    public static void main(String[] args) throws Exception {
        //需求：查看Person類的sayHi方法是否被@AutoRunMethod註解了
        Class cls = Class.forName("reflect.Person");

        Method method = cls.getDeclaredMethod("sayHi");
        boolean mark = method.isAnnotationPresent(AutoRunMethod.class);
        System.out.println(method.getName() + "是否被AutoRunMethod標注了 : " + mark );
    }
}
