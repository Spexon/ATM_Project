package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField accountNum;
    public TextField userPin;
    public Button btn;
    public Label stringAnswer;
    public Label stringAnswer2;


    /**
     * @param event
     * @brief Gets user input from text boxes and matches with information on the database
     */
    @FXML
    private void handleButtonAction(MouseEvent event) {
        Login lg = new Login();

        try {
            int acctNum = Integer.parseInt(accountNum.getText());
            int uPin = Integer.parseInt(userPin.getText());
            System.out.println(lg.userCredentials(acctNum,uPin));
            stringAnswer.setVisible(false);
            stringAnswer2.setVisible(false);
            btn.setText("Submitted");
        }
        catch(NumberFormatException e) {
            stringAnswer.setVisible(true);
            stringAnswer2.setVisible(true);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { //Everything done here can also be done in FXML
        //btn.setOnAction(this::handleButtonAction);
    }
}
