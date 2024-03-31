package edu.okcu.project2;

import javafx.scene.Scene;

import java.io.File;
import java.net.MalformedURLException;

//helper class
public class Helper {


    public static void setDarkTheme(Scene scene) throws MalformedURLException {
        //load dark theme
        File style = new File("Project2/src/main/resources/style.css");
        scene.getStylesheets().add(style.toURI().toURL().toExternalForm());
    }
}
