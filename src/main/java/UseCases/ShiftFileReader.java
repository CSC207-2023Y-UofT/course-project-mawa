package UseCases;

import Entities.Shift;
import Entities.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ShiftFileReader{
    private static ShiftFileReader instance;
    private Shift shift;
    private ShiftInteractor interactor;
    private ArrayList<Shift> list;

    private ShiftFileReader() {
        shift = new Shift(LocalDateTime.now(), new ArrayList<Integer>(), (float)0, -11);
        interactor = new ShiftInteractor();
        list = interactor.readData();
    }
    public static ShiftFileReader getInstance(){
        if (instance == null) {
            instance = new ShiftFileReader();
        }
        return instance;
    }

    public void update(){
        list = interactor.readData();
    }
    public void checkShift(int id){
        if (shift.getShiftId() == id){
            return;
        }
        for (Shift s:list){
            if(s.getShiftId() == id){
                this.shift = s;
                return;
            }
        }
        System.out.println("Invalid Shift ID");
    }

    public ArrayList<Integer> getIds(LocalDate date){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Shift s:list){
            if (s.getTime().toLocalDate().isEqual(date)){
                ids.add(s.getShiftId());
            }
        }
        return ids;
    }

    public ArrayList<Integer> getIds(int empId){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Shift s:list){
            if (s.getCoworkers().contains(empId)){
                ids.add(s.getShiftId());
            }
        }
        return ids;
    }
    public LocalDateTime getDate(int id){
        checkShift(id);
        return shift.getTime();
    }

    public ArrayList<Integer> getCoworkers(int id){
        checkShift(id);
        return (ArrayList<Integer>) shift.getCoworkers();
    }

    public float getDuration(int id){
        checkShift(id);
        return shift.getDuration();
    }

    public Shift getShift(int id){
        checkShift(id);
        return shift;
    }

    public ArrayList<Integer> getIds(){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Shift u:list){
            ids.add(u.getShiftId());
        }
        return ids;
    }

}
