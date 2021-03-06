package sample;

import java.sql.*;

class Withdrawal {

    /**
     * @brief Assigns a certain value to what the user types in, and subtracts their balance from the database by this number. Then the new balance is reassigned
     * @param acctNum The user's account number to make sure the correct balance is being used
     * @param amount A set amount the user choose (will range from 1 - 6)
     * @return The user's new balance if they have enough funds to make the transaction, otherwise it returns -1
     */
    double withdraw(int acctNum, int amount) {
        int moneyToWithdraw = 0;
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
        BalanceInquiry bi = new BalanceInquiry();
        double currentBalance = bi.displayBalance(acctNum);
        if (moneyToWithdraw > currentBalance) {
            return -1; //Will run code that will alert the user that they're trying to withdraw more than they have
        }
        System.out.println("Old balance: " + currentBalance);
        currentBalance = currentBalance - moneyToWithdraw;

        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res2/BankDatabase";
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
            String SQL = "INSERT INTO USERACCOUNT VALUES (?, ?)";
            String SQL2 = "DELETE FROM USERACCOUNT WHERE accountNum = (?)";

            //Delete previous balance
            pstmt = conn.prepareStatement(SQL2);
            pstmt.setInt(1,acctNum);
            pstmt.executeUpdate();

            //Add new balance
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,acctNum);
            pstmt.setDouble(2, currentBalance);
            pstmt.executeUpdate();

            // STEP 4: Clean-up environment
            pstmt.close();
            conn.close();
            System.out.println("New balance " + currentBalance);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return moneyToWithdraw;
    }
}
