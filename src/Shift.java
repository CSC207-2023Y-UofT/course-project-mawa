public class Shift {
    private LocalDateTime time;
    private User[] coworkers;
    public Shift(LocalDateTime time, User[] coworkers){
        this.time = time;
        this.coworkers = coworkers;
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

    public void setTime(LocalDateTime time, User editor) {
        if (editor instanceof Employee){
            return;
        }
        this.time = time;
    }
}