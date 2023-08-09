package UseCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Entities.*;

public final class ShiftSorter implements Sorter<Shift>{
    public static ArrayList<Integer> sortShiftsByDate(ArrayList<Integer> shifts){
        ShiftFileProcessor processor = ShiftFileProcessor.getInstance();
        ArrayList<Shift> shiftObj = new ArrayList<>();
        for (int i:shifts){
            shiftObj.add(processor.getIdToShift().get(i));
        }
        Collections.sort(shiftObj, new SortShiftByDate());
        ArrayList<Integer> sorted = new ArrayList<>();
        for (Shift s:shiftObj){
            sorted.add(s.getShiftId());
        }
        return sorted;
    }
}
