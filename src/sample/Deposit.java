/**
 * @Author Vladimir Hardy
 */
package sample;

import java.util.Scanner;

public class Deposit extends MainMenu {

    /**
     * @param input Scanner that allows for user input
     * @brief prompts the user to deposit to checking or savings and updates balance
     */
    public void deposit(Scanner input) {

        BalanceInquiry bi = new BalanceInquiry();
        System.out.println("Please select checking or savings: ");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int userChoice = input.nextInt();
        double depositAmount;
        switch(userChoice) {
            case 1: //Checking
                System.out.println("Please enter an amount you would like to deposit: ");
                depositAmount = input.nextDouble();
                System.out.println("You have deposited: " + depositAmount);
                bi.setUserBalanceChecking(bi.getUserBalanceChecking() + depositAmount);
                System.out.println("Your new balance is: $" + bi.getUserBalanceChecking());
                break;
            case 2: //Savings
                System.out.println("Please enter an amount you would like to deposit: ");
                depositAmount = input.nextDouble();
                System.out.println("You have deposited: " + depositAmount);
                bi.setUserBalanceSavings(bi.getUserBalanceSavings() + depositAmount);
                System.out.println("Your new balance is: $" + bi.getUserBalanceSavings());
                break;
            default:
                System.out.println("Please select a number in the given range");
        }
    }
}