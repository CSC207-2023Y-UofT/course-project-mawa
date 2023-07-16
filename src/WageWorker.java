public class WageWorker extends Employee{
    private float hourlyWage;

    public WageWorker(String surname, String firstname, String gender, String pronouns,
                      String email, String roleName, int empNum, int phoneNum, String dob,
                      int vacationDaysRemaining, int totalVacationDaysAllowed, float wage) {
        super(surname, firstname, gender, pronouns, email, roleName, empNum, phoneNum,
                dob, vacationDaysRemaining, totalVacationDaysAllowed);
        this.hourlyWage = wage;
    }
}
