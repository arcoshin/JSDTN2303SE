package reflect;

import java.util.Scanner;

/**
 * 透過反射來實現對象的實例化
 *
 * newInstance 默認調用反射對象的公開且無參的構造器
 */
public class ReflectDemo2 {
    public static void main(String[] args) throws Exception {
        //一般情況的實例化
        Person person = new Person();
        System.out.println(person);//Person@1b6d3586

        //透過反射的實例化:newInstance
        Class cls = Class.forName("reflect.Person");
        Object o = cls.newInstance();
        System.out.println(o);//Person@4554617c

        //透過Scanner於控制台實現動態實例化對象
        while (true) {
            System.out.println("請輸入類名......");
            String classLine = new Scanner(System.in).nextLine();
            Class classN = Class.forName(classLine);
            Object obj = classN.newInstance();
            System.out.println(obj);
        }

    }
}
