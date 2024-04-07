package edu.okcu.project2;

public class ProfessorTableController {
    Professor professor;

    public void setProfessor(Professor professor) {
        this.professor = professor;
        temporary();
    }

    private void temporary() {
        System.out.println("Name: " + professor.getName() + " || " + professor.getID());
    }
}
