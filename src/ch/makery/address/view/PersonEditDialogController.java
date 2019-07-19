package ch.makery.address.view;

import ch.makery.address.util.AlertFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Alert.AlertType;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

public class PersonEditDialogController implements Controller{

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked;

    @FXML
    private void initialize() {
        okClicked = false;
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = constructErrorMessage();

        if (errorMessage.length() == 0) {
            return true;
        } else {
            String title = "Campos Inválidos";
            String headerText = "Por favor, corrija os campos inválidos";

            AlertFactory.createAlert(AlertType.ERROR, title, headerText, errorMessage);

            return false;
        }
    }

    private String constructErrorMessage() {
        String errorMessage = "";
        
        if (!isFieldValid(firstNameField)) {
            errorMessage += "Nome inválido!\n";
        }
        
        if (!isFieldValid(lastNameField)) {
            errorMessage += "Sobrenome inválido!\n";
        }
        
        if (!isFieldValid(streetField)) {
            errorMessage += "Rua inválida!\n";
        }
        
        if (!isFieldValid(postalCodeField)) {
            errorMessage += "Código Postal inválido!\n";
        } else {
            final String postalCodeTypeError = "Código Postal inválido (deve ser um inteiro)!\n";
            errorMessage += constructErrorMessageIfFieldIsInvalid(postalCodeTypeError);
        }
        
        if (!isFieldValid(cityField)) {
            errorMessage += "Cidade inválida!\n";
        }
        
        if (!isFieldValid(birthdayField)) {
            errorMessage += "Aniversário inválido!\n";
        } else {
            final String birthdayFieldInvalidDateMessage = "Aniversário inválido. Use o formato dd.mm.yyyy!\n";
            errorMessage += handleDateFieldErrorMessageIFDateIsInvalid(birthdayFieldInvalidDateMessage);
        }
        
        return errorMessage;
    }

    private boolean isFieldValid(TextField field) {
        String text = field.getText();
        final boolean isFieldNull = (text == null);
        final boolean isFieldEmpty = (field.getText().length() == 0);
        boolean isInvalid = isFieldNull || isFieldEmpty;
        return !isInvalid;
    }

    private String constructErrorMessageIfFieldIsInvalid(String errorMessageIfInvalid) {
        String errorMessage = "";
        try {
            Integer.parseInt(postalCodeField.getText());
        } catch (NumberFormatException e) {
            errorMessage += errorMessageIfInvalid;
        }
        return errorMessage;
    }

    private String handleDateFieldErrorMessageIFDateIsInvalid(String errorMessageIfInvalid) {
        String errorMessage = "";
        if (!DateUtil.validDate(birthdayField.getText())) {
            errorMessage = errorMessageIfInvalid;
        }
        return errorMessage;
    }
}
