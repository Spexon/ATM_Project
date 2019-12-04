/**
 * @Author Vladimir Hardy
 */
package sample;

import java.sql.*;

public class BalanceInquiry {

    /**
     * @brief Displays the current user's balance
     * @param acctNum Makes sure the user is being displayed the balance associated to their account
     * @return The users balance from the database.
     */
    double displayBalance(int acctNum) {

        double balance = 0;
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
            String SQL = "SELECT TOTALBALANCE FROM UserAccount WHERE ACCOUNTNUM = (?)";

            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,acctNum);
            pstmt.executeQuery();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                balance = rs.getDouble("TOTALBALANCE");
            }

            // STEP 4: Clean-up environment
            pstmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

}
