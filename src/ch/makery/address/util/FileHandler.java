package ch.makery.address.util;

import java.io.File;
import java.util.List;

/**
 *
 * @author Paulo Vitor
 * @param <T>
 */
public interface FileHandler<T> {
    
    public void saveDataToFile(File file, List<T> personData);
    public void loadDataFromFile(File file, List<T> personData);
    
    public List<T> getDataFromFile(File file);
    
    public File getFilePath();
    public void setFilePath(File file);
}
