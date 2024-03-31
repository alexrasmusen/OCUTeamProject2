package edu.okcu.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WelcomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onSignUpButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUp-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}