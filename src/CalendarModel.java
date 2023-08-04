import Entities.Shift;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class CalendarModel {
    private int year, month, user;

    public CalendarModel(int year, int month, int user){
        this.year = year;
        this.month = month;
        this.user = user;
    }
    private ShiftDatabaseInteractor shiftDB = new ShiftDatabaseInteractor();
    public Object[] getDayInfo(int dayNum){
        LocalDate day = LocalDate.of(year, month, dayNum);
        LocalDate lastFri =  day.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        boolean isPayDay = day.isEqual(lastFri);
        Shift[] shifts = shiftDB.getShifts(day, user);
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
