package edu.okcu.project2;


import java.util.HashMap;

public class Course {
    public String courseName;
    public String professor;
    public HashMap<String, String> studentsMap = new HashMap<>();

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

    public void addStudent(String student, String grade) {
        studentsMap.put(student,grade);
    }

    public String getGrade(String student) {
        return studentsMap.get(student);
    }




}
