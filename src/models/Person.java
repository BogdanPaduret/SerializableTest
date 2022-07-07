package models;

import java.io.Serializable;

public class Person implements Serializable {

    //instance variables
    private static final long serialVersionUID = 1L;
    static String country = "ITALY";
    private int age;
    private String name;
    transient int height;

    // getters and setters
    public static String getCountry() {
        return country;
    }
    public static void setCountry(String country) {
        Person.country = country;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        String string = "";

        string += "Name: " + getName() + "\n";
        string += "Age: " + getAge() + "\n";
        string += "Height: " + getHeight() + "\n";

        return string;
    }
}
