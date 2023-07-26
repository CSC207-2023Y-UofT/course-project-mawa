package src;
import src.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CalendarGUI extends JFrame implements ActionListener {
    private LocalDate firstDay, lastDay;
    private int numSections;
    private ShiftInteractor shiftInteractor;
    private CalendarManager calMan;
    private JComboBox<String> monthList;
    private JComboBox<String> yearList;
    private int month;
    private int year;

    public CalendarGUI(int month, int year){
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.firstDay = new LocalDate(year, month, 1);
        this.lastDay = new LocalDate(year, month, firstDay.lengthOfMonth());
        this.calMan = new CalendarManager();
        this.month = month;
        this.year = year;

    }

    private JPanel layoutDays(){
        numSections =
                Math.ceiling((firstDay.lengthOfMonth() + firstDay.dayOfWeek() - 1) / 7) * 7;
        JPanel panelGrid = new JPanel (new GridLayout(numSections/7, 7));
        for(int i = 1; i < numSections + 1; i++){
            if (i < (firstDay.dayOfWeek() ||
                    i > (firstDay.lengthOfMonth() + firstDay.dayOfWeek())){
                panelGrid.add(new DayCell(0, "", false, new Shift[]{}));
            } else{
                int dayNum = i - firstDay.dayOfWeek() + 1;
                Object[] day = calMan.getDayInfo(dayNum, month, year);
                panelGrid.add(new DayCell(dayNum,
                        CalendarConstants.days[(dayNum + firstDay.dayOfWeek()) % 7],
                        day[CalendarConstants.dayInfoPayDay],  
                        day[CalendarConstants.dayInfoShifts]));
            }
        }
        return panelGrid;
    }
    private JPanel layoutHeader() {
        JPanel pageLayout = new JPanel(new BorderLayout());
        monthList = new JComboBox<String>(CalendarConstants.months);
        pageLayout.add(monthList, BorderLayout.PAGE_START);
        int[] years = new int[]{year - 2, year - 1, year, year + 1, year + 2};
        yearList = new JComboBox<String>(years);
        pageLayout.add(yearList, BorderLayout.PAGE_START);
    }

    private update(){

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
