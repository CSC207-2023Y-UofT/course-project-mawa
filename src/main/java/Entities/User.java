package Entities;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * The User class represents a user (employee or HR) in the system.
 */
public class User implements Serializable {
    private int userNum;
    private long phoneNum;
    private String surname, firstname, gender, email;
    private boolean active;
    LocalDate dob;//format should be "YYYY-MM-dd" as a String
    private char[] password;
    private String type;

    private String role;
    private float pay;
    /**
     * Constructs a User instance with the specified parameters.
     *
     * @param surname    The surname of the user.
     * @param firstname  The first name of the user.
     * @param gender     The gender of the user.
     * @param email      The email address of the user.
     * @param roleName   The role name of the user.
     * @param uNum       The unique user number.
     * @param phoneNum   The phone number of the user.
     * @param dob        The date of birth of the user in "YYYY-MM-dd" format.
     * @param password   The password of the user.
     * @param type       The type of user (e.g., HR, Salary Worker, etc.).
     * @param pay        The pay rate of the user.
     */
    public User(String surname, String firstname, String gender, String email,

                String roleName, int uNum, long phoneNum, String dob, char[] password,
                String type, float pay){

        this.userNum = uNum;
        this.phoneNum = phoneNum;
        this.surname = surname;
        this.firstname = firstname;
        this.gender = gender;
        this.email = email;
        this.dob = LocalDate.parse(dob);
        this.active = true;
        this.password = password;
        this.type = type;
        this.pay = pay;
        this.role = roleName;
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

    public String getRole() {return this.role;}

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
    public String getType(){
        return this.type;
    }
    @Override
    public String toString(){
        return firstname + " " + surname;
    }
}
