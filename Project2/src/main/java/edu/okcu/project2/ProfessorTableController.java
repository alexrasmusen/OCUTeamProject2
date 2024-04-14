package edu.okcu.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfessorTableController {

    @FXML
    TableView professorTableview;
    @FXML
    TableColumn<Person, Integer> classColumn;
    @FXML
    TableColumn<Person, String> accessColumn;
    @FXML
    Button accessButton;
    @FXML
    TextField txtFieldClass;
    @FXML
    Button btnAdd;
    @FXML
    Button btnUpdate;
    @FXML
    Button btnDelete;
    @FXML
    Button btnClear;

    String course1 = "";
    Professor professor;

    public void initialize () {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnClear.setDisable(true);

        classColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Classes"));
        accessColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("View"));
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;

    }

    public void onAddButtonClick(ActionEvent actionEvent){
        if (!txtFieldClass.getText().isEmpty()) {
            Course course = new Course(professor.getName(), txtFieldClass.getText());
            JSONWriter.addCourse(course);
        }
    }

    public void onUpdateButtonClick(ActionEvent actionEvent){

    }

    public void onDeleteButtonClick(ActionEvent actionEvent){

    }

    public void onAccessButtonClick(ActionEvent actionEvent){

    }
}
