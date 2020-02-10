package smallSpring.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Person {

    private int age;

    private Boss boss;

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
