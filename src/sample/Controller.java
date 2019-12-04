/**
 * @Author Vladimir Hardy
 */
package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.InputMismatchException;
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
    public Label confirmationLabel;
    public Label balToWithdraw;
    public Button confirmationNo;
    public Button confirmationYes;
    public Label depositCashLabel;
    public Label depositAmountLabel;
    public TextField depositAmount;
    public Button helpButton;
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
                accountNum.setOpacity(0.3);
                accountNum.setDisable(true);
                userPin.setDisable(false);
                userPin.setOpacity(1);
                return;
            }
            else if (goToNextTextField == 1) {
                accountNum.setOpacity(1);
                accountNum.setDisable(false);
                userPin.setDisable(true);
                userPin.setOpacity(0.3);
                int uPin = Integer.parseInt(userPin.getText());
                if (lg.userCredentials(acctNum, uPin)) {
                    displayMenuOptions();
                    return;
                } else {
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
                        if (!withdrawalChoice.getText().equals("")) {
                            if (Integer.parseInt(withdrawalChoice.getText()) < 7) {

                                double withdrawn = wd.withdraw(Integer.parseInt(accountNum.getText()), Integer.parseInt(withdrawalChoice.getText()));
                                if (withdrawn > 0) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully withdrawn $"+withdrawn+" from your account",ButtonType.OK);
                                    alert.show();
                                    displayMenuOptions();
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Insufficient funds in your bank account", ButtonType.OK);
                                    alert.show();
                                }
                                withdrawalChoice.clear();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a number within the given range",ButtonType.OK);
                                alert.show();
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
                        if(!depositAmount.getText().equals("")) {
                            double deposit = dp.depositCash(acctNum, Double.parseDouble(depositAmount.getText()));
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added $" + deposit + " to your account",ButtonType.OK);
                            alert.show();
                            displayMenuOptions();
                        }
                        break;
                    case 4:
                        returnToStart();
                        return;
                    default:
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a number within the given range",ButtonType.OK);
                        alert.show();
                        mmChoice.clear();
                }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a number within the given range",ButtonType.OK);
            alert.show();
            accountNum.clear();
            userPin.clear();
        }
        catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please refrain from using letters in this step",ButtonType.OK);
            alert.show();
            accountNum.clear();
            userPin.clear();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Something went wrong :/\nError in switch statement", ButtonType.OK);
            alert.show();
            accountNum.clear();
            userPin.clear();
            System.out.println(e);
        }

    }

    /**
     * @brief hides all the layers that may be shown, so that certain layers can overlap others
     */
    private void hideAllLayers() {
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
        mmChoice.clear();
        withdrawalChoice.clear();
        depositAmount.clear();
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
                helpButton.setVisible(true);
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
     * @brief if pressed, an alert box appears giving the user navigational information as well as sample accounts to login with
     */
    @FXML
    private void helpAlert() {
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION,"Navigation: To navigate this ATM shell, " +
                "use the keypad or your keyboard to enter values in the text boxes, then press enter to submit each " +
                "text entry individually on the keypad below.\n\nSample accounts: ---------------------------------------------------\n" +
                "Account number: 1    Pin: 1\nAccount number: 10  Pin: 4455 \nAccount number: 22  Pin: 5555",ButtonType.CLOSE);
        helpAlert.show();
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