/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.util.xml;

/**
 *
 * @author Paulo Vitor
 */
public class UnmarshallerCreationException extends Exception {

    /**
     * Creates a new instance of <code>UnmarshallerCreationException</code>
     * without detail message.
     */
    public UnmarshallerCreationException() {
    }

    /**
     * Constructs an instance of <code>UnmarshallerCreationException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public UnmarshallerCreationException(String msg) {
        super(msg);
    }
}
