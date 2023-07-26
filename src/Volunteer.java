package src;

public class Volunteer extends Employee{
    private float weeklyHours;
    public Volunteer(String surname, String firstname, String gender, String pronouns,
                     String email, String roleName, int empNum, int phoneNum, String dob, char[] password,
                     float weeklyHours, int vacationDaysTaken) {
        super(surname, firstname, gender, pronouns, email, roleName, empNum, phoneNum,
                dob, vacationDaysTaken, 365, password);
        this.weeklyHours = weeklyHours;

    }
}
