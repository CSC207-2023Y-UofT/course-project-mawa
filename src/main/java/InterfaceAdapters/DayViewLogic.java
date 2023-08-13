package InterfaceAdapters;

import UseCases.*;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The DayViewLogic class provides logic for the DayView component.
 * It handles time ranges, and ShiftCell positions.
 */
public class DayViewLogic {

    private ArrayList<Integer> shifts;
    private float width, height;
    private int user;
    private LocalDate date;

    /**
     * Constructs a DayViewLogic object.
     *
     * @param shifts The list of shift Ids.
     * @param width The width of the render area.
     * @param height The height of the render area.
     * @param user The user associated with the view.
     * @param date The LocalDate representing the date of the DayView.
     */
    public DayViewLogic(ArrayList<Integer> shifts, float width, float height, int user,
                        LocalDate date){
        this.shifts = new ShiftSorter().sortShiftsByDate(shifts);
        System.out.println(shifts.size());
        this.width = width;
        this.height = height;
        this.user = user;
        this.date = date;
    }

    /**
     * Checks if the user associated with the view is an HR user.
     *
     * @return true if the user is HR, false otherwise.
     */

    public boolean isHR(){
        UserFileReader ureader = UserFileReader.getInstance();
        return ureader.getType(user).equals(UserTypeConstants.HR);
    }

    /**
     * Gets the time range to display based on the shifts.
     *
     * @return An array containing the start and end hours of the time range.
     */
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

    /**
     * Updates the shifts to be associated with the view.
     */
    public void update(){
        ShiftFileReader sReader = ShiftFileReader.getInstance();
        shifts = sReader.getIds(date);
        shifts = new ShiftSorter().sortShiftsByDate(shifts);
    }

    /**
     * Gets the list of shifts.
     *
     * @return The list of shifts.
     */
    public ArrayList<Integer> getShifts(){
        return shifts;
    }

    /**
     * Gets the list of all hours within the time range.
     *
     * @return The list of hours.
     */
    public ArrayList<Integer> getHours(){
        int[] timeRange = getTimeRange();
        ArrayList<Integer> allHours = new ArrayList<Integer>();
        for (int i = timeRange[0]; i < timeRange[1]; i++){
            allHours.add(i);
        }
        return allHours;
    }

    /**
     * Gets the position of shift cells within the view.
     *
     * @return The list of Rectangles representing cell positions.
     */
    public ArrayList<Rectangle> getShiftCellPosition(){
        ArrayList<Rectangle> areas = new ArrayList<Rectangle>();
        int[] timeRange = getTimeRange();
        ArrayList<ArrayList<Integer>> shifts2D = DayViewModel.make2DList(shifts);
        ArrayList<Integer> newShifts = new ArrayList<>();
        for(ArrayList<Integer> a : shifts2D){
            newShifts.addAll(a);
        }
        this.shifts = newShifts;
        for (ArrayList<Integer> s0 : shifts2D){
            for(int i = 0; i < s0.size(); i++){
                ShiftFileReader reader = ShiftFileReader.getInstance();
                int s = s0.get(i);
                int hours = (int) Math.floor(reader.getDuration(s));
                int mins = (int) ((reader.getDuration(s) - hours) * 60);
                LocalDateTime time2 = reader.getDate(s).plusHours(hours).plusMinutes(mins);
                Rectangle area = new Rectangle((int) (width /10 + i * 8 * width / 10 / s0.size()),
                        (int) (DayViewModel.yCoord(reader.getDate(s).getHour() - timeRange[0] + 1 + (float)(reader.getDate(s).getMinute())/60,
                                                        timeRange[1] - timeRange[0], height)),
                        (int) ((float) 8 * width / 10 / s0.size()),
                        (int) DayViewModel.yCoord(reader.getDuration(s) + 1 + (float)time2.getMinute()/60,
                                timeRange[1] - timeRange[0], height));
                areas.add(area);
            }
        }
        return areas;
    }

}
