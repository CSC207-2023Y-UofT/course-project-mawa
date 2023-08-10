/*

package Testing;




import InterfaceAdapters.CalendarConstants;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarGUITest extends JFrame implements  {
    private LocalDate firstDay, lastDay;
    private int numSections;
    private JComboBox<String> monthList;
    private JComboBox<String> yearList;
    private int month;
    private int year;
    private int user;
    private JPanel panel;
    private ArrayList<Component> headerButtons = new ArrayList<Component>();

    public CalendarGUITest(int month, int year, int user){
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.firstDay = LocalDate.of(year, month, 1);
        this.lastDay = LocalDate.of(year, month, firstDay.lengthOfMonth());
        this.month = month;
        this.year = year;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addTitle();
        addContent();
        addHomeButton();
        setUser(user);
        JPanel headerPanel =  new JPanel(new FlowLayout());
        for (Component c:headerButtons){
            headerPanel.add(c);
        }
        panel.add(headerPanel, BorderLayout.PAGE_START);
        this.setVisible(true);

    }

    private JPanel layoutDays(){
        numSections = 7 +
                (int)Math.ceil((firstDay.lengthOfMonth() + firstDay.getDayOfWeek().getValue() - 1) / 7) * 7;
        JPanel panelGrid = new JPanel (new GridLayout(0, 7));
        for (String d: CalendarConstants.days){
            panelGrid.add(new JLabel(d));
        }
        for(int i = 1; i < numSections + 1; i++){
            if (i < (firstDay.getDayOfWeek().getValue()) + 1 ||
                    i > (firstDay.lengthOfMonth() + firstDay.getDayOfWeek().getValue() - 1)){
                panelGrid.add(new JButton());
            } else{
                int dayNum = i - firstDay.getDayOfWeek().getValue() - 1;
                JButton dayCell = new JButton(CalendarConstants.days[(dayNum + firstDay.getDayOfWeek().getValue()) % 7]);
                panelGrid.add(dayCell);
            }
        }
        return panelGrid;
    }
    private JPanel layoutHeader() {
        JPanel pageLayout = new JPanel(new BorderLayout());
        monthList = new JComboBox<String>(CalendarConstants.months);
        monthList.setSelectedItem(CalendarConstants.months[month - 1]);
        headerButtons.add(monthList);
        String[] years = new String[]{String.valueOf(year - 3), String.valueOf(year - 2),
                String.valueOf(year - 1), String.valueOf(year)};
        yearList = new JComboBox<String>(years);
        yearList.setSelectedItem(String.valueOf(year));
        headerButtons.add(yearList);
        return pageLayout;
    }



    public void addTitle() {
        panel = layoutHeader();
        this.setContentPane(panel);
    }

    public void addContent() {
        panel.add(layoutDays(), BorderLayout.CENTER);
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void addHomeButton() {
        JButton hb = new JButton("Home");
        Dimension size = hb.getPreferredSize();
        hb.setBounds(14 * getWidth() / 15 - size.width, getHeight() / 15 + size.height,
                size.width, size.height);
        headerButtons.add(hb);
    }

    @Override
    public void update() {

    }

    public static void main(String[] args){
        new CalendarGUITest(1,2005,1234);
    }
}
*/