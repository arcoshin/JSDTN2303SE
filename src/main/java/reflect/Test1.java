package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 需求:
 * 使用反射機制調用Person中所有公開的無參方法
 *
 * 提示:
 * int getParameterCount()可以獲得調用的方法對象中的參數個數
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        //獲取類對象並實例化
        Class cls = Class.forName("reflect.Person");
        Object obj = cls.newInstance();

        //獲取Person中定義的所有的公開方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {//遍歷每個方法
            if (
                    method.getParameterCount() == 0 //如果方法的參數個數為0
                            && method.getModifiers() == Modifier.PUBLIC
            ) {//且方法為公開
                method.invoke(obj);//調用方法
            }
        }
    }
}






