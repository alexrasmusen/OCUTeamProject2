package edu.okcu.project2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;

public class ProfessorViewStudentsController{

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
    Button btnAdd;
    @FXML
    Button btnUpdate;
    @FXML
    Button btnDelete;

    private Person selectedPerson;
    ObservableList<Person> people;

    Course course;
    HashMap<String, String> students;


    public void initialize (){
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

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


        /*
        tableView.setItems(people);

        TableView.TableViewSelectionModel<Person> selectionModel = tableView.getSelectionModel();
        ObservableList<Person> selectedItems = selectionModel.getSelectedItems();

        selectedItems.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                btnAdd.setDisable(true);
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);

                System.out.println(change.getList());
                selectedPerson = change.getList().get(0);
                lblID.setText(String.valueOf(selectedPerson.getID()));
                txtEmail.setText(selectedPerson.getEmail());
                txtName.setText(selectedPerson.getName());
            }
        });

         */
    }


    public void onAddButtonClick(ActionEvent actionEvent){
        String studentName = txtName.getText();
        String grade = txtGrade.getText();
        JSONWriter.updateStudentAndGrade(tableView, course, studentName.toLowerCase(), grade);
        tableView.refresh();


        /*
        var newPerson = new Student();
        newPerson.setID(lblID.getText());
        newPerson.setName(txtName.getText());
        newPerson.setEmail(txtEmail.getText());
        if (newPerson == new Student()){
            lblError.setText("Student Already added. Would you like to update?");
        } else {
            people.add(newPerson);
        }
        */
    }

    public void onUpdateButtonClick(ActionEvent actionEvent){
        /*
        for (var record : people) {
            if (Objects.equals(record.getID(), selectedPerson.getID())) {
                record.setID(lblID.getText());
                record.setName(txtName.getText());
                record.setEmail(txtEmail.getText());
            }
        }

         */
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

        /*
        if(selectedPerson != null){
            people.remove(selectedPerson);
        } else {
            lblError.setText("Please select a student");
        }
         */
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

    public void onReturnButtonClick(ActionEvent actionEvent){

    }

    public void onSignoutButtonClick(ActionEvent actionEvent){

    }

    public void setCourse(Course course){
        this.course = course;
        lblWelcomeMessage.setText("Here are the students in " + course.getCourseName());
        //call the initial table refresh
        JSONWriter.initialTableRefreshForProfessorStudentView(course, tableView);
    }

    public Course getCourse(){
        return course;
    }

    private void getCourseInfo() {

    }

    public void onSignOutButtonClick(ActionEvent actionEvent) {
    }
}
