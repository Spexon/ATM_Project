/**
 * @Author Vladimir Hardy
 * This Project is following a tutorial
 */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Main extends Application {

    /**
     * @param primaryStage
     * @throws Exception
     * @brief Gets styles from sample.fxml and prints hello World
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ATM");
        primaryStage.setScene(new Scene(root, 441, 416));
        primaryStage.show();
    }

    /**
     * @param args
     * @throws SQLException
     * @brief the main method for the ATM, calls other classes here
     */
    public static void main(String[] args) throws SQLException {
        launch(args);


    }
}