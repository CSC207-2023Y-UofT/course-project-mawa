package Entities;


import java.util.Comparator;

/**
 * The SortShiftByDate class implements the Comparator interface to sort Shift objects by date.
 */
public class SortShiftByDate implements Comparator<Shift> {

    /**
     * Compares two Shift objects based on their date and time.
     *
     * @param s1 The first Shift object.
     * @param s2 The second Shift object.
     * @return A negative integer, zero, or a positive integer if the first Shift is less than, equal to,
     *         or greater than the second Shift based on their date and time.
     */
    @Override
    public int compare(Shift s1, Shift s2) {
        int dateCompare = s1.getTime().compareTo(s2.getTime());
        return dateCompare;
    }
}
