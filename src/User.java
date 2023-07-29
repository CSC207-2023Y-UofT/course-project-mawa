package src;

import java.time.LocalDate;
public abstract class User {
    private int employeeNum, phoneNum;
    private String surname, firstname, gender, pronouns, email, roleName;
    private boolean active;
    LocalDate dob;//format should be "YYYY-MM-dd" as a String
    private char[] password;

    public User(String surname, String firstname, String gender, String email,
                String roleName, int empNum, int phoneNum, String dob, char[] password){
        this.employeeNum = empNum;
        this.phoneNum = phoneNum;
        this.surname = surname;
        this.firstname = firstname;
        this.gender = gender;
        this.email = email;
        this.roleName = roleName;
        this.dob = LocalDate.parse(dob);
        this.active = true;
        this.password = password;
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
    public char[] getPassword(){
        return password;
    }

    public void setPassword(char[] pwd){
        password = pwd;
    }
    @Override
    public String toString(){
        return firstname + " " + surname;
    }
}
