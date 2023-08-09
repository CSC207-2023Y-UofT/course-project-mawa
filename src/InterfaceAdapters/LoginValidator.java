package InterfaceAdapters;


import java.util.ArrayList;

public class LoginValidator {
    private int employeeID;
    private char[] password;
    private UserFileReader empDB;

    public int validateCredentials(int empID, char[] pwd){
        try{
            empDB = new UserFileReader(FileNameConstants.USER_FILE_NAME);
        } catch (InvalidFileNameException e){
            System.out.println("Invalid File Name.");
        }
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
