package edu.okcu.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileWriter;
import java.io.IOException;

public class ProfessorSignUpController {
    @FXML
    public Label lblName;
    @FXML
    public TextField txtfieldName;
    @FXML
    public Label lblEmail;
    @FXML
    public TextField txtfieldEmail;
    @FXML
    public Label lblPassword;
    @FXML
    public TextField txtfieldPassword;
    @FXML
    public Button buttonSignUp;
    @FXML
    public Button buttonCancel;

    String ProfSUEmail;
    String ProfSUName;
    String ProfSUPassword;
    String file = "ProfessorSignUp.txt";

    public void onProfessorSignUpButtonClick(ActionEvent actionEvent) throws IOException {
        ProfSUName = txtfieldName.getText();
        ProfSUEmail = txtfieldEmail.getText();
        ProfSUPassword = txtfieldPassword.getText();
        var hashedPassword = BCrypt.hashpw(ProfSUPassword, BCrypt.gensalt(10));

        FileWriter fw = new FileWriter(file, true);
        fw.write(ProfSUName);
        fw.write(" , ");
        fw.write(ProfSUEmail);
        fw.write(" , ");
        fw.write(hashedPassword);
        fw.write("\n");

        fw.close();

        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
    public void onCancelButtonClick(ActionEvent actionEvent){
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
}
