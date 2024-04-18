package edu.okcu.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;


public class LoginController {

    @FXML
    private Label lblLogin, lblEmail, lblPassword;
    @FXML
    private TextField txtfieldEmail, txtfieldPassword;
    @FXML
    private Button buttonCancel;
    String LoginEmail;
    String LoginPassword;

    String name, ID;

    File file;

    public void login(Stage stage) throws IOException {

    }

    public boolean onSecondLoginButtonClick() throws IOException {
        LoginEmail = txtfieldEmail.getText();
        LoginPassword = txtfieldPassword.getText();

        //read in the contents of the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String row;
            while ((row = reader.readLine()) != null) {
                var items = row.split(" , ");
                var IDFromFile = items[0];
                var nameFromFile = items[1];
                var emailFromFile = items[2];
                var passwordFromFile = items[3];

                var decryptedPass = BCrypt.checkpw(LoginPassword, passwordFromFile);
                //check if the email and password are correct
                if (LoginEmail.equals(emailFromFile) && decryptedPass) {
                    setID(IDFromFile);
                    setName(nameFromFile);
                    return true;
                }
            }
        }
        return false;
    }

    public void setLblLogin(Label lblLogin) {
        this.lblLogin = lblLogin;
    }

    public void onSecondCancelButtonClick() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("welcome-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 400, 400);
            stage.setScene(scene);
            Helper.setDarkTheme(scene);
            stage.show();

            closeCurrentWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeCurrentWindow() {
        Stage currentStage = (Stage) buttonCancel.getScene().getWindow();
        currentStage.close();
    }

    //getters and setters for stuff
    public void setFile(String fileName) {
        file = new File(fileName);
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }
}


