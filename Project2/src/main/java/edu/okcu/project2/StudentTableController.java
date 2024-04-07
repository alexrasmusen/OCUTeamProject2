package edu.okcu.project2;

public class StudentTableController {
    Student student;

    public void setStudent(Student student) {
        this.student = student;
        temporary();
    }

    private void temporary() {
        System.out.println("Name: " + student.getName() + " || " + student.getID());
    }
}

