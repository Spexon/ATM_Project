package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField accountNum;
    public TextField userPin;
    public Label stringAnswer;
    public Label stringAnswer2;
    public Button btn;
    public Button btn0;
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;
    public Button btn9;


    /**
     * @param event
     * @brief Gets user input from text boxes and matches with information on the database
     */
    @FXML
    private void handleButtonAction(MouseEvent event) {
        Login lg = new Login();
        MainMenu mm = new MainMenu();

        try {
            int acctNum = Integer.parseInt(accountNum.getText());
            int uPin = Integer.parseInt(userPin.getText());
            if(lg.userCredentials(acctNum, uPin)) {
                mm.displayOptions();
            }
            stringAnswer.setVisible(false);
            stringAnswer2.setVisible(false);
        } catch (NumberFormatException e) {
            stringAnswer.setVisible(true);
            stringAnswer2.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void keypad() {
        System.out.println("here");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { //Everything done here can also be done in FXML
        //btn.setOnAction(this::handleButtonAction);
    }
}
