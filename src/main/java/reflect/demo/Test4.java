package reflect.demo;

import reflect.annotations.AutoRunClass;
import reflect.annotations.AutoRunMethod;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 實例化當前類與Test4所在同一個包中
 * 被@AutoRunClass所標注的類中
 * 又被@AutoRunMethod所標注的方法
 */
public class Test4 {
    public static void main(String[] args) throws Exception {
        //定位當前類加載對象
        File baseFile = new File(
                Test4.class.getResource(".").toURI()
        );

        //獲取包名的完整路徑
        String packageName = Test4.class.getPackage().getName();

        //遍歷所在包的所有字節碼文件
        File[] files = baseFile.listFiles(f -> f.getName().endsWith(".class"));
        for (File file : files) {
            //獲取類名:xxxxx.class
            String fileName = file.getName();
            //修改類名:xxxxx.class -> xxxxx
            fileName = fileName.substring(0,fileName.indexOf("."));
            //綁定類對象
            Class cls = Class.forName(packageName + "." + fileName);
            //判別類是否被AutoRunClass所標注
            if (cls.isAnnotationPresent(AutoRunClass.class)) {//如果類被標注
                //實例化
                Object obj = cls.newInstance();
                //遍歷該類自定義的所有方法
                Method[] methods = cls.getDeclaredMethods();
                for (Method method : methods) {
                    //判別是否被AutoRunMethod所標注
                    if (method.isAnnotationPresent(AutoRunMethod.class)){//如果方法被標注
                        //調用方法
                        method.invoke(obj);
                    }
                }
            }
        }

    }

}
