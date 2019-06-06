package ch.makery.address.util.xml;

import java.io.File;

/**
 *
 * @author Paulo Vitor
 * @param <T>
 */
public interface XmlHandler<T> {
    public void marshall(T wrapper, File outputFile) throws MarshalException;
    
    public T unmarshall(File inputFile) throws UnmarshalException;
    
}
