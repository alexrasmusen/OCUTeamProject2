package edu.okcu.project2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {

    public static final String FILE = "classes.txt";
    public static void addCourse(Course course) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter(FILE, true)) {
                String jsonCourse = gson.toJson(course);


                writer.write(jsonCourse);
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
