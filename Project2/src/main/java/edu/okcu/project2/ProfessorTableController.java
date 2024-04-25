package edu.okcu.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProfessorTableController extends AbstractStudentProfessorController{

    @FXML
    TableView professorTableview = new TableView<>();
    @FXML
    TableColumn<Course, String> classColumn = new TableColumn<Course, String>();
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
    @FXML
    Button btnSignout;
    @FXML
    Label lblWelcomeMessage;

    Professor professor;

    public void initialize () {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnClear.setDisable(true);
        //make array of buttons
        Button[] buttons = {btnAdd,btnClear,btnDelete,btnSignout,btnUpdate};
        //resize all the buttons so the text doesn't cut off
        Helper.setButtonSize(buttons);
        //??? profit
        classColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));

    }

    /**
     * Method to set the professor. This is called from the welcome view controller. It will set the professor and update the table.
     * @param professor
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
        lblWelcomeMessage.setText("Welcome, " + professor.getName());
        JSONWriter.readCourses();
        //we will update the table here. This will display the professor's courses. This has to be called here because calling it earlier would result in professor being "null"
        JSONWriter.updateTableForProfessors(professorTableview, professor);
    }

    /**
     * This method is called when the user clicks "add". If there is text in the text field, it will create a new
     * course of that name. It will then add that course to the professor's list of courses and update the table.
     */
    public void onAddButtonClick(){
        //check if text field is empty
        if (!txtFieldClass.getText().isEmpty()) {
            Course course = new Course(professor.getName(), txtFieldClass.getText());
            JSONWriter.addCourse(course);
            JSONWriter.updateTableForProfessors(professorTableview, professor);
            professorTableview.refresh();
        }
        btnClear.setDisable(false);
    }

    //This method loads in the professorViewStudents fxml
    public void onViewClassInfo(ActionEvent actionEvent){
        Course selectedCourse = (Course) professorTableview.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("professorViewStudents.fxml"));
                Parent root = loader.load();
                //open up the professor's student view
                ProfessorViewStudentsController controller = loader.getController();
                controller.setCourse(selectedCourse);
                controller.setProfessor(professor);
                Stage stage = new Stage();
                Scene scene = new Scene(root, 550, 450);
                Helper.setDarkTheme(scene);
                stage.setScene(scene);
                stage.show();

                //close the current window
                Stage currentStage = (Stage) btnUpdate.getScene().getWindow();
                currentStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onDeleteButtonClick(ActionEvent actionEvent){
        Course selectedCourse = (Course) professorTableview.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            JSONWriter.removeCourse(selectedCourse, professorTableview);
            JSONWriter.readCourses();
            JSONWriter.updateTableForProfessors(professorTableview, professor);
        }
    }

    public void onClearButtonClick(ActionEvent actionEvent){
        txtFieldClass.setText("");
    }

    public void onSignoutButtonClick() {
        super.onSignoutButtonClick(btnSignout);
    }

}
