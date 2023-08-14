package UseCases;

import java.util.ArrayList;
import java.util.Collections;

import Entities.*;
/**
 * The ShiftSorter class provides methods for sorting shifts based on different criteria (theoretically).
 */
public class ShiftSorter implements Sorter<Shift> {

    public ShiftFileReader reader = ShiftFileReader.getInstance();;

    /**
     * Sorts shifts by date in ascending order.
     *
     * @param shifts The list of shift IDs to be sorted.
     * @return The sorted list of shift IDs.
     */
    public ArrayList<Integer> sortShiftsByDate(ArrayList<Integer> shifts){
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
