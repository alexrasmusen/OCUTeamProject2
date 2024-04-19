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
        JSONWriter.updateStudentRecord(tableView, course, studentName, grade);
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
        for (var record : people) {
            if (record.getID() == selectedPerson.getID()) {
                record.setID(lblID.getText());
                record.setName(txtName.getText());
                record.setEmail(txtEmail.getText());
            }
        }
        tableView.refresh();
    }

    public void onDeleteButtonClick(ActionEvent actionEvent){
        if(selectedPerson != null){
            people.remove(selectedPerson);
        } else {
            lblError.setText("Please select a student");
        }
    }

    public void onClearButtonClick(ActionEvent actionEvent){
        txtName.setText("");
        txtEmail.setText("");
        txtGrade.setText("");
    }

    public void onReturnButtonClick(ActionEvent actionEvent){

    }

    public void onSignoutButtonClick(ActionEvent actionEvent){

    }

    public void setCourse(Course course){
        this.course = course;
        lblWelcomeMessage.setText("Here are the students in " + course.getCourseName());
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
