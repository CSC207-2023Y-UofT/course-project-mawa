import java.until.EmployeeDataBaseInteractor;


public class UserFactory{

    public Boolean verifyCredentials(int employeeNum, String password){
        EmployeeDataBaseInterActor edb = new EmployeeDataBaseInteractor();
        employees = edb.listEmployees();

    }

    public Volunteer makeUser(int employeeNum, String name, LocalDateTime dateOfBirth, String gender, int phoneNumber,
                              String email, Boolean active, String role, String emergencyContact, String address){

        return new Volunteer(employeeNum, name, dateOfBirth, gender, phoneNumber, email, active, role,
                LocalDateTime.now(), emergencyContact, address)
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
