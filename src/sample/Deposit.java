package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Deposit {

    /**
     * @brief gets any number a user inputs and adds this number to their current balance
     * (in a real scenario, the amount they deposit in the slot gets read and added to the database)
     * @param acctNum user's account number, to make sure the correct account is being added to
     * @param moneyToDeposit the amount the user decided to add to their account (can be any number)
     */
    double depositCash(int acctNum, double moneyToDeposit) {

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
            BalanceInquiry bi = new BalanceInquiry();
            double currentBalance = bi.displayBalance(acctNum);
            System.out.println("Old balance: " + currentBalance);
            currentBalance = moneyToDeposit + currentBalance;


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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return moneyToDeposit;
    }
}
