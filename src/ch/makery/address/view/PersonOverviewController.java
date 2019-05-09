package ch.makery.address.view;

import ch.makery.address.AlertFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.scene.control.Alert.AlertType;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private MainApp mainApp;

    public PersonOverviewController() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        personTable.setItems(mainApp.getPersonData());
    }

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        showPersonDetails(null);
        
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue)
        );
    }
    
    @FXML
    private void handleDeletePerson(){
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        try{
            personTable.getItems().remove(selectedIndex);
        }catch(ArrayIndexOutOfBoundsException e){
            String title = "Nenhuma seleção";
            String headerText = "Nenhuma Pessoa Selecionada";
            String contentText = "Por favor, selecione uma pessoa na tabela.";
            AlertFactory.createAlert(AlertType.WARNING, title, headerText, contentText);
        }
    }
    
   @FXML
   private void handleNewPerson() {
       Person tempPerson = new Person();
       boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
       if (okClicked) {
           mainApp.getPersonData().add(tempPerson);
       }
   }

    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            String title = "Nenhuma seleção";
            String headerText = "Nenhuma Pessoa Selecionada";
            String contentText = "Por favor, selecione uma pessoa na tabela.";
            
            AlertFactory.createAlert(AlertType.WARNING, title, headerText, contentText);
        }
    }
    
    private void showPersonDetails(Person person) {
        if(person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
}
