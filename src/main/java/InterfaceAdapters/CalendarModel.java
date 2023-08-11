package InterfaceAdapters;


import UseCases.ShiftFileReader;
import UseCases.UserFileReader;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CalendarModel {
    private int year, month, user;

    public CalendarModel(int year, int month, int user){
        this.year = year;
        this.month = month;
        this.user = user;
    }

    public ArrayList<Integer> getShifts(int dayNum){
        LocalDate day = LocalDate.of(year, month, dayNum);
        ArrayList<Integer> shifts;
        ShiftFileReader shiftDB= ShiftFileReader.getInstance();
        UserFileReader userDB = UserFileReader.getInstance();
        if (userDB.getType(user).equals("HR")){
            shifts = shiftDB.getIds(day);
        } else{
            ArrayList<Integer> userShifts = shiftDB.getIds(user);
            ArrayList<Integer> dayShifts = shiftDB.getIds(day);
            if (userShifts.size() > 0 && dayShifts.size() > 0){
                shifts = (ArrayList<Integer>) List.copyOf(userShifts.stream()
                        .distinct()
                        .filter(dayShifts::contains)
                        .collect(Collectors.toSet()));
            } else{
                shifts = new ArrayList<Integer>();
            }
        }
        return shifts;
    }

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
