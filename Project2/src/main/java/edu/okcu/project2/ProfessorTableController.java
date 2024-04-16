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
    TableView professorTableview = new TableView<>();
    @FXML
    TableColumn<Course, Integer> classColumn = new TableColumn<Course, Integer>();
    @FXML
    TableColumn<Person, String> accessColumn = new TableColumn<Person, String>();
    @FXML
    Button accessButton;
    @FXML
    TextField txtFieldClass;
    @FXML
    Button btnAdd = new Button();
    @FXML
    Button btnUpdate= new Button();
    @FXML
    Button btnDelete= new Button();
    @FXML
    Button btnClear= new Button();

    String course1 = "";
    Professor professor;

    public void initialize () {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnClear.setDisable(true);

        classColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("courseName"));
        accessColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("View"));

        professorTableview.getColumns().add(classColumn);

    }
    public void setProfessor(Professor professor) {
        this.professor = professor;

    }

    public void onAddButtonClick(ActionEvent actionEvent){
        if (!txtFieldClass.getText().isEmpty()) {
            Course course = new Course(professor.getName(), txtFieldClass.getText());
            JSONWriter.addCourse(course);
            JSONWriter.updateTable(professorTableview, professor);
            professorTableview.refresh();
        }
    }

    public void onUpdateButtonClick(ActionEvent actionEvent){

    }

    public void onDeleteButtonClick(ActionEvent actionEvent){

    }

    public void onAccessButtonClick(ActionEvent actionEvent){

    }


}
