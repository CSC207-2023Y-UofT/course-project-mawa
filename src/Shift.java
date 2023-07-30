

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Shift implements Serializable {
    private LocalDateTime time;
    private float duration;
    private List<Integer> coworkers;
    public Shift(LocalDateTime time, List<Integer> coworkers, float duration){
        this.time = time;
        this.coworkers = coworkers;
        this.duration = duration;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public List<Integer> getCoworkers() {
        return coworkers;
    }

    public void addCoworker(Integer coworker, User editor){
        if (editor instanceof Employee){
            return;
        }
        this.coworkers.add(coworker);
    }

    public void addCoworker(List<Integer> coworkers, User editor){
        if (editor instanceof Employee){
            return;
        }
        this.coworkers.addAll(coworkers);
    }
    public void setCoworkers(List<Integer> coworkers, User editor) {
        if (editor instanceof Employee){
            return;
        }
        this.coworkers = coworkers;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public float getDuration(){
        return duration;
    }

    public void setDuration(float dur){
        duration = dur;
    }
}