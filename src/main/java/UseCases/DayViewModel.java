package UseCases;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The DayViewModel class provides utility methods related to DayView rendering
 * and shift position calculations.
 */
public class DayViewModel {
    private ShiftFileReader reader;
    public DayViewModel(){
        reader = ShiftFileReader.getInstance();
    }
    public DayViewModel(String isTest){
        reader = new ShiftFileReader("test");
    }
    /**
     * Calculates the Y coordinate on the day view grid.
     *
     * @param i The index.
     * @param scale The scale.
     * @param height The height of the render area.
     * @return The calculated Y coordinate.
     */
    public static float yCoord(float i, float scale, float height){
        return (( (14 * height) /15) * i / scale + height /30);
    }

    /**
     * Converts a list of shifts into a 2D list of overlapping shifts.
     *
     * @param shifts The list of shifts.
     * @return A 2D list of overlapping shifts.
     */
    public ArrayList<ArrayList<Integer>> make2DList(ArrayList<Integer> shifts){
        ArrayList<ArrayList<Integer>> shifts2D = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> shifts1 = new ArrayList<>(shifts);
        //know that shifts is already sorted by time
        while(shifts1.size()>0) {
            ArrayList<Integer> overlappingShifts = new ArrayList<Integer>();
            overlappingShifts.add(shifts1.get(0));
            shifts1.remove(0);
            while(shifts1.size()>0) {
                if (isOverlapping(overlappingShifts.get(0), shifts1.get(0))) {
                    overlappingShifts.add(shifts1.get(0));
                    shifts1.remove(0);
                }else{
                    break;
                }
            }
            shifts2D.add(overlappingShifts);
        }

        return shifts2D;
    }

    /**
     * Checks if two shifts are overlapping.
     *
     * @param shift1 The Id of the first shift.
     * @param shift2 The Id of the second shift.
     * @return true if the shifts overlap, false otherwise.
     */
    public boolean isOverlapping(Integer shift1, Integer shift2) {
        LocalDateTime start1 = reader.getDate(shift1);
        LocalDateTime end1 = reader.getDate(shift1).plus(Duration.ofMinutes((long) (reader.getDuration(shift1)* 60)));
        LocalDateTime start2 = reader.getDate(shift2);
        LocalDateTime end2 = reader.getDate(shift2).plus(Duration.ofMinutes((long) (reader.getDuration(shift2) * 60)));
        return (start1.isBefore(end2) && start2.isBefore(end1) && !shift1.equals(shift2));
    }

}
