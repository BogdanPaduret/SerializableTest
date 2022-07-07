package models;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void whenSerializingAndDeserializing_ThenObjectIsTheSame() throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setAge(20);
        person.setName("Joe");

        String path = "test/resources/persons.txt";

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person p2 = (Person) objectInputStream.readObject();
        objectInputStream.close();

        assertTrue(p2.getAge() == person.getAge());
        assertTrue(p2.getName().equals(person.getName()));
    }

    @Test
    public void listTest() {
        String path = "test/resources/persons.txt";
        ArrayList<Person> people = new ArrayList<>();
        int[] ages = {20, 21, 22, 23, 24};
        String[] names = {"Noel", "Joe", "Dumitru", "Costel", "Mircea"};
        int[] heights = {187, 155, 192, 212, 198};

        for (int i = 0; i < ages.length; i++) {
            people.add(new Person());
            people.get(i).setAge(ages[i]);
            people.get(i).setName(names[i]);
            people.get(i).setHeight(heights[i]);
        }

        assertEquals(5, people.size());
        System.out.println("\n\n\n===== Filled list before save =====");
        showArrayList(people);
        assertTrue(saveDatabase(people,path));
        people = new ArrayList<>();
        System.out.println("\n\n\n===== Empty list =====");
        showArrayList(people);
        assertEquals(0, people.size());
        people = loadDatabase(people, path);
        assertEquals(5, people.size());
        System.out.println("\n\n\n===== Filled list after load =====");
        showArrayList(people);
    }

    private boolean saveDatabase(ArrayList<Person> data, String path) {

        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private ArrayList<Person> loadDatabase(ArrayList<Person> data, String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (ArrayList<Person>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("***catch ERROR***");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("***catch ERROR***");
            e.printStackTrace();
        }
        return data;
    }

    private void showArrayList(ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void localDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 05, 11, 8, 30);
        System.out.println(localDateTime.toString());
    }



}