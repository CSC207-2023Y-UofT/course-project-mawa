package UseCases;


import java.util.ArrayList;

public class LoginValidator {
    private int employeeID;
    private char[] password;
    private UserFileReader empDB;

    public int validateCredentials(int empID, char[] pwd){
        empDB = UserFileReader.getInstance();
        ArrayList<Integer> allActiveUsers = empDB.getIds(true);
        for (int u : allActiveUsers){
            if (u == empID){
                if(empDB.getPassword(u) == pwd){
                    return u;
                }
                return -1;
            }
        }
        return -1;
    }
}
