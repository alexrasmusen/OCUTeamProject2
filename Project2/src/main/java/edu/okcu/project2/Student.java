package edu.okcu.project2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student extends Person {
    public Student(String ID, String name, String email) {
        super(ID, name, email);
    }
    public Student(String name, String ID) {
        setName(name);
        setID(ID);
    }

    public Student (){
        super();
    }

    public Student(String name, String id) {
    }

    public ObservableList<Student> insertStudents(){
        ObservableList <Student> people = FXCollections.observableArrayList();
        people.add(new Student("one", "Sakka", "Sakka14@gmail.com"));
        people.add(new Student("two", "Zuko", "Zuko87@yahoo.com"));

        return people;
    }

}
