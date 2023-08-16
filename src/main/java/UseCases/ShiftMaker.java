package UseCases;

import Entities.Shift;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The ShiftMaker class provides a method for creating shifts.
 */
public class ShiftMaker {
    public ShiftInteractor interactor;
    public ShiftFileReader reader;
    private LocalDateTime date;
    private float duration;

    /**
     * Constructs a ShiftMaker instance with the specified date and duration.
     *
     * @param date     The start date and time of the shift.
     * @param duration The duration of the shift in hours.
     */
    public ShiftMaker(LocalDateTime date, float duration){
        this.date = date;
        this.duration = duration;
        interactor = new ShiftInteractor();
        reader = ShiftFileReader.getInstance();
    }

    public ShiftMaker(LocalDateTime date, float duration, String test){
        this.date = date;
        this.duration = duration;
        interactor = new ShiftInteractor("p");
        reader = new ShiftFileReader("p");
    }


    /**
     * Creates a new shift based on the provided date and duration.
     *
     * @throws InvalidTimeException If the shift's end time is not on the same date as the start time.
     */
    public void makeShift() throws InvalidTimeException {
        int hours = (int) Math.floor(duration);
        int mins = (int) ((duration - hours) * 60);
        LocalDateTime time2 = date.plusHours(hours).plusMinutes(mins);
        if (time2.toLocalDate().isEqual(date.toLocalDate())){
            //default shift has no coworkers on it
            Shift shift = new Shift(date,new ArrayList<Integer>(), duration, reader.getIds().size() + 1);
            interactor.writeData(shift);
        } else{
            throw new InvalidTimeException();
        }

    }
}
