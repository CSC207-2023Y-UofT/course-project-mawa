package UseCases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Entities.*;

public final class ShiftSorter implements Sorter<Shift>{
    public static ArrayList<Integer> sortShiftsByDate(ArrayList<Integer> shifts){
        ShiftFileReader reader = ShiftFileReader.getInstance();
        ArrayList<Shift> shiftObj = new ArrayList<>();
        for (int i:shifts){
            shiftObj.add(reader.getShift(i));
        }
        Collections.sort(shiftObj, new SortShiftByDate());
        ArrayList<Integer> sorted = new ArrayList<>();
        for (Shift s:shiftObj){
            sorted.add(s.getShiftId());
        }
        return sorted;
    }
}
