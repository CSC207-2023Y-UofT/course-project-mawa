import Entities.Shift;

import java.util.Comparator;

public class SortShiftByDate implements Comparator<Shift> {

    @Override
    public int compare(Shift s1, Shift s2) {
        int dateCompare = s1.getTime().compareTo(s2.getTime());
        return dateCompare;
    }
}
