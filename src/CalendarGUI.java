import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import CalendarConstants.*;

public class CalendarGUI extends JFrame{
    private LocalDate firstDay, lastDay;
    private int numSections;
    private ShiftInteractor shiftInteractor;
    private CalendarManager calMan;

    public CalendarGUI(int month, int year){
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.firstDay = new LocalDate(year, month, 1);
        this.lastDay = new LocalDate(year, month, firstDay.lengthOfMonth());
        this.calMan = new CalendarManager();

    }

    private layout(){
        numSections =
                Math.ceiling((firstDay.lengthOfMonth() + firstDay.dayOfWeek() - 1) / 7) * 7;
        JPanel panelGrid = new JPanel (new GridLayout(numSections/7, 7));
        for(int i = 1; i < numSections + 1; i++){
            if (i < (firstDay.dayOfWeek() ||
                    i > (firstDay.lengthOfMonth() + firstDay.dayOfWeek())){
                panelGrid.add(new DayCell(0, "", false, new Shift[]));
            } else{
                int dayNum = i - firstDay.dayOfWeek() + 1;
                Object[] day = calMan.getDayInfo(dayNum, month, year);
                panelGrid.add(new DayCell(dayNum,
                        CalendarConstants.days[(dayNum + firstDay.dayOfWeek()) % 7],
                        day[CalendarConstants.dayInfoPayDay],  
                        day[CalendarConstants.dayInfoShifts]));
            }
        }
    }

}
