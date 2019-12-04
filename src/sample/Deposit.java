package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Deposit {

    void depositCash(int acctNum, double moneyToDeposit) {

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
    }
}
