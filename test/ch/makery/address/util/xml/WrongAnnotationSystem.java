package ch.makery.address.util.xml;

import ch.makery.address.model.Person;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class WrongAnnotationSystem {

    private List<Person> persons;
    
    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }
    
    public void setPersons(List<Person> persons){
        this.persons = persons;
    }
}
