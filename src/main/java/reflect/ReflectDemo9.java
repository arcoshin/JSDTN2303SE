package reflect;

import reflect.annotations.AutoRunMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectDemo9 {
    public static void main(String[] args) throws Exception {
        //需求:獲取Person類中的方法sayHi()被註解@AutoRunMethod標注時的參數
        Class cls = Class.forName("reflect.Person");
        Method method = cls.getMethod("sayHi");
        if (method.isAnnotationPresent(AutoRunMethod.class)){
            /**
             * 所有反射對象都支持獲取註解的方法
             * getAnnotation(Class cls)
             */
            AutoRunMethod arm = method.getAnnotation(AutoRunMethod.class);
            int value = arm.value();
            System.out.println(value);
        }


    }
}
