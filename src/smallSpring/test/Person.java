package smallSpring.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Person {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class C=Person.class;
        Person p=(Person)C.newInstance();
        Method method=C.getDeclaredMethod("setName",String.class);
        method.invoke((Object) p,"xiaoming");
    }
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
