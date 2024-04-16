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
    TableColumn<Course, String> classColumn = new TableColumn<Course, String>();
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

        classColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));

    }
    public void setProfessor(Professor professor) {
        this.professor = professor;

        //we will update the table here. This will display the professor's courses. This has to be called here because calling it earlier would result in professor being "null"
        JSONWriter.updateTable(professorTableview, professor);
    }

    /**
     * This method is called when the user clicks "add". If there is text in the text field, it will create a new
     * course of that name. It will then add that course to the professor's list of courses and update the table.
     */
    public void onAddButtonClick(){
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
