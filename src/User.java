import java.time.LocalDate;
public abstract class User {
    private int employeeNum, phoneNum;
    private String surname, firstname, gender, pronouns, email, roleName;
    private boolean active;
    LocalDate dob;//format should be "YYYY-MM-dd" as a String

    public User(String surname, String firstname, String gender, String pronouns, String email,
                String roleName, int empNum, int phoneNum, String dob){
        this.employeeNum = empNum;
        this.phoneNum = phoneNum;
        this.surname = surname;
        this.firstname = firstname;
        this.gender = gender;
        this.pronouns = pronouns;
        this.email = email;
        this.roleName = roleName;
        this.dob = LocalDate.parse(dob);
        this.active = true;
    }
    public int getEmployeeNum(){
        return employeeNum;
    }

    public int getPhoneNum(){
        return phoneNum;
    }

    public void setPhoneNum(int pn){
        this.phoneNum = pn;
    }
    public String[] getEmployeeName(){
        return new String[]{surname, firstname};
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String g){
        this.gender = g;
    }

    public String getPronouns(){
        return pronouns;
    }

    public String setPronouns(){
        return pronouns;
    }

    public String getEmployeeEmail(){
        return email;
    }

    public void setEmployeeEmail(String email){
        this.email = email;
    }

    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String rn){
        this.roleName = rn;
    }
    public LocalDate getDob(){
        return dob;
    }


    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
