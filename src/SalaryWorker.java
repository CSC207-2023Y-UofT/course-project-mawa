

public class SalaryWorker extends Employee{
    private float yearlySalary;

    public SalaryWorker(String surname, String firstname, String gender,
                        String email, String roleName, int empNum, int phoneNum, String dob,
                        float salary, char[] password) {
        super(surname, firstname, gender, email, roleName, empNum, phoneNum,
                dob, password);
        this.yearlySalary = salary;
    }

    public void setYearlySalary(float yearlySalary){
        this.yearlySalary = yearlySalary;
    }

    public float getYearlySalary(){
        return yearlySalary;
    }
}
