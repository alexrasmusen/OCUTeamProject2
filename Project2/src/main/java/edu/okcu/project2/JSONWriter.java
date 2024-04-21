package edu.okcu.project2;

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.scene.control.TableView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JSONWriter {

    //name of our file
    private static final String FILE = "classes.txt";

    public static List<Course> courses;


    /**
     * This method is used to add a course to the list of courses and write it to the file. This is called from the ProfessorTableController
     *
     * @param course <-- the course to add
     */
    public static void addCourse(Course course) {
        try {
            //if the course already exists in the file, we will return. we do not want duplicate courses
            if (courses.contains(course)) {
                return;
            }
            //add our new course to the list
            courses.add(course);

            //write the GSON to the text file
            gsonToJson();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is used to add a course to the table of courses
     *
     * @param tableView <-- the table to update
     * @param professor <-- the professor that teaches the course. This is necessary to find the courses they teach
     */
    public static void updateTableForProfessors(TableView tableView, Professor professor) {

        //clear the table
        tableView.getItems().clear();


        //loop through the courses in the list
        for (Course course : courses) {
            //if the course is taught by the professor, add it to the table
            if (course.getProfessor().equals(professor.getName())) {
                tableView.getItems().add(course);
            }
        }

    }

    /**
     * This method will update the list of students in the current class
     *
     * @param tableView      <-- the table to update
     * @param student        <-- the student to add
     * @param selectedCourse <-- the course to add the student to
     */
    public static void updateTableForStudents(TableView tableView, Student student, Course selectedCourse) {
        try {

            //loop through students to list
            for (Course course : courses) {
                //if the student is in the course, add it to the table
                if (course.equals(selectedCourse)) {
                    tableView.getItems().add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This is a method to read in the existing courses from the file.
     *
     * @return <-- returns a list of courses. if no courses are present, returns a new list.
     */
    public static void readCourses() {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(FILE));
            courses = gson.fromJson(reader, new TypeToken<ArrayList<Course>>() {
            }.getType());
        } catch (Exception e) {
            //if the file doesn't exist or any other exception occurs, return an empty list
            courses = new ArrayList<>();
        }
    }


    /**
     * This method is used to update the student's grade in the course
     *
     * @param tableView <-- the table to update
     * @param course    <-- the course the student is enrolled in
     * @param name      <-- the student's name
     * @param grade     <-- the student's grade
     */
    public static void updateStudentAndGrade(TableView tableView, Course course, String name, String grade) {

        for (Course c : courses) {
            if (c.equals(course)) {
                c.updateStudent(name, grade);
            }
        }
        gsonToJson();
        getStudentInfo(name, tableView, grade, course);

    }

    /**
     * This method is used to remove a student from the course. It will then write the updated list to the file.
     *
     * @param course <-- the course to remove the student from
     * @param name   <-- the student's name
     */
    public static void removeStudent(Course course, String name) {
        //iterate through the courses
        for (Course c : courses) {
            if (c.equals(course)) {
                //remove the student from the course
                c.removeStudent(name);
            }
            gsonToJson();

        }
    }

    /**
     * This method is used to get the student's information from the file "Students.txt" This is the version for when
     * we only have the student's name
     *
     * @param studentName <-- the student's name
     * @param tableView   <-- the table to passed along to other methods
     * @param grade       <-- the student's grade
     * @param course      <-- the course the student is enrolled in
     */
    public static void getStudentInfo(String studentName, TableView tableView, String grade, Course course) {
        File file = new File("Students.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String row;
            while ((row = reader.readLine()) != null) {
                var items = row.split(" , ");
                var IDFromFile = items[0];
                var nameFromFile = items[2];
                var emailFromFile = items[1];

                //check if the student name is correct
                if (nameFromFile.equals(studentName)) {
                    Student student = new Student(IDFromFile, nameFromFile, emailFromFile, grade);
                    updateTableForStudents(tableView, student, course);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the student's information from the file "Students.txt". This is the version when we have a Student
     * already created.
     *
     * @param student   <-- the student
     * @param tableView <-- the table to passed along to other methods
     * @param grade     <-- the student's grade
     * @param course    <-- the course the student is enrolled in
     */
    public static void getStudentInfo(Student student, TableView tableView, String grade, Course course) {
        File file = new File("Students.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String row;
            while ((row = reader.readLine()) != null) {
                var items = row.split(" , ");
                var IDFromFile = items[0];
                var nameFromFile = items[1];
                var emailFromFile = items[2];


                //check if the student name is correct
                if (nameFromFile.equals(student.getName())) {
                    Student newStudent = new Student(IDFromFile, nameFromFile, emailFromFile, grade);
                    updateTableForStudents(tableView, newStudent, course);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to update the table for the professor's student view when the table first loads
     *
     * @param course    <-- the specific course to edit
     * @param tableView <-- the table to edit
     */
    public static void initialTableRefreshForProfessorStudentView(Course course, TableView tableView) {
        tableView.getItems().clear();

        for (Course c : courses) {
            if (c.equals(course)) {
                //iterate through hashmap and add students to the table
                for (String key : c.students.keySet()) {

                    String grade = c.students.get(key);
                    Student student = new Student(key);
                    getStudentInfo(student, tableView, grade, course);
                }
            }
        }
    }

    public static void updateStudentInfo(Course course, TableView tableView, Student student) {
        File file = new File("Students.txt");
        StringBuilder currentInfo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //read in all the current records
            String row;
            while ((row = reader.readLine()) != null) {
                var items = row.split(" , ");
                var IDFromFile = items[0];
                var hashedPassword = items[3];
                //check if the student name is correct
                if (IDFromFile.equals(student.getID())) {
                    currentInfo.append(student.ID).append(" , ").append(student.getName()).append(" , ").append(student.getEmail()).append(" , ").append(hashedPassword).append("\n");
                } else {
                    //if it's not the correct student, just append the row
                    currentInfo.append(row).append("\n");
                }
            }
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(currentInfo.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateStudentAndGrade(tableView, course, student.getName(), student.getGrade());
            // add updated student to table view
            tableView.getItems().add(student);
            initialTableRefreshForProfessorStudentView(course, tableView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used so that I didn't have to copy and paste some stuff. It will write the list of courses to the file.
     */
    private static void gsonToJson() {
        //initialize a Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE)) {
            gson.toJson(courses, writer);
        } catch (IOException e) {
            //realistically, this should never happen because we are writing to a file that we know exists
            e.printStackTrace();

        }
    }
}
