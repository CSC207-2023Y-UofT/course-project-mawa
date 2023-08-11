package UseCases;

import Entities.Shift;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShiftMaker {
    private LocalDateTime date;
    private float duration;
    public ShiftMaker(LocalDateTime date, float duration){
        this.date = date;
        this.duration = duration;
    }

    public void makeShift() throws InvalidTimeException {
        ShiftInteractor interactor = new ShiftInteractor();
        ShiftFileReader reader = ShiftFileReader.getInstance();
        int hours = (int) Math.floor(duration);
        int mins = (int) ((duration - hours) * 60);
        LocalDateTime time2 = date.plusHours(hours).plusMinutes(mins);
        if (time2.toLocalDate().isEqual(date.toLocalDate())){
            Shift shift = new Shift(date,new ArrayList<Integer>(), duration, reader.getIds().size() + 1);
            interactor.writeData(shift);
        } else{
            throw new InvalidTimeException();
        }

    }
}
