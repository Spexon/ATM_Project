/**
 * @Author Vladimir Hardy
 * @TODO Connect userBalance to a database
 */
package sample;

public class BalanceInquiry extends MainMenu {

    private double userBalanceChecking = 10;
    private double userBalanceSavings = 50;

    /**
     * @brief displays the users current balance for checking and savings
     */
    public void displayBalance() {
        System.out.println("Checking: $" + userBalanceChecking);
        System.out.println("Savings: $" + userBalanceSavings);
    }

    /**
     * @return
     * @brief
     */
    public double getUserBalanceChecking() {
        return userBalanceChecking;
    }

    /**
     * @return
     * @brief
     */
    public void setUserBalanceChecking(double userBalanceChecking) {
        this.userBalanceChecking = userBalanceChecking;
    }

    /**
     * @return
     * @brief
     */
    public double getUserBalanceSavings() {
        return userBalanceSavings;
    }

    /**
     * @return
     * @brief
     */
    public void setUserBalanceSavings(double userBalanceSavings) {
        this.userBalanceSavings = userBalanceSavings;
    }
}