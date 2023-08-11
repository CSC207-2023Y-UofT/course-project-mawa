package InterfaceAdapters;

import Entities.User;
import FrameworksAndDrivers.RequestForm;
import UseCases.*;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DayViewLogic {

    private ArrayList<Integer> shifts;
    private float width, height;
    private int user;
    private LocalDate date;
    public DayViewLogic(ArrayList<Integer> shifts, float width, float height, int user,
                        LocalDate date){
        this.shifts = new ShiftSorter().sortShiftsByDate(shifts);
        System.out.println(shifts.size());
        this.width = width;
        this.height = height;
        this.user = user;
        this.date = date;
    }

    public boolean isHR(){
        UserFileReader ureader = UserFileReader.getInstance();
        return ureader.getType(user).equals(UserTypeConstants.HR);
    }

    public int[] getTimeRange(){
        if (shifts.size() > 0){
            ShiftFileReader reader = ShiftFileReader.getInstance();
            return (new int[] {Math.min(Math.max(0, reader.getDate(shifts.get(0)).getHour() - 2), 8),
                    (int) Math.max(Math.min(24, reader.getDate(shifts.get(shifts.size() - 1)).getHour()
                                    + reader.getDuration(shifts.get(0))+ 2), 18)});
        } else{
            return (new int[] {8,18});
        }

    }

    public void update(){
        ShiftFileReader sReader = ShiftFileReader.getInstance();
        shifts = sReader.getIds(date);
        shifts = new ShiftSorter().sortShiftsByDate(shifts);
    }

    public ArrayList<Integer> getShifts(){
        return shifts;
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
        ArrayList<ArrayList<Integer>> shifts2D = make2DList();
        float increment = (float) (14 * height / 15) /(timeRange[1] - timeRange[0]);
        for (ArrayList<Integer> s0 : shifts2D){
            for(int i = 0; i < s0.size(); i++){
                ShiftFileReader reader = ShiftFileReader.getInstance();
                int s = s0.get(i);
                int hours = (int) Math.floor(reader.getDuration(s));
                int mins = (int) ((reader.getDuration(s) - hours) * 60);
                LocalDateTime time2 = reader.getDate(s).plusHours(hours).plusMinutes(mins);
                Rectangle area = new Rectangle((int) (width /10 + i * 8 * width / 10 / s0.size()),
                        (int) (yCoord(reader.getDate(s).getHour() - timeRange[0] + (float)(reader.getDate(s).getMinute())/60,
                                                        timeRange[1] - timeRange[0]) + increment),
                        (int) ((float) 8 * width / 10 / s0.size()),
                        (int) yCoord(time2.getHour() - timeRange[0] + (float)time2.getMinute()/60,
                                timeRange[1] - timeRange[0])/2);
                areas.add(area);
            }
        }
        return areas;
    }

    private float yCoord(float i, float scale){
        return (( (14 * height) /15) * i / scale + height /30);
    }

    public ArrayList<ArrayList<Integer>> make2DList(){
        ArrayList<ArrayList<Integer>> shifts2D = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> shifts1 = new ArrayList<>(shifts);
        //know that shifts is already sorted by time
        while(shifts1.size() > 0){
            ArrayList<Integer> overlappingShifts = new ArrayList<Integer>();
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

    public boolean isOverlapping(Integer shift1, Integer shift2) {

        ShiftFileReader reader = ShiftFileReader.getInstance();
        LocalDateTime start1 = reader.getDate(shift1);
        LocalDateTime end1 = reader.getDate(shift1).plus(Duration.ofMinutes((long) (reader.getDuration(shift1)* 60)));
        LocalDateTime start2 = reader.getDate(shift2);
        LocalDateTime end2 = reader.getDate(shift2).plus(Duration.ofMinutes((long) (reader.getDuration(shift2) * 60)));
        return (start1.isBefore(end2) && start2.isBefore(end1) && !shift1.equals(shift2));
    }

}
