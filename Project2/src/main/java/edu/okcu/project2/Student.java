package edu.okcu.project2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student extends Person {
    private String grade;
    public Student(String ID, String name, String email) {
        super(ID, name, email);
    }
    public Student(String name, String ID) {
        setName(name);
        setID(ID);
    }

    public Student(String name) {
        setName(name);
    }

    public Student (String ID, String name, String email, String grade){
        super(ID, name, email);
        this.grade = grade;
    }

    public Student (){
        super();
    }

    public ObservableList<Student> insertStudents(){
        ObservableList <Student> people = FXCollections.observableArrayList();
        people.add(new Student("one", "Sakka", "Sakka14@gmail.com"));
        people.add(new Student("two", "Zuko", "Zuko87@yahoo.com"));

        return people;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
