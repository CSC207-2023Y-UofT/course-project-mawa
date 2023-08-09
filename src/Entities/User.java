package Entities;

import java.io.Serializable;
import java.time.LocalDate;
public class User implements Serializable {
    private int userNum;
    private long phoneNum;
    private String surname, firstname, gender, email, roleName;
    private boolean active;
    LocalDate dob;//format should be "YYYY-MM-dd" as a String
    private char[] password;
    private UserEnum type;
    private float pay;

    public User(String surname, String firstname, String gender, String email,
                String roleName, int uNum, long phoneNum, String dob, char[] password,
                UserEnum type, float pay){
        this.userNum = uNum;
        this.phoneNum = phoneNum;
        this.surname = surname;
        this.firstname = firstname;
        this.gender = gender;
        this.email = email;
        this.roleName = roleName;
        this.dob = LocalDate.parse(dob);
        this.active = true;
        this.password = password;
        this.type = type;
        this.pay = pay;
    }


    public String getEmail(){return email;}
    public String getSurname(){return surname;}

    public String getFirstname(){return firstname;}
    public int getUserNum(){
        return userNum;
    }

    public long getPhoneNum(){
        return phoneNum;
    }

    public void setPhoneNum(long pn){
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

    public void setEmail(String email){
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

    public void setPay(float pay){
        this.pay = pay;
    }

    public float getPay(){
        return this.pay;
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
    public UserEnum getType(){
        return this.type;
    }
    @Override
    public String toString(){
        return firstname + " " + surname;
    }
}
