package reflect;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 透過反射機制調用方法
 */
public class ReflectDemo4 {
    public static void main(String[] args) throws Exception {
        //普通調用
//        Person p = new Person();
//        p.playGame();

        /**
         * 透過反射機制調用方法
         */

//        //綁定類對象
//        Class cls = Class.forName("reflect.Person");
//        Object obj = cls.newInstance();//默認使用無參且公開的構造器生成對象
//
//        //透過反射機制綁定方法
//        Method method = cls.getMethod("playGame");
//
//        //反射機制中的Method類自帶invoke(對象)方法，可以直接調用
//        method.invoke(p);//使用普通對象
//        method.invoke(obj);//也可以直接使用在反射機制中實例化的對象(雖為Object類型，invoke會自行識別並強轉)

        /**
         * 擴展成動態機制，於程序執行時再指定對象及方法並執行
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入類名");
        String className = scanner.nextLine();
        System.out.println("請輸入方法名");
        String methodName = scanner.nextLine();


        //綁定類對象
        Class cls = Class.forName(className);
        Object obj = cls.newInstance();//默認使用無參且公開的構造器生成對象

        //綁定方法
        Method method = cls.getMethod(methodName);

        //調用方法
        method.invoke(obj);

    }
}






