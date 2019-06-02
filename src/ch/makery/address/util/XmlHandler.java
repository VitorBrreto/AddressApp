/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.util;

import java.io.File;

/**
 *
 * @author Paulo Vitor
 * @param <T>
 */
public interface XmlHandler<T> {
    public void marshall(T wrapper, File outputFile);
    
    public T unmarshall(File inputFile);
    
}
