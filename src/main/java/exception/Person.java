package exception;

/**
 * 使用當前類測試異常的拋出
 */
class Person {
    private int age;

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    public int getAge () {
        return age;
    }

    public void setAge (int age) throws IllegalAgeException {
        if (age < 0 || age >= 120) {
            throw new IllegalAgeException("輸入年齡不合法");
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
