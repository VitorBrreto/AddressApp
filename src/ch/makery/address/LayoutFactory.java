/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.makery.address;

import ch.makery.address.view.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Paulo Vitor
 */
public abstract class LayoutFactory <L extends FXMLLoader> {
    private FXMLLoader loader;
    private L pane;
    Controller controller;
    
    public void showPersonOverview() throws IOException {
            setLoader();
            pane = (L) loader.load();
            setInScreen();
            controller = loader.getController();
            startController();
    }

    public void setLoader() {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/PersonOverview.fxml"));
    }
    
    abstract void setInScreen();
    abstract void startController();
}
