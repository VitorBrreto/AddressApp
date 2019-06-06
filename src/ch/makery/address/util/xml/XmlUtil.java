package ch.makery.address.util.xml;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

/**
 *
 * @author Paulo Vitor
 * @param <T>
 */
public class XmlUtil <T> implements XmlHandler<T> {
    
    private final JAXBContext context;
    
    public XmlUtil (Class<T> classToBeBound) throws UnboundableClassException {
        try {
            context = JAXBContext.newInstance(classToBeBound);
        } catch (JAXBException ex) {
            throw new UnboundableClassException();
        }
    }
    
    private Unmarshaller getUnmarsheller() throws UnmarshallerCreationException {
        try {
            return tryGetUnmarshaller();
        } catch (JAXBException ex) {
            throw new UnmarshallerCreationException();
        }
    }

    private Unmarshaller tryGetUnmarshaller() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller;
    }

    private Marshaller getMarshaller() throws MarshallerCreationException {
        try {
            return tryGetMarshaller();
        } catch (JAXBException ex) {
            throw new MarshallerCreationException(ex.getMessage());
        }
    }

    private Marshaller tryGetMarshaller() throws JAXBException, PropertyException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    @Override
    public void marshall(T wrapper, File outputFile) throws MarshalException {
        try {
            Marshaller marshaller = getMarshaller();
            marshaller.marshal(wrapper, outputFile);  
        } catch (MarshallerCreationException | JAXBException ex) {
            throw new MarshalException(ex.getLocalizedMessage());
        }
        
    }

    @Override
    public T unmarshall(File inputFile) throws UnmarshalException{
        try {
            Unmarshaller unmarshaller = getUnmarsheller();
            T wrapper = (T) unmarshaller.unmarshal(inputFile);
            return wrapper;
        } catch (UnmarshallerCreationException | JAXBException ex) {
            throw new UnmarshalException(ex.getLocalizedMessage());
        }
    }
}
