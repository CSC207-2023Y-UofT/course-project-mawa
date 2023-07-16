public class Employee extends User{
    private int vacationDaysRemaining;
    private int totalVacationDaysAllowed;
    public Employee(String surname, String firstname, String gender,
                    String pronouns, String email, String roleName, int empNum,
                    int phoneNum, String dob, int vacationDaysRemaining,
                    int totalVacationDaysAllowed) {
        super(surname, firstname, gender, pronouns, email, roleName, empNum, phoneNum, dob);
        this.vacationDaysRemaining = vacationDaysRemaining;
        this.totalVacationDaysAllowed = totalVacationDaysAllowed;
    }

    public boolean removeVacationDaysRemaining(int daysUsed){
        vacationDaysRemaining -= daysUsed;
        return (vacationDaysRemaining > 0);
    }

    public void resetVacationDaysRemaining(){
        vacationDaysRemaining = totalVacationDaysAllowed;
    }

    public int getVacationDaysRemaining(){
        return vacationDaysRemaining;
    }

    public void setTotalVacationDaysAllowed(int totalDays){
        totalVacationDaysAllowed = totalDays;
    }

    public int getTotalVacationDaysAllowed(){
        return totalVacationDaysAllowed;
    }
}
