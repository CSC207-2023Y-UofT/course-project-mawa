package UseCases;

import Entities.Shift;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ShiftFileReader{
    private static ShiftFileReader instance;
    private Shift shift;
    private ShiftInteractor interactor;
    private ArrayList<Shift> list;
    /**
     * The ShiftFileReader class provides methods to read shift records from a data source.
     */
    private ShiftFileReader() {
        shift = new Shift(LocalDateTime.now(), new ArrayList<Integer>(), (float)0, -11);
        interactor = new ShiftInteractor();
        list = interactor.readData();
    }
    /**
     * Get the singleton instance of ShiftFileReader.
     *
     * @return The instance of ShiftFileReader.
     */
    public static ShiftFileReader getInstance(){
        if (instance == null) {
            instance = new ShiftFileReader();
        }
        return instance;
    }

    /**
     * Update the list of shift records from the data source.
     */
    public void update() {
        list = interactor.readData();
    }

    /**
     * Check if the specified shift ID matches the currently loaded shift record, and update if necessary.
     *
     * @param id The ID of the shift.
     */
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
    /**
     * Get a list of shift IDs for shifts on the specified date.
     *
     * @param date The date for which to retrieve shift IDs.
     * @return A list of shift IDs.
     */
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
    /**
     * Get the Shift object for the specified shift ID.
     *
     * @param id The ID of the shift.
     * @return The Shift object.
     */
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
