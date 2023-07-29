package src;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Shift implements Serializable {
    private LocalDateTime time;
    private float duration;
    private List<User> coworkers;
    public Shift(LocalDateTime time, List<User> coworkers, float duration){
        this.time = time;
        this.coworkers = coworkers;
        this.duration = duration;
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

    public float getDuration(){
        return duration;
    }

    public void setDuration(float dur, User editor){
        if (editor instanceof Employee){
            return;
        }
        duration = dur;
    }
}