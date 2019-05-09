package ch.makery.address.view;

import ch.makery.address.AlertFactory;
import ch.makery.address.MainApp;
import ch.makery.address.FileHandler;
import ch.makery.address.model.Person;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootLayoutController {
    private MainApp mainApp;
    private FileHandler<Person> fileHandler;
    private ObservableList<Person> personData;
    
    public void setMainAppAndFileHandler(MainApp mainApp, FileHandler fileHandler){
        this.mainApp = mainApp;
        personData = mainApp.getPersonData();
        this.fileHandler = fileHandler;
    }
    
    @FXML
    private void handleNew() {
        personData.clear();
        fileHandler.setFilePath(null);
    }
    
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML files (*xml)", ".xml");
        
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        
        if (file != null) {
            fileHandler.loadDataFromFile(file, personData);
        }
    }
    
    @FXML
    private void handleSave() {
        File personFile = fileHandler.getFilePath();
        if (personFile != null) {
            fileHandler.saveDataToFile(personFile, personData);
        } else {
            handleSaveAs();
        }
    }
    
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        
        if (file != null) {
            if(!file.getPath().endsWith(".xml")){
                file = new File(file.getPath() + ".xml");
            }
            fileHandler.saveDataToFile(file, personData);
        }
    }
    
    @FXML
    private void handleAbout() {
        String title = "AddresApp";
        String headerText = "Sobre:";
        String contentText = "Autor: Marco Jakob\n Website: http://code.makery.ch";

        AlertFactory.createAlert(AlertType.INFORMATION, title, headerText, contentText);
    }
    
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }
}
