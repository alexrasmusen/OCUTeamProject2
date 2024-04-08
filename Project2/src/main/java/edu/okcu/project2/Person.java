package edu.okcu.project2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person {
    String name, email, grade;
    int ID;

    public Person (int ID, String name, String email, String grade){
        this.ID=ID;
        this.name=name;
        this.email=email;
        this.grade=grade;
    }

    public Person(){
        this.ID=0;
        this.name="Null";
        this.email="Null";
        this.grade="Null";
    }

    public String getEmail() {
        return email;
    }

    public String getGrade() {
        return grade;
    }

    public int getID() {
        return ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static ObservableList fakeData(){
        ObservableList <Person> people = FXCollections.observableArrayList();

        people.add(new Person(1, "Sakka", "Sakka14@gmail.com", "C-"));
        people.add(new Person(2, "Zuko", "Zuko87@yahoo.com", "B+"));

        return people;
    }
}
