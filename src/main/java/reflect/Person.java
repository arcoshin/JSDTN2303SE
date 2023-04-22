package reflect;


import reflect.annotations.AutoRunClass;
import reflect.annotations.AutoRunMethod;

/**
 * 使用當前類測試反射
 */
@AutoRunClass
public class Person {
    private String name = "張三";

    private int age = 18;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println(name + ":hello");
    }

    @AutoRunMethod(3)
    public void sayHi() {
        System.out.println(name + ":hi");
    }

    @AutoRunMethod(2)
    public void sayGoodBye() {
        System.out.println(name + ":goodBye");
    }

    public void playGame() {
        System.out.println(name + ":打遊戲");
    }

    @AutoRunMethod
    public void watchTV() {
        System.out.println(name + ":看電視");
    }

    public void say(String info) {
        System.out.println(name + "說:" + info);
    }

    public void say(String info, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(name + "說:" + info);
        }
    }

    private void hey() {
        System.out.println("Person中的私有方法hey()已被調用!!!");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
