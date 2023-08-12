package UseCases;


import java.util.ArrayList;

/**
 * The LoginValidator class validates user credentials during the login process.
 */
public class LoginValidator {
    public UserFileReader empDB = UserFileReader.getInstance();;

    /**
     * Validates the provided employee ID and password.
     *
     * @param empID The employee ID to validate.
     * @param pwd   The password to validate as a character array (safer than a String).
     * @return The employee ID if credentials are valid, or -1 if invalid.
     */
    public int validateCredentials(int empID, char[] pwd){
        ArrayList<Integer> allActiveUsers = empDB.getIds(true);
        for (int u : allActiveUsers){
            if (u == empID){
                if(empDB.getPassword(u).length == pwd.length){
                    boolean equal_pwd = true;
                    for (int i = 0; i < pwd.length; i ++){
                        if (empDB.getPassword(u)[i] != pwd[i]){
                            equal_pwd = false;
                        }
                    }
                    if (equal_pwd){
                        return u;
                    }
                }
                return -1;
            }
        }
        return -1;
    }
}
