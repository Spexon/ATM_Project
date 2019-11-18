/**
 * @Author Vladimir Hardy
 * @TODO set up and connect a database for accountNum and userPin
 */
package sample;


import java.sql.*;
import java.util.ArrayList;

public class Login {

    //private int accountNum;
    //private int userPin;

    /**
     * @return true if the user matches a pin and account number on database (not set up yet)
     * @brief Prompts the user to login and grants access to account based on their credentials through the GUI interface
     */
    public boolean userCredentials(int username, int password) {
        System.out.println(username + " " + password);
        ArrayList<Integer> accountNum = new ArrayList<>();
        ArrayList<Integer> userPin = new ArrayList<>();

        final String JDBC_DRIVER = "org.h2.Driver";   //Try changing .FGCU-STUDENT on home computer
        final String DB_URL = "jdbc:h2:C:/Users/vvhardy0143.FGCU-STUDENT/OneDrive - Florida Gulf Coast University/COP 3003/ATM_Project/res2";
        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn;
        Statement stmt = null;

        try {

            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            String SQL = "SELECT * FROM BankDatabase";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                accountNum.add(rs.getInt("accountNum"));
                userPin.add(rs.getInt("userPin"));
            }
            System.out.println(userPin.get(0));

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if(username == accountNum.get(0) && password == userPin.get(0)) {
            return true;
        }
        else {
            return false;
        }
    }
}
