/**
 * @Author Vladimir Hardy
 */
package sample;

import java.sql.*;

public class BalanceInquiry {

    public double displayBalance(int acctNum) {
        System.out.println("In Balance");
        double balance = 0;
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:C:/Users/Windows/OneDrive - Florida Gulf Coast University/COP 3003/ATM_Project2/res2/BankDatabase";
        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn;
        Statement stmt;
        try {

            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            //STEP 3: Execute a query

            String SQL = "SELECT TOTALBALANCE FROM userInformation WHERE ACCOUNTNUM = " + acctNum;
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                balance = rs.getDouble(SQL);
            }

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

}
