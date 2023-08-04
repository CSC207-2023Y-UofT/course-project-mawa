package Entities;

public class HR extends User{

    public HR(String surname, String firstname, String gender,
              String email, String roleName, int empNum, long phoneNum, String dob, char[] password) {
        super(surname, firstname, gender, email, roleName, empNum, phoneNum, dob, password);

    }
}
