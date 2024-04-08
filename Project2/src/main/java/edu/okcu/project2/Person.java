package edu.okcu.project2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Person {
    String name, ID, email;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Person (String ID, String name, String email){
        this.ID=ID;
        this.name=name;
        this.email=email;
    }

    public Person(){
        this.ID="Null";
        this.name="Null";
        this.email="Null";
    }

    public String getEmail() {
        return email;
    }

    public String getID() {
        return ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    /*public static ObservableList fakeData(){
        ObservableList <Person> people = FXCollections.observableArrayList();

        people.add(new Person(1, "Sakka", "Sakka14@gmail.com", "C-"));
        people.add(new Person(2, "Zuko", "Zuko87@yahoo.com", "B+"));

        return people;
    }*/
}
