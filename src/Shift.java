public class Shift {
    private LocalDateTime time;
    private List<User> coworkers;
    public Shift(LocalDateTime time, List<User> coworkers){
        this.time = time;
        this.coworkers = coworkers;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public List<User> getCoworkers() {
        return coworkers;
    }

    public void addCoworker(User coworker, User editor){
        if (editor instanceof Employee){
            return;
        }
        this.coworkers.add(coworker);
    }

    public void addCoworker(List<User> coworkers, User editor){
        if (editor instanceof Employee){
            return;
        }
        this.coworkers.addAll(coworker);
    }
    public void setCoworkers(List<User> coworkers, User editor) {
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