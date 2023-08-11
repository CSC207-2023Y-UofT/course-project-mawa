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

    public void makeShift(){
        ShiftInteractor interactor = new ShiftInteractor();
        ShiftFileReader reader = ShiftFileReader.getInstance();
        Shift shift = new Shift(date,new ArrayList<Integer>(), duration, reader.getIds().size() + 1);
        interactor.writeData(shift);
    }
}
