import Entities.Employee;
import Entities.User;
import InterfaceAdapters.UserInteractor;

import java.util.ArrayList;

public class LoginValidator {
    private int employeeID;
    private char[] password;
    private UserInteractor empDB = new UserInteractor();

    public User validateCredentials(int empID, char[] pwd){
        ArrayList<User> allActiveUsers = empDB.readData();
        for (User u : allActiveUsers){
            if (u.getEmployeeNum() == empID){
                if(u.getPassword() == pwd){
                    return u;
                }
                return new Employee("","","","","incorrect password",
                        -1, 0, "",0,0,new char[]{});
            }
        }
        return new Employee("","","","","no employee found",
                0, 0, "",0,0,new char[]{});
    }
}
