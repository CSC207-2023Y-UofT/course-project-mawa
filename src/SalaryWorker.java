public class SalaryWorker extends Employee{
    private float yearlySalary;

    public SalaryWorker(String surname, String firstname, String gender, String pronouns,
                        String email, String roleName, int empNum, int phoneNum, String dob,
                        int vacationDaysRemaining, int totalVacationDaysAllowed, float salary) {
        super(surname, firstname, gender, pronouns, email, roleName, empNum, phoneNum,
                dob, vacationDaysRemaining, totalVacationDaysAllowed);
        this.yearlySalary = salary;
    }
}
