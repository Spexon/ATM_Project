/**
 * @Author Vladimir Hardy
 */
package sample;

import java.util.Scanner;

public class Withdrawal extends MainMenu{

    /**
     * @param input Scanner that allows for user input
     * @brief prompts the user to withdraw from their balance if sufficient funds are available
     */
    public void withdraw(Scanner input) {

        System.out.println("Please enter an amount you would like to withdraw: ");
        double withdrawAmount = input.nextDouble();
        BalanceInquiry bi = new BalanceInquiry();
        if(withdrawAmount > bi.getUserBalanceChecking()) {
            System.out.println("Insufficient funds");
        }
        else {
            System.out.println("Withdrawing: " + withdrawAmount);
            bi.setUserBalanceChecking(bi.getUserBalanceChecking() - withdrawAmount);
        }
    }
}