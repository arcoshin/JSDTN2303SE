package io;

import java.io.Serializable;

/**
 * 使用當前類測試對象流的讀寫操作
 */
public class Person implements Serializable {
    private String name;
    int age;
    String gender;
    private String[] otherInfo;

    public Person(String name, int age, String gender, String otherInfo[]) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo[]) {
        this.otherInfo = otherInfo;
    }
}
