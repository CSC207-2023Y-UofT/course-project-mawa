package InterfaceAdapters;


import UseCases.FileNameConstants;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CalendarModel {
    private int year, month, user;
    private ShiftFileReader shiftDB;
    private UserFileReader userDB;

    public CalendarModel(int year, int month, int user){
        this.year = year;
        this.month = month;
        this.user = user;
        shiftDB= new ShiftFileReader(FileNameConstants.SHIFT_FILE_NAME);
        userDB = new UserFileReader();
    }
    public Object[] getDayInfo(int dayNum){
        LocalDate day = LocalDate.of(year, month, dayNum);
        LocalDate lastFri =  day.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        boolean isPayDay = day.isEqual(lastFri);
        ArrayList<Integer> shifts = null;
        if (userDB.getType(user).equals("HR")){
            shifts = shiftDB.getIds(day);
        } else{
            shifts = (ArrayList<Integer>) List.copyOf(shiftDB.getIds(user).stream()
                    .distinct()
                    .filter(shiftDB.getIds(day)::contains)
                    .collect(Collectors.toSet()));
        }

        Object[] dayInfo = new Object[CalendarConstants.dayFormat.length];
        dayInfo[CalendarConstants.dayInfoPayDay] = isPayDay;
        dayInfo[CalendarConstants.dayInfoShifts] = shifts;
        return(dayInfo);
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
