package com.epam.turik.consoledb.model.person;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "People")
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    String name;
    Integer age;
    Boolean hascat;

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

    public boolean isHascat() {
        return hascat;
    }

    public void setHascat(boolean hascat) {
        this.hascat = hascat;
    }

    private Person() {}

    public Person(String name, int age, boolean hascat) {
        this.name = name;
        this.age = age;
        this.hascat = hascat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, hascat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age.equals(person.age) &&
                hascat == person.hascat &&
                name.equals(person.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hascat=" + hascat +
                '}';
    }

}

