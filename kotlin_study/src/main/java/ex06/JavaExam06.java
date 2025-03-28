package ex06;

interface Bark {
    void bark();
}

abstract class Animal implements Bark {

    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + ", 식사");
    }

    public String getName() {
        return this.name;
    }

}

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void bark() {
        System.out.println(this.getName() + ", bark bark");
    }

}

public class JavaExam06 {

    public JavaExam06() {
        var dog = new Dog("개");
        dog.eat();
        dog.bark();
    }

    public static void main(String[] args) {
        new JavaExam06();
    }

}
