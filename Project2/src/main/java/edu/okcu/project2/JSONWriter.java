package edu.okcu.project2;

import com.google.gson.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.util.*;

import com.google.gson.JsonParser;
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


    public static void updateTable(TableView tableView, Professor professor) {
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


}
