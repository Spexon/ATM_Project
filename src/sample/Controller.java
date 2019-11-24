package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class Controller implements Initializable, Screen {

    public PasswordField accountNum;
    public PasswordField userPin;
    public Label stringAnswer;
    public Label stringAnswer2;
    private int btnClicked;
    private int acctNum;
    private Boolean goToAcctNum = true;
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
            if(goToAcctNum) {
                acctNum = Integer.parseInt(accountNum.getText());
            }
            else {
                int uPin = Integer.parseInt(userPin.getText());
                if (lg.userCredentials(acctNum, uPin)) {
                    mm.displayOptions();
                }
                stringAnswer.setVisible(false);
                stringAnswer2.setVisible(false);
            }
        } catch (NumberFormatException e) {
            stringAnswer.setVisible(true);
            stringAnswer2.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }

        goToAcctNum = false;
    }

    public void keypad() {
        if(btnClicked == 0) {
            System.out.println(btn0.getText());
            displayNumber(btn0.getText());
        }
        else if(btnClicked == 1) {
            System.out.println(btn1.getText());
            displayNumber(btn1.getText());
        }
        else if(btnClicked == 2) {
            System.out.println(btn2.getText());
            displayNumber(btn2.getText());
        }
        else if(btnClicked == 3) {
            System.out.println(btn3.getText());
            displayNumber(btn3.getText());
        }
        else if(btnClicked == 4) {
            System.out.println(btn4.getText());
            displayNumber(btn4.getText());
        }
        else if(btnClicked == 5) {
            System.out.println(btn5.getText());
            displayNumber(btn5.getText());
        }
        else if(btnClicked == 6) {
            System.out.println(btn6.getText());
            displayNumber(btn6.getText());
        }
        else if(btnClicked == 7) {
            System.out.println(btn7.getText());
            displayNumber(btn7.getText());
        }
        else if(btnClicked == 8) {
            System.out.println(btn8.getText());
            displayNumber(btn8.getText());
        }
        else if(btnClicked == 9) {
            System.out.println(btn9.getText());
            displayNumber(btn9.getText());
        }
        else {
            System.out.println("An error has occurred");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { //Everything done here can also be done in FXML
        btn0.setOnAction(e->btnClicked=0);
        btn1.setOnAction(e->btnClicked=1);
        btn2.setOnAction(e->btnClicked=2);
        btn3.setOnAction(e->btnClicked=3);
        btn4.setOnAction(e->btnClicked=4);
        btn5.setOnAction(e->btnClicked=5);
        btn6.setOnAction(e->btnClicked=6);
        btn7.setOnAction(e->btnClicked=7);
        btn8.setOnAction(e->btnClicked=8);
        btn9.setOnAction(e->btnClicked=9);
    }

    @Override
    public void displayNumber(String btnValue) {
        if(goToAcctNum) {
            accountNum.appendText(btnValue);
        }
        else {
            userPin.appendText(btnValue);
        }
    }
}
