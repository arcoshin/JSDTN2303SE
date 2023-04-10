package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 對象輸出流
 * ObjectOutputStream
 */
public class OOSDemo {
    public static void main(String[] args) {
        String name = "王克晶";
        int age = 18;
        String gender = "女";
        String[] otherInfo = {"黑", "嗓門大", "JAVA技術好", "大家的啟蒙老師", "來自廊坊佳木斯"};

        Person p1 = new Person(name, age, gender, otherInfo);

        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("demo/person.obj"));
        ) {
            oos.writeObject(p1);
            System.out.println("FINISH!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
