package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Shift implements Serializable {
    private LocalDateTime time;
    private float duration;
    private List<Integer> coworkers;

    private int shiftId;

    public Shift(LocalDateTime time, List<Integer> coworkers, float duration, int shiftId){
        this.time = time;
        this.coworkers = coworkers.stream().distinct().collect(Collectors.toList());
        this.duration = duration;
        this.shiftId = shiftId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getShiftId(){return shiftId;}

    public List<Integer> getCoworkers() {
        return coworkers;
    }

    public void addCoworker(Integer coworker){

        this.coworkers.add(coworker);
        this.coworkers = this.coworkers.stream().distinct().collect(Collectors.toList());
    }

    public void addCoworkers(List<Integer> coworkers){

        this.coworkers.addAll(coworkers);
        this.coworkers = this.coworkers.stream().distinct().collect(Collectors.toList());
    }

    public void removeCoworker(Integer coworker){
        if (this.coworkers.contains(coworker)){
            this.coworkers.remove(coworker);
        }

    }
    public void setCoworkers(List<Integer> coworkers) {

        this.coworkers = coworkers;
        this.coworkers = this.coworkers.stream().distinct().collect(Collectors.toList());
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