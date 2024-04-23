package edu.okcu.project2;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.File;
import java.net.MalformedURLException;

//helper class
public class Helper {


    public static void setDarkTheme(Scene scene) throws MalformedURLException {
        //load dark theme
        File style = new File("Project2/src/main/resources/style.css");
        scene.getStylesheets().add(style.toURI().toURL().toExternalForm());
    }

    /**
     * Set the button size so the text doesn't cut off
     * @param button <-- button to change size of
     */
    public static void setButtonSize(Button[] buttons) {
        for (Button button : buttons) {
            button.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        }
    }
}
