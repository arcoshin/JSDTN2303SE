package reflect;

import reflect.annotations.AutoRunClass;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 調前與當前類Test2在同一個包中所有類的所有公開的無參方法
 */
@AutoRunClass
public class Test2 {
    public static void main(String[] args) throws Exception {
        /**
         * 定位Test2所在包路徑
         */
        File baseFile = new File(
                Test2.class.getClassLoader().getResource(".").toURI()
                //C:\Users\User\IdeaProjects\JSD2303SE\target\classes
                //獲取到的是Test2類加載對象所在的包的最上一層目錄 : classes
        );
        System.out.println(baseFile);

        File packageFile = new File(
                Test2.class.getResource("").toURI()
                //C:\Users\User\IdeaProjects\JSD2303SE\target\classes\reflect\demo
                //獲取到的是Test2類加載對象所在的包 : reflect
        );
        System.out.println(packageFile.getAbsolutePath());
        //不建議直接於此獲得包名，因為當位處多層目錄中時，程序會無法正常運作
//        String packageName = packageFile.getName();//獲取包名
//        System.out.println("packageName = " + packageName);

        //獲取所在包的所有字節碼文件
        File[] Files = packageFile.listFiles(f -> f.getName().endsWith(".class"));//只包含字節碼文件
        for (File File : Files) {//遍歷這些文件
            //獲取這些文件的純類名//xxxxx.class ---> xxxxx
            String fileName = File.getName().substring(0,File.getName().indexOf("."));

            //獲取當前遍歷對象的類加載文件的包名
            String packageName = Test2.class.getPackage().getName();

            //綁定類加載對象並實例化
            Class cls = Class.forName(packageName + "." + fileName);
            Object obj = cls.newInstance();

            //獲取這些類本身自定義的所有方法
            Method[] methods = cls.getDeclaredMethods();

            //遍歷這些方法
            for (Method method : methods) {
                if (
                        method.getParameterCount() == 0 //無參
                                &&
                                method.getModifiers() == Modifier.PUBLIC //且公開
                ) {
                    method.invoke(obj);//調用這個方法
                }
            }


        }
    }
}




