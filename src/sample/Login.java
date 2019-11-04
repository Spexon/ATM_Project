/**
 * @Author Vladimir Hardy
 * @TODO set up and connect a database for accountNum and userPin
 */
package sample;


public class Login {

    private int accountNum;
    private int userPin;

    /**
     * @return true if the user matches a pin and account number on database (not set up yet)
     * @brief Prompts the user to login and grants access to account based on their credentials through the GUI interface
     */
    public boolean userCredentials(int username, int password) {
        //System.out.println(username + " " + password);
        if(username == accountNum && password == userPin) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * @return accountNum
     * @brief getter for account num
     */
    public int getAccountNum() {
        return accountNum;
    }

    /**
     * @param accountNum
     * @brief
     */
    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * @return
     * @brief
     */
    public int getUserPin() {
        return userPin;
    }

    /**
     * @param userPin
     * @brief
     */
    public void setUserPin(int userPin) {
        this.userPin = userPin;
    }
}
