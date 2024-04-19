package edu.okcu.project2;

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.scene.control.TableView;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JSONWriter {

    //name of our file
    public static final String FILE = "classes.txt";

    public static List<Course> courses;



    public static void addCourse(Course course) {
        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //make a list of the existing courses

            //if the course already exists in the file, we will return. we do not want duplicate courses
            if (courses.contains(course)) {
                return;
            }
            //add our new course to the list
            courses.add(course);

            //write the GSON to the text file
            try (FileWriter writer = new FileWriter(FILE)) {
                gson.toJson(courses, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is used to add a course to the table of courses
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
     * @param tableView <-- the table to update
     * @param student <-- the student to add
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
     * @return <-- returns a list of courses. if no courses are present, returns a new list.
     */
    public static void readCourses() {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(FILE));
            courses = gson.fromJson(reader, new TypeToken<ArrayList<Course>>(){}.getType());
        } catch (Exception e) {
            //if the file doesn't exist or any other exception occurs, return an empty list
            courses = new ArrayList<>();
        }
    }


    /**
     * This method is used to update the student's grade in the course
     * @param tableView <-- the table to update
     * @param course <-- the course the student is enrolled in
     * @param name <-- the student's name
     * @param grade <-- the student's grade
     */
    public static void updateStudentRecord(TableView tableView, Course course, String name, String grade) {

        for (Course c : courses) {
            if (c.equals(course)) {
                c.updateStudent(name, grade);
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE)) {
            gson.toJson(courses, writer);
            getStudentInfo(name, tableView, grade, course);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the student's information from the file "Students.txt" This is the version for when
     * we only have the student's name
     * @param studentName <-- the student's name
     * @param tableView <-- the table to passed along to other methods
     * @param grade <-- the student's grade
     * @param course <-- the course the student is enrolled in
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
     * @param student <-- the student
     * @param tableView <-- the table to passed along to other methods
     * @param grade <-- the student's grade
     * @param course <-- the course the student is enrolled in
     */
    public static void getStudentInfo(Student student, TableView tableView, String grade, Course course) {
        File file = new File("Students.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String row;
            while ((row = reader.readLine()) != null) {
                var items = row.split(" , ");
                var IDFromFile = items[0];
                var nameFromFile = items[2];
                var emailFromFile = items[1];


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
     * @param course <-- the specific course to edit
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

}
