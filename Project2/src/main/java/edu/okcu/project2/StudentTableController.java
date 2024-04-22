package edu.okcu.project2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentTableController {

    @FXML
    ComboBox comboCourses;
    @FXML
    TableView studentTableView = new TableView<>();
    @FXML
    TableColumn<Course, String> columnClasses;
    @FXML
    TableColumn<Course, String> columnGrades;
    @FXML
    Label lblWelcome;
    Student student;


    public void setStudent(Student student) {
        this.student = student;
        lblWelcome.setText("Welcome, " + student.getName() +  "!");
        //refresh the table view with courses currently enrolled in
        JSONWriter.initialTableRefreshForStudentView(studentTableView, student);
    }

    public void initialize() {
        //read the courses from file
        JSONWriter.readCourses();
        //populate the combo box with the courses
        JSONWriter.getCoursesOffered(comboCourses);
        columnClasses.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        columnGrades.setCellFactory(column -> new TableCell<Course, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    //get the course
                    Course course = getTableRow().getItem();
                    //get the grade of the currently logged in student
                    String grade = course.getGrades().get(student.getName());
                    setText(grade);
                }
            }
        });
    }

    public void onAddButtonClick(){
        Course course = (Course) comboCourses.getValue();
        JSONWriter.updateStudentAndGrade(course, student.getName());
        studentTableView.getItems().add(course);
    }

    public void onDeleteButtonClick(){
        Course course = (Course) studentTableView.getSelectionModel().getSelectedItem();
        JSONWriter.removeStudent(course, student.getName());
        studentTableView.getItems().remove(course);
    }
/*
    public void onUpdateButtonClick(ActionEvent actionEvent){

    }*/

}
