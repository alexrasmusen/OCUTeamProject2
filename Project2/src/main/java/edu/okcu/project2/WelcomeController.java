package edu.okcu.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WelcomeController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button StudentSUButton;
    @FXML
    private Button StudentLButton;

    @FXML
    protected void onStuLoginButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("studentLogin.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 400, 400);
        Helper.setDarkTheme(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onStuSignUpButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("studentSignUp.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 400, 400);
        Helper.setDarkTheme(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onProfLoginButtonClick(ActionEvent actionEvent)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("profLogin.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root,400,400);
        Helper.setDarkTheme(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onProfSignUpButtonClick()throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("profSignUp.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 400,400);
        Helper.setDarkTheme(scene);
        stage.setScene(scene);
        stage.show();
    }
}