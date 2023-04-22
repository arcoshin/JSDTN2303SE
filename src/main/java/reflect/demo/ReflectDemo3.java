package reflect.demo;

import java.lang.reflect.Constructor;

/**
 * 操作特定构造器进行对象实例化
 */
public class ReflectDemo3 {
    public static void main(String[] args) throws Exception {
        /**
         * 綁定Person類
         */
        Class cls = Class.forName("reflect.demo.Person");

        //綁定無參構造器
        Constructor constructor1 = cls.getConstructor();

        //實例化
        Object o1 = constructor1.newInstance();
        System.out.println(o1);//Person{name='張三', age=18}

        //綁定一參構造器
        Constructor constructor2 = cls.getConstructor(String.class);
        Object o2 = constructor2.newInstance("李四");
        System.out.println(o2);//Person{name='李四', age=18}

        //綁定雙參構造器
        Constructor constructor3 = cls.getConstructor(String.class, int.class);
        Object o3 = constructor3.newInstance("王五", 99);
        System.out.println(o3);//Person{name='王五', age=99}
    }

}






