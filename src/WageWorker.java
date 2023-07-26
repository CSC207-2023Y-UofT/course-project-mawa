package src;

public class WageWorker extends Employee{
    private float hourlyWage;

    public WageWorker(String surname, String firstname, String gender, String pronouns,
                      String email, String roleName, int empNum, int phoneNum, String dob,
                      int vacationDaysTaken, int totalVacationDaysAllowed, float wage, char[] password) {
        super(surname, firstname, gender, pronouns, email, roleName, empNum, phoneNum,
                dob, vacationDaysTaken, totalVacationDaysAllowed, password);
        this.hourlyWage = wage;
    }
}
