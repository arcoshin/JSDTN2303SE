package io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

/**
 * 對象輸入流
 */
class OISDemo {
    public static void main(String[] args) {
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("demo/person.obj"));
        ) {
            Person person = (Person) ois.readObject();
            System.out.println(person);
            System.out.println("otherInfo():" + Arrays.toString(person.getOtherInfo()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
