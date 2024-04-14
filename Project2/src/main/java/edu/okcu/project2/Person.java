package edu.okcu.project2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Person {
    String name, ID, email;

    public Person(String name, String id) {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }


    public Person (String ID, String name, String email) {
        setID(ID);
        setName(name);
        setEmail(email);
    }

    public Person (String name, String ID) {
        setName(name);
        setID(ID);
    }

    public Person(){
        this.ID="Null";
        this.name="Null";
        this.email="Null";
    }
}
