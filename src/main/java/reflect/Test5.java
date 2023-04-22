package reflect;

import reflect.annotations.AutoRunClass;
import reflect.annotations.AutoRunMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 實例化當前類與Test5所在同一個包中
 * 被@AutoRunClass所標注的類中
 * 又被@AutoRunMethod所標注的參數次
 */
public class Test5 {
    public static void main(String[] args) throws Exception {
        //綁定類對象地址
        File baseFile = new File(Test5.class.getResource(".").toURI());

        //獲取包路徑
        String packageName = Test5.class.getPackage().getName();

        //遍歷類對象地址中的所有文件
        File[] files = baseFile.listFiles(f -> f.getName().endsWith(".class"));
        for (File file : files) {
            //獲取文件名
            String fileName = file.getName();
            //修改文件名
            fileName = fileName.substring(0, fileName.indexOf("."));
            //生成類加載對象
            Class cls = Class.forName(packageName + "." + fileName);
            //判定對象是否被AutoRunClass所標注
            if (cls.isAnnotationPresent(AutoRunClass.class)) {
                //實例化對象
                Object obj = cls.newInstance();
                //遍歷對象中所有方法
                Method[] methods = cls.getDeclaredMethods();
                for (Method method : methods) {
                    //判定方法是否被AutoRunMethod所標注
                    if (method.isAnnotationPresent(AutoRunMethod.class)) {
                        //查詢註解中所傳入的參數
                        AutoRunMethod arm = method.getAnnotation(AutoRunMethod.class);
                        int value = arm.value();
                        System.out.println("執行" + method.getName() + "方法" + value + "次");
                        for (int i = 0; i < value; i++) {
                            method.invoke(obj);
                        }

                    }
                }
            }
        }

    }

}
