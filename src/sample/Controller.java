/**
 * @Author Vladimir Hardy
 */
package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, Screen {

    public PasswordField accountNum;
    public PasswordField userPin;
    public Label acctNumLabel;
    public Label pinLabel;
    public Label welcomeLabel;
    public TextField mmChoice;
    public Label choiceLabel;
    public Label balanceLabel;
    public Label withdrawLabel;
    public Label fundsLabel;
    public Label exitLabel;
    public Label mainMenuLabel;
    public Label errorMessage1;
    public Label errorMessage2pt2;
    public Label errorMessage2pt1;
    public ImageView delete1;
    public ImageView delete2;
    public ImageView delete3;
    public ImageView delete4;
    public Label exitMessage;
    public Label currentBalLabel;
    public Label balanceToDisplay;
    public Button returnToMainMenuBtn;
    public Label withdrawalMenuLabel;
    public TextField withdrawalChoice;
    public Label label200;
    public Label label100;
    public Label label60;
    public Label label40;
    public Label label20;
    public Label selectWithdrawLabel;
    public Label label500;
    public Label errorMessage3;
    public Label confirmationLabel;
    public Label balToWithdraw;
    public Button confirmationNo;
    public Button confirmationYes;
    public Label depositCashLabel;
    public Label depositAmountLabel;
    public TextField depositAmount;
    private int btnClicked;
    private int acctNum;
    private int goToNextTextField = 0;
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
     * @brief Gets user input from TextFields and matches with information on the database, if information is correct,
     * the program continues to display more options for that specified user account.
     */
    @FXML
    private void handleButtonAction(MouseEvent event) {
        Login lg = new Login();
        BalanceInquiry bi = new BalanceInquiry();
        Withdrawal wd = new Withdrawal();
        Deposit dp = new Deposit();

        try {
            if (goToNextTextField == 0) {
                acctNum = Integer.parseInt(accountNum.getText());
                goToNextTextField = 1;
                return;
            }
            else if (goToNextTextField == 1) {
                int uPin = Integer.parseInt(userPin.getText());
                if (lg.userCredentials(acctNum, uPin)) {
                    displayMenuOptions();
                    return;
                } else {
                    //errorMessage1.setVisible(true);
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Login Information, Please try again", ButtonType.OK);
                    alert.show();
                    accountNum.clear();
                    userPin.clear();
                    goToNextTextField = 0;
                    return;
                }
            }

                int menuChoice = Integer.parseInt(mmChoice.getText());
                switch (menuChoice) {
                    case 1:
                        hideAllLayers();
                        balanceToDisplay.setText("$" + bi.displayBalance(acctNum));
                        currentBalLabel.setVisible(true);
                        balanceToDisplay.setVisible(true);
                        returnToMainMenuBtn.setVisible(true);
                        break;
                    case 2:
                        hideAllLayers();
                        goToNextTextField = 3;
                        label20.setVisible(true);
                        label40.setVisible(true);
                        label60.setVisible(true);
                        label100.setVisible(true);
                        label200.setVisible(true);
                        label500.setVisible(true);
                        withdrawalChoice.setVisible(true);
                        withdrawalMenuLabel.setVisible(true);
                        selectWithdrawLabel.setVisible(true);
                        returnToMainMenuBtn.setVisible(true);
                        delete4.setVisible(true);
                        if (!withdrawalChoice.getText().equals("")) {
                            if (Integer.parseInt(withdrawalChoice.getText()) < 7) {
                                errorMessage2pt1.setVisible(false);
                                errorMessage2pt2.setVisible(false);
                                if (wd.withdraw(Integer.parseInt(accountNum.getText()), Integer.parseInt(withdrawalChoice.getText())) > 0) {
                                    errorMessage3.setVisible(false);
                                    /*hideAllLayers();
                                    confirmationLabel.setVisible(true);
                                    balToWithdraw.setVisible(true);
                                    confirmationNo.setVisible(true);
                                    confirmationYes.setVisible(true);
                                    balToWithdraw.setText(wd.withdraw(Integer.parseInt(accountNum.getText()), Integer.parseInt(withdrawalChoice.getText())));
                                    if(confirmationYes.isPressed()) {
                                        System.out.println("yes");
                                        wd.withdrawFromDB(acctNum);
                                    }
                                    if(confirmationNo.isPressed()) {
                                        System.out.println("no");
                                        break;
                                    }*/
                                } else {
                                    errorMessage3.setVisible(true);
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Insufficient funds in your bank account", ButtonType.OK);
                                    alert.show();
                                }
                                withdrawalChoice.clear();
                            } else {
                                errorMessage2pt1.setVisible(true);
                                errorMessage2pt2.setVisible(true);
                                withdrawalChoice.clear();
                            }
                        }
                        break;
                    case 3:
                        hideAllLayers();
                        goToNextTextField = 4;
                        depositAmountLabel.setVisible(true);
                        depositCashLabel.setVisible(true);
                        depositAmount.setVisible(true);
                        returnToMainMenuBtn.setVisible(true);
                        dp.depositCash(acctNum, Double.parseDouble(depositAmount.getText()));
                        depositAmount.clear();
                        break;
                    case 4:
                        returnToStart();
                        return;
                    default:
                        errorMessage2pt1.setVisible(true);
                        errorMessage2pt2.setVisible(true);
                        mmChoice.clear();
                }
            System.out.println("End switch");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Error in Switch", ButtonType.OK);
            alert.show();
            System.out.println(e);
        }

    }

    /**
     * @brief deletes text for account number if the user presses the delete button
     */
    @FXML
    private void deleteAcctText() {
        accountNum.clear();
        goToNextTextField = 0;
    }

    /**
     * @brief deletes text for pin number if the user presses the delete button
     */
    @FXML
    private void deletePinText() {
        userPin.clear();
        goToNextTextField = 1;
    }

    /**
     * @brief deletes text for choice number if the user presses the delete button
     */
    @FXML
    private void deleteChoiceText() {
        mmChoice.clear();
        withdrawalChoice.clear();
    }

    /**
     * @brief hides all the layers that may be shown, so that certain layers can overlap others
     */
    private void hideAllLayers() {
        errorMessage1.setVisible(false);
        errorMessage2pt1.setVisible(false);
        errorMessage2pt2.setVisible(false);
        errorMessage3.setVisible(false);
        depositAmount.setVisible(false);
        depositCashLabel.setVisible(false);
        depositAmountLabel.setVisible(false);
        currentBalLabel.setVisible(false);
        balanceToDisplay.setVisible(false);
        currentBalLabel.setVisible(false);
        label20.setVisible(false);
        label40.setVisible(false);
        label60.setVisible(false);
        label100.setVisible(false);
        label200.setVisible(false);
        label500.setVisible(false);
        withdrawalChoice.setVisible(false);
        withdrawalMenuLabel.setVisible(false);
        selectWithdrawLabel.setVisible(false);
        returnToMainMenuBtn.setVisible(false);
        accountNum.setVisible(false);
        userPin.setVisible(false);
        welcomeLabel.setVisible(false);
        acctNumLabel.setVisible(false);
        pinLabel.setVisible(false);
        delete1.setVisible(false);
        delete2.setVisible(false);
        delete3.setVisible(false);
        delete4.setVisible(false);
        mainMenuLabel.setVisible(false);
        mmChoice.setVisible(false);
        balanceLabel.setVisible(false);
        choiceLabel.setVisible(false);
        fundsLabel.setVisible(false);
        withdrawLabel.setVisible(false);
        exitLabel.setVisible(false);
    }

    /**
     * @brief hides all login text and shows personal menu options for the user
     */
    @FXML
    private void displayMenuOptions() {
        hideAllLayers();
        mainMenuLabel.setVisible(true);
        mmChoice.setVisible(true);
        balanceLabel.setVisible(true);
        choiceLabel.setVisible(true);
        fundsLabel.setVisible(true);
        withdrawLabel.setVisible(true);
        exitLabel.setVisible(true);
        delete3.setVisible(true);
        mmChoice.clear();
        goToNextTextField = 2;
    }

    /**
     * @brief clears personal user information, and brings up login information for the new user after a short delay
     */
    private void returnToStart() {
        mainMenuLabel.setVisible(false);
        mmChoice.setVisible(false);
        balanceLabel.setVisible(false);
        choiceLabel.setVisible(false);
        fundsLabel.setVisible(false);
        withdrawLabel.setVisible(false);
        exitLabel.setVisible(false);
        delete3.setVisible(false);
        exitMessage.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                exitMessage.setVisible(false);
                accountNum.setVisible(true);
                userPin.setVisible(true);
                welcomeLabel.setVisible(true);
                acctNumLabel.setVisible(true);
                pinLabel.setVisible(true);
                delete1.setVisible(true);
                delete2.setVisible(true);
            }
        };
        timer.schedule(task,5000);
        accountNum.setText("");
        accountNum.clear();
        userPin.setText("");
        userPin.clear();
        goToNextTextField = 0;


    }

    /**
     * @brief sends the specified value pressed to displayNumber, which will get displayed in a TextField
     */
    public void keypad() {
        if (btnClicked == 0) {
            displayNumber(btn0.getText());
        } else if (btnClicked == 1) {
            displayNumber(btn1.getText());
        } else if (btnClicked == 2) {
            displayNumber(btn2.getText());
        } else if (btnClicked == 3) {
            displayNumber(btn3.getText());
        } else if (btnClicked == 4) {
            displayNumber(btn4.getText());
        } else if (btnClicked == 5) {
            displayNumber(btn5.getText());
        } else if (btnClicked == 6) {
            displayNumber(btn6.getText());
        } else if (btnClicked == 7) {
            displayNumber(btn7.getText());
        } else if (btnClicked == 8) {
            displayNumber(btn8.getText());
        } else if (btnClicked == 9) {
            displayNumber(btn9.getText());
        } else {
            System.out.println("An error has occurred in keypad");
        }
    }

    /**
     * @param location
     * @param resources
     * @brief prepares all the buttons in the keypad once something gets pressed.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) { //Everything done here should be possible to do in FXML
        userPin.setOnMouseClicked(e -> goToNextTextField = 1);
        accountNum.setOnMouseClicked(e -> goToNextTextField = 0);
        btn0.setOnAction(e -> btnClicked = 0);
        btn1.setOnAction(e -> btnClicked = 1);
        btn2.setOnAction(e -> btnClicked = 2);
        btn3.setOnAction(e -> btnClicked = 3);
        btn4.setOnAction(e -> btnClicked = 4);
        btn5.setOnAction(e -> btnClicked = 5);
        btn6.setOnAction(e -> btnClicked = 6);
        btn7.setOnAction(e -> btnClicked = 7);
        btn8.setOnAction(e -> btnClicked = 8);
        btn9.setOnAction(e -> btnClicked = 9);
        btn.setStyle("-fx-background-color: #aad7ff");
        btn0.setStyle("-fx-background-color: #aad7ff");
        btn1.setStyle("-fx-background-color: #aad7ff");
        btn2.setStyle("-fx-background-color: #aad7ff");
        btn3.setStyle("-fx-background-color: #aad7ff");
        btn4.setStyle("-fx-background-color: #aad7ff");
        btn5.setStyle("-fx-background-color: #aad7ff");
        btn6.setStyle("-fx-background-color: #aad7ff");
        btn7.setStyle("-fx-background-color: #aad7ff");
        btn8.setStyle("-fx-background-color: #aad7ff");
        btn9.setStyle("-fx-background-color: #aad7ff");
    }

    /**
     * @param btnValue is the number displayed on the button
     * @brief Moves the keypad input location to the next TextField once enter is pressed, and displays the specified
     * number in the TextField
     */
    @Override
    public void displayNumber(String btnValue) {
        if (goToNextTextField == 0) {
            accountNum.appendText(btnValue);
        } else if (goToNextTextField == 1) {
            userPin.appendText(btnValue);
        } else if (goToNextTextField == 2) {
            mmChoice.appendText(btnValue);
        }
        else if (goToNextTextField == 3){
            withdrawalChoice.appendText(btnValue);
        }
        else if (goToNextTextField == 4) {
            depositAmount.appendText(btnValue);
        }
        else {
            System.out.println("Create a new goToNextField block");
        }
    }
}