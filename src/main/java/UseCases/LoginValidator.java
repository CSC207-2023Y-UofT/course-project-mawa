package UseCases;


import java.util.ArrayList;

public class LoginValidator {
    private int employeeID;
    private char[] password;

    private UserInteractor interactor = new UserInteractor();
    private UserFileReader empDB;

    public int validateCredentials(int empID, char[] pwd){
        empDB = UserFileReader.getInstance();
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
