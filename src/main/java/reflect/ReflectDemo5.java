package reflect;

import java.lang.reflect.Method;

/**
 * 透過反射機制調用有參方法
 */
public class ReflectDemo5 {
    public static void main(String[] args) throws Exception {
        //綁定類對象並實例化一個對象
        Class cls = Class.forName("reflect.Person");
        Object obj = cls.newInstance();

        //獲取有參方法並執行
        //say(String info)
        Method m1 = cls.getMethod("say", String.class);
        m1.invoke(obj, "早安你好");

        //當方法含雙參時
        //say(String info,int count)
        Method m2 = cls.getMethod("say", String.class, int.class);
        m2.invoke(obj, "嘿嘿嘿",3);


    }
}






