package Lab_0;
public class Person {
    private final String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

