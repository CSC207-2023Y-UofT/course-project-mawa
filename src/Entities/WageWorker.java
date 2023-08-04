package Entities;

public class WageWorker extends Employee{
    private float hourlyWage;

    public WageWorker(String surname, String firstname, String gender,
                      String email, String roleName, int empNum, long phoneNum, String dob,
                      float wage, char[] password) {
        super(surname, firstname, gender, email, roleName, empNum, phoneNum,
                dob, password);
        this.hourlyWage = wage;
    }

    public void setHourlyWage(float hourlyWage){
        this.hourlyWage = hourlyWage;
    }

    public float getHourlyWage(){
        return hourlyWage;
    }
}
