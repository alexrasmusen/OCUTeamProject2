package edu.okcu.project2;


import java.util.HashMap;

public class Course {
    //for some reason, these have to be public or GSON struggles with writing them
    public String courseName;
    public String professor;
    public HashMap<String, String> students = new HashMap<>();


    public Course(String professor, String courseName) {
        setProfessor(professor);
        setCourseName(courseName);
    }

    public void updateStudent(String student, String grade) {
        students.put(student, grade);
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

    /**
     * The equals method must be overridden to compare if two courses are the same. This is called in the JSONWriter class.
     * This prevents the same course from being added a million times.
     * @param obj <-- the object/course to be compared
     * @return <-- true if courses are same, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return courseName.equals(course.courseName) && professor.equals(course.professor);

    }






}
