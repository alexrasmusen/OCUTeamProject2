package edu.okcu.project2;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfessorViewStudentsController{

    @FXML
    TableView<Person> tableView;
    @FXML
    TableColumn<Person, Integer> IDColumn;
    @FXML
    TableColumn<Person, String> nameColumn;
    @FXML
    TableColumn<Person, String> emailColumn;
    @FXML
    TableColumn<Person, String> gradeColumn;
    @FXML
    TextField txtID;
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

    public void initialize (){
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        IDColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("grade"));

        people = Person.fakeData();
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
                txtID.setText(String.valueOf(selectedPerson.getID()));
                txtEmail.setText(selectedPerson.getEmail());
                txtName.setText(selectedPerson.getName());
                txtGrade.setText(selectedPerson.getGrade());
            }
        });
    }


    public void onAddButtonClick(ActionEvent actionEvent){
        var newPerson = new Person();
        newPerson.setID(Integer.parseInt(txtID.getText()));
        newPerson.setName(txtName.getText());
        newPerson.setEmail(txtEmail.getText());
        newPerson.setGrade(txtGrade.getText());
        if (newPerson == new Person()){
            lblError.setText("Student Already added. Would you like to update?");
        } else {
            people.add(newPerson);
        }
    }

    public void onUpdateButtonClick(ActionEvent actionEvent){
        for (var record : people) {
            if (record.getID() == selectedPerson.getID()) {
                record.setID(Integer.parseInt(txtID.getText()));
                record.setName(txtName.getText());
                record.setEmail(txtEmail.getText());
                record.setGrade(txtGrade.getText());
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

    public void onReturnButtonClick(ActionEvent actionEvent){

    }

    public void onSignoutButtonClick(ActionEvent actionEvent){

    }

}
