public class Shift {
    private User employee;
    private LocalDateTime time;
    private User[] coworkers;
    public Shift(User employee, LocalDateTime time, User[] coworkers){
        this.employee = employee;
        this.time = time;
        this.coworkers = coworkers;
    }

    public User getEmployee() {
        return employee;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public User[] getCoworkers() {
        return coworkers;
    }

    public void setCoworkers(User[] coworkers, User editor) {
        if (editor instanceof Employee){
            return;
        }
        this.coworkers = coworkers;
    }

    public void setEmployee(User employee, User editor) {
        if (editor instanceof Employee){
            return;
        }
        this.employee = employee;
    }

    public void setTime(LocalDateTime time, User editor) {
        if (editor instanceof Employee){
            return;
        }
        this.time = time;
    }
}