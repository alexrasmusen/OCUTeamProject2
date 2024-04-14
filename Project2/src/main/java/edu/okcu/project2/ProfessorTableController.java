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
    TableColumn<Person, String> viewColumn;
    @FXML
    Button accessButton;
    @FXML
    TextField txtClass;
    @FXML
    Button btnAdd;
    @FXML
    Button btnUpdate;
    @FXML
    Button btnDelete;
    @FXML
    Button btnClear;
    String course = "";
    Professor professor;

    public void initialize () {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnClear.setDisable(true);

        classColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Classes"));
        viewColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("View"));
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;

    }

    public void onAddButtonClick(ActionEvent actionEvent){

    }

    public void onUpdateButtonClick(ActionEvent actionEvent){

    }

    public void onDeleteButtonClick(ActionEvent actionEvent){

    }

    public void onViewButtonClick(ActionEvent actionEvent){

    }

    public void onClearButtonClick(ActionEvent actionEvent){

    }
}
