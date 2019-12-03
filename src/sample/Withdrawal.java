package sample;

import java.sql.*;

public class Withdrawal {

    private int moneyToWithdraw;

    double withdraw(int acctNum, int amount) {
        double currentBalance;
        switch(amount) {
            case 1:
                moneyToWithdraw = 20;
                break;
            case 2:
                moneyToWithdraw = 40;
                break;
            case 3:
                moneyToWithdraw = 60;
                break;
            case 4:
                moneyToWithdraw = 100;
                break;
            case 5:
                moneyToWithdraw = 200;
                break;
            case 6:
                moneyToWithdraw = 500;
                break;
            default:
                System.out.println("Something went wrong in withdraw switch-case");
        }
        System.out.println("Withdraw: " + moneyToWithdraw);
        BalanceInquiry bi = new BalanceInquiry();
        currentBalance = bi.displayBalance(acctNum);
        if (moneyToWithdraw > currentBalance) {
            return -1; //Will run code that will alert the user that they're trying to withdraw more than they have
        }
        else {
            currentBalance = currentBalance - moneyToWithdraw;
        }

        return currentBalance;
    }

    void withdrawFromDB(int acctNum) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:C:/Users/Windows/OneDrive - Florida Gulf Coast University/COP 3003/ATM_Project2/res2/BankDatabase";
        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn;
        PreparedStatement pstmt;
        try {

            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            BalanceInquiry bi = new BalanceInquiry();
            double currentBalance = bi.displayBalance(acctNum);
            currentBalance = currentBalance - moneyToWithdraw;

            String SQL = "INSERT INTO USERACCOUNT VALUES (?, ?)";

            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,acctNum);
            pstmt.setDouble(2, currentBalance);
            pstmt.executeUpdate();

            // STEP 4: Clean-up environment
            pstmt.close();
            conn.close();
            System.out.println(currentBalance);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
