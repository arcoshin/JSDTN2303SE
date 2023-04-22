package reflect;

import reflect.annotations.AutoRunClass;

import java.io.File;

/**
 * 實例化與當前類Test3在同一個包中被註解@AutoRunClass的類
 */
public class Test3 {
    public static void main(String[] args) throws Exception{
        //綁定Test3類對象位置
        File baseFile = new File(
                Test3.class.getResource(".").toURI()
        );
//        System.out.println(baseFile);//打樁
        //C:\Users\User\IdeaProjects\JSD2303SE\target\classes\reflect

        //獲得包路徑名
        String packageName = Test3.class.getPackage().getName();
//        System.out.println(packageName);//reflect(打樁)

        //獲取同包內的所有內容
        File[] files = baseFile.listFiles(f->f.getName().endsWith(".class"));//只獲取字節碼文件

        //遍歷這些對象
        for (File file : files) {
//            System.out.println(file.getName());//打樁
            //獲取類名:xxxxx.class
            String fileName = file.getName();
            //處理檔名:xxxxx.class -> xxxxx
            fileName = file.getName().substring(0,fileName.indexOf("."));
            //獲取類對象
            Class cls = Class.forName(packageName + "." + fileName);
            //判斷是否被標注
            if (cls.isAnnotationPresent(AutoRunClass.class)){//如果有被AutoRunClass所標注
                //實例化對象
                Object obj = cls.newInstance();
                System.out.println("實例化......" + obj);
            }
        }

    }
}
