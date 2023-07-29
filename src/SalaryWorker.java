package src;

public class SalaryWorker extends Employee{
    private float yearlySalary;

    public SalaryWorker(String surname, String firstname, String gender,
                        String email, String roleName, int empNum, int phoneNum, String dob,
                        int vacationDaysTaken, int totalVacationDaysAllowed, float salary, char[] password) {
        super(surname, firstname, gender, email, roleName, empNum, phoneNum,
                dob, vacationDaysTaken, totalVacationDaysAllowed, password);
        this.yearlySalary = salary;
    }
}
