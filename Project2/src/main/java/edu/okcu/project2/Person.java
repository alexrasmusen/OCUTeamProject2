package edu.okcu.project2;

public abstract class Person {
    String name, ID, email;


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

    public Person (String ID, String name){
        setID(ID);
        setName(name);
    }
    public Person(){
        this.ID="Null";
        this.name="Null";
        this.email="Null";
    }
}
