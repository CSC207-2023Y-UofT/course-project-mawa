
public class Employee extends User{
    private int vacationDaysTaken;
    private int totalVacationDaysAllowed;
    public Employee(String surname, String firstname, String gender,
                    String email, String roleName, int empNum,
                    int phoneNum, String dob, int vacationDaysTaken,
                    int totalVacationDaysAllowed, char[] password) {
        super(surname, firstname, gender, email, roleName, empNum, phoneNum, dob, password);
        this.vacationDaysTaken = vacationDaysTaken;
        this.totalVacationDaysAllowed = totalVacationDaysAllowed;
    }

    public boolean addVacationDaysUsed(int daysUsed){
        vacationDaysTaken += daysUsed;
        return (totalVacationDaysAllowed - vacationDaysTaken) > 0;
    }

    public void resetVacationDaysTaken(){
        vacationDaysTaken = 0;
    }

    public int getVacationDaysUsed(){
        return vacationDaysTaken;
    }

    public void setTotalVacationDaysAllowed(int totalDays){
        totalVacationDaysAllowed = totalDays;
    }

    public int getTotalVacationDaysAllowed(){
        return totalVacationDaysAllowed;
    }
}
