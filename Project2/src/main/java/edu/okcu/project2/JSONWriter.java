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
    public static final String FILE = "classes.txt";



    public static void addCourse(Course course) {
        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //make a list of the existing courses
            List<Course> courses = readCourses();
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


    public static void updateTableForProfessors(TableView tableView, Professor professor) {
        List<Course> courses = readCourses();
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

    public static void updateTableForStudents(TableView tableView, Student student) {
        List<Course> courses = readCourses();
        //clear the table
        tableView.getItems().clear();

        //loop through students to list
        for (Course course : courses) {
            //if the student is in the course, add it to the table
            if (course.students.containsKey(student.getName())) {
                tableView.getItems().add(course);
            }
        }
    }

    /**
     * This is a method to read in the existing courses from the file.
     * @return <-- returns a list of courses. if no courses are present, returns a new list.
     */
    private static ArrayList<Course> readCourses() {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(FILE));
            return gson.fromJson(reader, new TypeToken<ArrayList<Course>>(){}.getType());
        } catch (IOException e) {
            //if the file doesn't exist, return an empty list
            return new ArrayList<>();
        }
    }

    //TODO: This will currently add a student and their grade. We need to update the table now
    public static void updateStudentRecord(TableView tableView, Course course, String name, String grade) {
        List<Course> courses = readCourses();
        for (Course c : courses) {
            if (c.equals(course)) {
                c.updateStudent(name, grade);
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE)) {
            gson.toJson(courses, writer);
        }
     catch (IOException e) {
        e.printStackTrace();
    }
    }
}
