/**
 * Number Systems.
 * Calculator to solve the sum and product of two numbers (in base 2, 10, or 16)
 * and displays the answers in formatted binary, decimal, and hexadecimal. Application is with
 * GUI made with javafx.
 *
 * @author Will Nigel De Jesus
 * @version 1
 */

package com.project.numbersystems;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindow extends Application {
    /**
     * Sets the scene and stage for the javafx GUI.
     * @param stage Main window of the javafx application.
     * @throws Exception Handles errors so that application will not crash.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindowInterface.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Number Systems");

        stage.show();
    }

    /**
     * Launches the calculator application GUI.
     */
    public void run() {
        launch();
    }
}