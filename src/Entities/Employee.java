package Entities;

public class Employee extends User{

    private String role;
    public Employee(String surname, String firstname, String gender,
                    String email, String roleName, int empNum,
                    long phoneNum, String dob, char[] password) {
        super(surname, firstname, gender, email, empNum, phoneNum, dob, password);
        this.role = roleName;

    }

    public String getRoleName(){
        return role;
    }

    public void setRoleName(String rn){
        this.role = rn;
    }

}
