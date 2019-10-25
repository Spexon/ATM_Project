/**
 * @Author Vladimir Hardy
 */
package sample;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {

    private int accountNum;
    private int userPin;

    /**
     * @param input Scanner that allows for user input
     * @return true if the user matches a pin and account number on database
     * @brief Prompts the user to login and grants access to account based on their credentials
     */
    public boolean userCredentials(Scanner input) {

        boolean reLoop;
        boolean reLoopLogin;
        do {
            System.out.println("Please enter your account number below:");
            do {
                try {
                    accountNum = input.nextInt();
                    //check if accountNum matches whats on the database
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter a number and try again");
                    reLoop = true;
                    input.nextLine();
                    continue;
                }
                reLoop = false;
            }
            while (reLoop);
            System.out.println("Please enter your pin below:");
            do {
                try {
                    userPin = input.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter a number and try again");
                    reLoop = true;
                    input.nextLine();
                    continue;
                }
                reLoop = false;
            }
            while (reLoop);

            int accountNumDB = 0; //Connect database to these values and set them appropriately
            int userPinDB = 0;
            if (accountNum == accountNumDB && userPin == userPinDB) {
                System.out.println("Access Granted");
                reLoopLogin = false;
            } else {
                System.out.println("Incorrect Account number or Pin, please try again");
                reLoopLogin = true;
            }
        }
        while(reLoopLogin);
        return true;
    }

    /**
     * @return accountNum
     * @brief getter for account num
     */
    public int getAccountNum() {
        return accountNum;
    }

    /**
     * @param accountNum
     * @brief
     */
    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * @return
     * @brief
     */
    public int getUserPin() {
        return userPin;
    }

    /**
     * @param userPin
     * @brief
     */
    public void setUserPin(int userPin) {
        this.userPin = userPin;
    }
}
