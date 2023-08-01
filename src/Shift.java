

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

    public void addCoworker(Integer coworker){

        this.coworkers.add(coworker);
    }

    public void addCoworkers(List<Integer> coworkers){

        this.coworkers.addAll(coworkers);
    }

    public void removeCoworker(Integer coworker){
        if (this.coworkers.contains(coworker)){
            this.coworkers.remove(coworker);
        }

    }
    public void setCoworkers(List<Integer> coworkers) {

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