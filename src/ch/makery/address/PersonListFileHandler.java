package ch.makery.address;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import java.io.File;
import java.util.List;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Paulo Vitor
 */
public class PersonListFileHandler implements FileHandler<Person> {

    private final Preferences preferences;
    private final Stage primaryStage;

    public PersonListFileHandler(Preferences preferences, Stage primaryStage) {
        this.preferences = preferences;
        this.primaryStage = primaryStage;
    }

    @Override
    public List<Person> getDataFromFile(File file) {
        try {
            Unmarshaller unmarshaller = getUnmarsheller();

            PersonListWrapper wrapper = (PersonListWrapper) unmarshaller.unmarshal(file);

            setFilePath(file);

            return wrapper.getPersons();
        } catch (JAXBException e) {
        }
        return null;
    }

    @Override
    public void saveDataToFile(File file, List<Person> personData) {
        try {
            PersonListWrapper wrapper = new PersonListWrapper();
            Marshaller marshaller = getMarshaller();

            wrapper.setPersons(personData);
            marshaller.marshal(wrapper, file);

            setFilePath(file);
        } catch (JAXBException e) {
            String title = "Erro";
            String headerText = "Não foi possível salvar os dados do arquivo:\n" + file.getPath();
            AlertFactory.createAlert(AlertType.ERROR, title, headerText, null);
        }
    }

    @Override
    public void loadDataFromFile(File file, List<Person> personData) {
        try {
            Unmarshaller um = getUnmarsheller();

            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            setFilePath(file);
        } catch (JAXBException e) {
            String title = "Erro";
            String headerText = "Não foi possível carregar os dados do arquivo:\n" + file.getPath();
            AlertFactory.createAlert(AlertType.ERROR, title, headerText, null);

        }
    }

    @Override
    public File getFilePath() {
        String filePath = preferences.get("filePath", null);

        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    @Override
    public void setFilePath(File file) {
        if (file != null) {
            preferences.put("filePath", file.getPath());

            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            preferences.remove("filePath");

            primaryStage.setTitle("AdressApp");
        }
    }

    private Unmarshaller getUnmarsheller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller;
    }

    private Marshaller getMarshaller() throws JAXBException, PropertyException {
        JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }
}
