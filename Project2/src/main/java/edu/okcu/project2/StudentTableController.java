package edu.okcu.project2;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentTableController {

    @FXML
    TableView studentTableView = new TableView<>();
    @FXML
    TableColumn<Course, String> columnClasses = new TableColumn<Course, String>();
    @FXML
    ComboBox<Course> comboBoxClasses = new ComboBox<>();
    Student student;


    public void setStudent(Student student) {
        this.student = student;
        JSONWriter.readCourses();
        JSONWriter.getCoursesOffered(comboBoxClasses);
    }

    public void initialize() {
        columnClasses.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
    }

    /*public void onAddButtonClick(ActionEvent actionEvent){

    }

    public void onDeleteButtonClick(ActionEvent actionEvent){

    }

    public void onUpdateButtonClick(ActionEvent actionEvent){

    }*/

}
