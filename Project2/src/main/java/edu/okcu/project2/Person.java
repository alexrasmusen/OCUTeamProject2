package edu.okcu.project2;

public abstract class Person {
    String name;
    String ID;

    public Person(String name, String ID) {
        setID(ID);
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }

}
