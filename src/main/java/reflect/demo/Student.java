package reflect.demo;

import reflect.annotations.AutoRunClass;
import reflect.annotations.AutoRunMethod;

/**
 * 使用當前類測試反射
 */
@AutoRunClass
public class Student {
    @AutoRunMethod
    public void study() {
        System.out.println("學生:good good study,day day up！");
    }

    public void playGame() {
        System.out.println("學生:玩遊戲");
    }

    @AutoRunMethod
    public void sleep() {
        System.out.println("學生:睡覺");
    }

    @Override
    public String toString() {
        return "Student{}";
    }
}
