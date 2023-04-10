package exception;

/**
 * 使用當前類測試異常的拋出
 */
public class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge () {
        return age;
    }

    public void setAge (int age) {
        if (age < 0 || age >= 120) {
            throw new RuntimeException("輸入年齡不合法");
        } else {
            this.age = age;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
