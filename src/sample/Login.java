/**
 * @Author Vladimir Hardy
 */
package sample;


import java.sql.*;
import java.util.ArrayList;

public class Login {

    /**
     * @return true if the user matches a pin and account number on database (not set up yet)
     * @brief Prompts the user to login and grants access to account based on their credentials through the GUI interface
     */
    public boolean userCredentials(int username, int password) {
        //System.out.println(username + " " + password);
        ArrayList<Integer> accountNum = new ArrayList<>();
        ArrayList<Integer> userPin = new ArrayList<>();

        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res2/BankDatabase";
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

            String SQL = "SELECT * FROM BankDBTable";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                accountNum.add(rs.getInt("accountNum"));
                userPin.add(rs.getInt("userPin"));
            }

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < accountNum.size(); i++) {
            if (username == accountNum.get(i) && password == userPin.get(i)) {
                System.out.println("Login Successful");
                return true;
            }
        }
        System.out.println("Incorrect Login");
        return false;
    }
}