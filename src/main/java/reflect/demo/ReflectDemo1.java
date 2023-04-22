package reflect.demo;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 反射機制
 */
public class ReflectDemo1 {
    public static void main(String[] args) {
//        /**
//         * 反射機制的第一步:獲取待操作的類的類對象
//         *
//         * 第一種方式------類名.class
//         * 例如: String.class  int.class(基本類型只適用這種方式)
//         *
//         * 第二種方式------Class.forName(類的完全限定名)
//         * 例如: class.forName("java.lang.String")
//         *
//         * 第三種方式
//         * class.get
//         */
//
//        Class cls = String.class;
//        String name = cls.getName();//獲得類的完全限定名
//        System.out.println("類全名 = " + name);
//        name = cls.getSimpleName();//獲得純類名
//        System.out.println("純類名 = " + name);
//
//        /**
//         * Package getPackageName()
//         * 透過類對象獲取該類所在的包訊息
//         *
//         * Class         的實例用來表示一個類的訊息
//         * Package       的實例用來表示一個包的訊息
//         * Constructor   的實例用來表示一個構造器的訊息
//         * Method        的實例用來表示一個方法的訊息
//         */
//        String packageName = cls.getPackage().getName();
//        System.out.println("packageName = " + packageName);//獲取該類所在的包的包名
//
//        Method[] methods = cls.getMethods();//獲取所有公開方法
//        System.out.println("透過反射可以知道String的公開方法有......");
//        for (Method method : methods) {
//            System.out.print("方法名 = " + method.getName());
//            System.out.print(",參數類型 = " + Arrays.toString(method.getParameterTypes()));
//            System.out.print(",參數個數 = " + method.getParameterCount());
//            System.out.print(",返回值類型 = " + method.getReturnType().getSimpleName());
//            System.out.println();
//        }
//
//        //獲取ArrayList的類對象
//        try {
//            cls = Class.forName("java.util.ArrayList");
//            System.out.println("完全限定名 = " + cls.getName());//完全限定名
//            System.out.println("純類名 = " + cls.getSimpleName());//純類名
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        //利用Scanner於控制台"動態生成"類對象
        try {
            System.out.println("請輸入類名");
            String className = new Scanner(System.in).nextLine();
            Class classReflect = Class.forName(className);

            Method[] methods = classReflect.getMethods();
            for (Method method : methods) {
                System.out.println("方法名 = " + method.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
