package ch.makery.address.util;

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
 */
public class XmlUtil <T> implements XmlHandler<T> {
    
    private final JAXBContext context;
    
    private XmlUtil (Class<? extends T> theClass) throws JAXBException {
        context = JAXBContext.newInstance(theClass);
    }
    
    public static XmlUtil getInstance(Class contextClass) throws JAXBException {
        return new XmlUtil<>(contextClass);
    }
    
    private Unmarshaller getUnmarsheller() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller;
    }

    private Marshaller getMarshaller() throws JAXBException, PropertyException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    @Override
    public void marshall(T wrapper, File outputFile) {
        try {
            Marshaller marshaller = getMarshaller();
            marshaller.marshal(wrapper, outputFile);
        } catch (JAXBException ex) {
            Logger.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public T unmarshall(File inputFile) {
        try {
            Unmarshaller unmarshaller = getUnmarsheller();
            T wrapper = (T) unmarshaller.unmarshal(inputFile);
            return wrapper;
        } catch (JAXBException ex) {
            Logger.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
