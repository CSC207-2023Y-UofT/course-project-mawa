package InterfaceAdapters;


import UseCases.ShiftFileReader;
import UseCases.UserFileReader;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;


/**
 * The CalendarModel class represents the model component for the CalendarGUI.
 * It provides methods to retrieve shifts, check payday, and manage year and month information.
 */
public class CalendarModel {
    private int year, month, user;
    private ShiftFileReader shiftDB;
    private UserFileReader userDB;

    /**
     * Constructs a CalendarModel object with the specified year, month, and user.
     *
     * @param year The year of the calendar.
     * @param month The month of the calendar.
     * @param user The user associated with the calendar.
     */

    public CalendarModel(int year, int month, int user){
        this.year = year;
        this.month = month;
        this.user = user;
        userDB = UserFileReader.getInstance();
    }

    /**
     * Retrieves the list of shifts for a specific day, for HR. Retrieves the intersection
     * of all shifts on this day and all shifts of the user, for Employees.
     *
     * @param dayNum The day of the month.
     * @return An ArrayList of shift Ids for the specified day.
     */
    public ArrayList<Integer> getShifts(int dayNum){
        shiftDB= ShiftFileReader.getInstance();
        LocalDate day = LocalDate.of(year, month, dayNum);
        ArrayList<Integer> shifts = new ArrayList<>();
        if (userDB.getType(user).equals("HR")){
            shifts = shiftDB.getIds(day);
        } else{
            ArrayList<Integer> userShifts = shiftDB.getIds(user);
            ArrayList<Integer> dayShifts = shiftDB.getIds(day);
            if (userShifts.size() > 0 && dayShifts.size() > 0){
                for (int i:userShifts){
                    for (int j:dayShifts){
                        if (i == j){
                            shifts.add(i);
                        }
                    }
                }
            } else{
                shifts = new ArrayList<Integer>();
            }
        }
        return shifts;
    }

    /**
     * Checks if a specific day is a payday (last Friday of the month).
     *
     * @param dayNum The day of the month.
     * @return True if the day is a payday, false otherwise.
     */
    public boolean isPayDay(int dayNum){
        LocalDate day = LocalDate.of(year, month, dayNum);
        LocalDate lastFri =  day.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        boolean isPayDay = day.isEqual(lastFri);
        return isPayDay;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public int getYear(){
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getUser() {
        return user;
    }
}
