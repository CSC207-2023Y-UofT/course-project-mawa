package InterfaceAdapters;

import Entities.Shift;
import InterfaceAdapters.SortShiftByDate;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DayViewLogic {

    private Shift[] shifts;
    private float width, height;
    public DayViewLogic(Shift[] shifts, float width, float height){
        Arrays.sort(shifts, new SortShiftByDate());
        this.shifts = shifts;
        this.width = width;
        this.height = height;
    }

    public int[] getTimeRange(){
        return (new int[] {Math.max(0, shifts[0].getTime().getHour() - 2),
                Math.min(24, shifts[shifts.length - 1].getTime().getHour() + 2)});
    }

    public ArrayList<Integer> getHours(){
        int[] timeRange = getTimeRange();
        ArrayList<Integer> allHours = new ArrayList<Integer>();
        for (int i = timeRange[0]; i < timeRange[1]; i++){
            allHours.add(i);
        }
        return allHours;
    }

    public ArrayList<Rectangle> getShiftCellPosition(){
        ArrayList<Rectangle> areas = new ArrayList<Rectangle>();
        int[] timeRange = getTimeRange();
        ArrayList<ArrayList<Shift>> shifts2D = make2DList();
        for (ArrayList<Shift> s0 : shifts2D){
            for(int i = 0; i < s0.size(); i++){
                Shift s = s0.get(i);
                Rectangle area = new Rectangle((int) ((float) width /10 + i * 8 * width / 10 / s0.size()),
                        (int) yCoord(s.getTime().getHour() - timeRange[0] + (float)s.getTime().getMinute()/60,
                                timeRange[1] - timeRange[0]),
                        (int) ((float) 8 * width / 10 / s0.size()),
                        (int) yCoord(s.getDuration(), timeRange[1] - timeRange[0]));
                areas.add(area);
            }
        }
        return areas;
    }

    private ArrayList<ArrayList<Shift>> make2DList(){
        ArrayList<ArrayList<Shift>> shifts2D = new ArrayList<ArrayList<Shift>>();
        ArrayList<Shift> shifts1 = (ArrayList<Shift>) Arrays.asList(shifts.clone());
        //know that shifts is already sorted by time
        while(shifts1.size() > 0){
            ArrayList<Shift> overlappingShifts = new ArrayList<Shift>();
            overlappingShifts.add(shifts1.get(0));
            shifts1.remove(0);
            for (int j = 0; j < shifts1.size(); j ++){
                if (isOverlapping(overlappingShifts.get(0), shifts1.get(j))){
                    overlappingShifts.add(shifts1.get(j));
                    shifts1.remove(j);
                } else{
                    break;
                }
            }
            shifts2D.add(overlappingShifts);
        }
        return shifts2D;
    }

    private float yCoord(float i, float scale){
        return (((float) (14 * height) /15) * i / scale + (float) height /30);
    }

    private boolean isOverlapping(Shift shift1, Shift shift2) {
        LocalDateTime start1 = shift1.getTime();
        LocalDateTime end1 = shift1.getTime().plus(Duration.ofMinutes((long) (shift1.getDuration() * 60)));
        LocalDateTime start2 = shift2.getTime();
        LocalDateTime end2 = shift2.getTime().plus(Duration.ofMinutes((long) (shift2.getDuration() * 60)));
        return (start1.isBefore(end2) && start2.isBefore(end1) && !shift1.equals(shift2));
    }

}
