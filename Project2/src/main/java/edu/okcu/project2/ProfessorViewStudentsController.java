package edu.okcu.project2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

public class ProfessorViewStudentsController extends AbstractStudentProfessorController{

    @FXML
    TableView<Person> tableView = new TableView<>();
    @FXML
    TableColumn<Student, Integer> IDColumn;
    @FXML
    TableColumn<Student, String> nameColumn;
    @FXML
    TableColumn<Student, String> emailColumn;
    @FXML
    TableColumn<Student, String> gradeColumn;
    @FXML
    Label lblID, lblWelcomeMessage;
    @FXML
    TextField txtName;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtGrade;
    @FXML
    Label lblError;
    @FXML
    Button btnAdd, btnUpdate, btnDelete, btnSignout, btnReturn, btnClear;


    private Professor professor;
    ObservableList<Person> people;

    Course course;
    HashMap<String, String> students;


    public void initialize (){
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        //resize array of buttons
        Button [] buttons = {btnAdd,btnDelete,btnUpdate,btnSignout,btnReturn,btnClear};
        Helper.setButtonSize(buttons);

        IDColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("grade"));


        //add a listener to the table view
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                //convert selection to student
                Student selectedStudent = (Student) newSelection;

                //set the text fields to the selected student
                txtName.setText(selectedStudent.getName());
                txtEmail.setText(selectedStudent.getEmail());
                txtGrade.setText(selectedStudent.getGrade());
                lblID.setText(String.valueOf(selectedStudent.getID()));
                //enable update if a student is selected
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                btnAdd.setDisable(true);
            } else {
                //if no student is selected, disable update button
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
                btnAdd.setDisable(false);
            }
        });


    }


    public void onAddButtonClick(ActionEvent actionEvent){
        String studentName = txtName.getText();
        String grade = txtGrade.getText();
        JSONWriter.updateStudentAndGrade(tableView, course, studentName.toLowerCase(), grade);
        tableView.refresh();



    }

    public void onUpdateButtonClick(ActionEvent actionEvent){

        Student student = new Student(lblID.getText(), txtName.getText(), txtEmail.getText(), txtGrade.getText());
        JSONWriter.updateStudentInfo(course, tableView, student);
        tableView.refresh();
    }

    /**
     * Here's the method for removing a student. It gets the student's name from the text field,
     * then removes it from the JSON file as well as the table.
     */
    public void onDeleteButtonClick(){
        String studentName = txtName.getText();
        JSONWriter.removeStudent(course, studentName);
        //this was just supposed to be an initial method, but I realized it would still work here to refresh the table
        JSONWriter.initialTableRefreshForProfessorStudentView(course, tableView);
        tableView.refresh();

    }

    public void onClearButtonClick(){
        //clear all the text
        txtName.setText("");
        txtEmail.setText("");
        txtGrade.setText("");
        lblID.setText("");
        //disable some buttons
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void onReturnButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("professor-view.fxml"));
        Parent root = loader.load();
        ProfessorTableController controller = loader.getController();
        controller.setProfessor(professor);
        Stage stage = new Stage();
        Scene scene = new Scene(root, 500, 450);
        Helper.setDarkTheme(scene);
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) btnSignout.getScene().getWindow();
        currentStage.close();
    }

    public void onSignoutButtonClick() {
        super.onSignoutButtonClick(btnSignout);
    }

    public void setCourse(Course course){
        this.course = course;
        lblWelcomeMessage.setText("Here are the students in " + course.getCourseName());
        //call the initial table refresh
        JSONWriter.initialTableRefreshForProfessorStudentView(course, tableView);
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
