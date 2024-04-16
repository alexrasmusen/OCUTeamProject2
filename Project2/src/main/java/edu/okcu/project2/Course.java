package edu.okcu.project2;


import java.util.HashMap;

public class Course {
    //for some reason, these have to be public or GSON struggles with writing them
    public String courseName;
    public String professor;


    public Course(String professor, String courseName) {
        setProfessor(professor);
        setCourseName(courseName);
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getProfessor() {
        return this.professor;
    }






}
