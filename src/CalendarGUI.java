
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CalendarGUI extends JFrame implements ActionListener, Page {
    private LocalDate firstDay, lastDay;
    private int numSections;
    private ShiftInteractor shiftInteractor;
    private CalendarManager calMan;
    private JComboBox<String> monthList;
    private JComboBox<String> yearList;
    private int month;
    private int year;
    private Employee employee;

    public CalendarGUI(int month, int year, Employee employee){
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.firstDay = LocalDate.of(year, month, 1);
        this.lastDay = LocalDate.of(year, month, firstDay.lengthOfMonth());
        this.calMan = new CalendarManager();
        this.month = month;
        this.year = year;
        this.employee = employee;

    }

    private JPanel layoutDays(){
        numSections =
                (int)Math.ceil((firstDay.lengthOfMonth() + firstDay.getDayOfWeek().getValue() - 1) / 7) * 7;
        JPanel panelGrid = new JPanel (new GridLayout(numSections/7, 7));
        for(int i = 1; i < numSections + 1; i++){
            if (i < (firstDay.getDayOfWeek().getValue()) ||
                    i > (firstDay.lengthOfMonth() + firstDay.getDayOfWeek().getValue())){
                panelGrid.add(new DayCell(0, "", false, new Shift[]{}));
            } else{
                int dayNum = i - firstDay.getDayOfWeek().getValue() + 1;
                Object[] day = calMan.getDayInfo(dayNum, month, year);
                DayCell dayCell = new DayCell(dayNum,
                        CalendarConstants.days[(dayNum + firstDay.getDayOfWeek().getValue()) % 7],
                        (boolean)day[CalendarConstants.dayInfoPayDay],
                        (Shift[])day[CalendarConstants.dayInfoShifts]);
                dayCell.addActionListener(this);
                panelGrid.add(dayCell);
            }
        }
        return panelGrid;
    }
    private JPanel layoutHeader() {
        JPanel pageLayout = new JPanel(new BorderLayout());
        monthList = new JComboBox<String>(CalendarConstants.months);
        pageLayout.add(monthList, BorderLayout.PAGE_START);
        String[] years = new String[]{String.valueOf(year - 2),
                String.valueOf(year - 1), String.valueOf(year),
                String.valueOf(year + 1), String.valueOf(year + 2)};
        yearList = new JComboBox<String>(years);
        pageLayout.add(yearList, BorderLayout.PAGE_START);
        return pageLayout;
    }

    private update(){

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof DayCell){
            new DayView(((DayCell) e.getSource()).getDay(), ((DayCell) e.getSource()).getWeekday(),
                    ((DayCell) e.getSource()).getPayday(), ((DayCell) e.getSource()).getShifts(), employee);
        }
    }

    @Override
    public void addTitle() {

    }

    @Override
    public void addContent() {

    }
}
