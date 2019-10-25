/**
 * @Author Vladimir Hardy
 * @todo fix try/catch surrounding userChoice
 */
package sample;

import java.util.InputMismatchException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class MainMenu {
    /**
     * @param input Scanner that allows for user input
     * @brief Displays different options for the user to select and sends them to that class
     */
    public void displayOptions(Scanner input) {

        boolean restart = true;
        while(restart) {
            System.out.println("Please select one of the options below:");
            System.out.println("1. Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");

            int userChoice = 0;
            boolean reLoop;
            do {
                try {
                    userChoice = input.nextInt();
                } catch (InputMismatchException ex) { //Catch doesnt work, InvocationTargetException gets thrown
                    reLoop = true;
                    input.nextInt(); //clears old input
                    continue;
                }
                reLoop = false;
            }
            while (reLoop);
            switch (userChoice) {
                case 1:
                    BalanceInquiry bi = new BalanceInquiry();
                    bi.displayBalance();
                    break;
                case 2:
                    Withdrawal wd = new Withdrawal();
                    wd.withdraw(input);
                    break;
                case 3:
                    Deposit dp = new Deposit();
                    dp.deposit(input);
                    break;
                case 4:
                    System.out.println("Have a nice day!");
                    restart = false;
                    break;
                default:
                    System.out.println("Please select a number in the given range");
            }
        }
    }
}