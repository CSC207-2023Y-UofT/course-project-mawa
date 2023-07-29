import java.time.LocalDateTime;
import java.util.EmployeeDataBaseInteractor;
import java.util.HRDatabaseInteractor;
import java.util.User;


public class UserFactory{


    private User[] getAllUsers(){
        EmployeeDataBaseInteractor edb = new EmployeeDataBaseInteractor();
        users = edb.readData();
        HRDataBaseInteractor hrdb = new HRDataBaseInteractor();
        users.addAll(hrdb.readData());
        return users;
    }


    public Boolean[] verifyCredentials(int userNum, String password){

        users = this.getAllUsers();
        toReturn = new Boolean[2];
        for (User user: users){
            if (user.getUserNum() == userNum){
                if (user.getPassword().equals(password)){
                    toReturn[0] = True;
                } if (user instanceof HR){
                    toReturn[1] = True;
                }
            }
        }
        return toReturn;
    }

    public Volunteer makeUser(String surname, String firstname String dateOfBirth, String gender, int phoneNumber,
                              String email, Boolean active, String role, String emergencyContact, String address){

        return new Volunteer( name, dateOfBirth, gender, phoneNumber, email, active, role,
                LocalDateTime.now(), emergencyContact, address);
    }

    public Employee makeUser(int employeeNum, String name, LocalDateTime dateOfBirth, String gender, int phoneNumber,
                              String email, Boolean active, String role, String emergencyContact, String address,
                             double pay, Boolean salaried){

        if salaried{
            return new SalariedEmployee(employeeNum, name, dateOfBirth, gender, phoneNumber, email, active, role,
                    LocalDateTime.now(), emergencyContact, address, pay)
        } else{
            return new WageWorker(employeeNum, name, dateOfBirth, gender, phoneNumber, email, active, role,
                    LocalDateTime.now(), emergencyContact, address, pay)
        }

    }


}
