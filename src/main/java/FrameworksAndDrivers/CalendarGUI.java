package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import InterfaceAdapters.*;

public class CalendarGUI extends JFrame implements Page {
    private LocalDate firstDay, lastDay;
    private int numSections;
    private CalendarModel model;
    private CustomComboBox monthList, yearList;
    private int month;
    private int year;
    private int user;
    private JPanel panel = new JPanel();
    private ArrayList<Component> headerButtons = new ArrayList<Component>();
    private CalendarPresenter presenter;
    private JPanel panelGrid;

    public CalendarGUI(int month, int year, int user){
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.month = month;
        this.year = year;
        this.firstDay = LocalDate.of(year, month, 1);
        this.lastDay = LocalDate.of(year, month, firstDay.lengthOfMonth());
        setUser(user);
        //this.panel = new JPanel(new BorderLayout());
        this.model = new CalendarModel(this.year, this.month, this.user);
        this.presenter = new CalendarPresenter(this.year, this.month, this.user,
                this, model, yearList, monthList);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addTitle();
        addContent();
        this.setVisible(true);

    }

    private JPanel layoutDays(){
        numSections = 7 +
                (int)Math.ceil((firstDay.lengthOfMonth() + firstDay.getDayOfWeek().getValue() - 1) / 7) * 7;
        panelGrid = new JPanel (new GridLayout(0, 7));
        for (String d: CalendarConstants.days){
            panelGrid.add(new JLabel(d));
        }
        for(int i = 1; i < numSections + 1; i++){
            if (i < (firstDay.getDayOfWeek().getValue()) ||
                    i > (firstDay.lengthOfMonth() + firstDay.getDayOfWeek().getValue() - 1)){
                panelGrid.add(new JButton());
            } else{
                int dayNum = i - firstDay.getDayOfWeek().getValue() + 1;
                boolean payDay = model.isPayDay(dayNum);
                ArrayList<Integer> shifts = model.getShifts(dayNum);
                DayCell dayCell = new DayCell(this, LocalDate.of(year, month, dayNum),
                        CalendarConstants.days[(dayNum + firstDay.getDayOfWeek().getValue() - 1) % 7],
                        payDay, shifts, user);
                panelGrid.add(dayCell);
            }
        }
        return panelGrid;
    }
    private JPanel layoutHeader() {
        JPanel pageLayout = new JPanel(new BorderLayout());
        monthList = new CustomComboBox(CalendarConstants.months);
        monthList.setSelectedItem(CalendarConstants.months[month - 1]);
        //monthList.addItemListener(presenter);
        monthList.addActionListener(presenter);
        headerButtons.add(monthList);
        yearList = new CustomComboBox(presenter.getYearRange());
        yearList.setSelectedItem(String.valueOf(year));
        //yearList.addItemListener(presenter);
        yearList.addActionListener(presenter);
        headerButtons.add(yearList);
        addHomeButton();
        JPanel headerPanel =  new JPanel(new FlowLayout());
        for (Component c:headerButtons){
            headerPanel.add(c);
        }
        pageLayout.add(headerPanel, BorderLayout.PAGE_START);
        return pageLayout;
    }



    @Override
    public void addTitle() {
        this.setContentPane(panel);
    }

    @Override
    public void addContent() {
        panel.add(layoutDays(), BorderLayout.CENTER);
    }

    @Override
    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public void addHomeButton() {
        HomeButton hb = new HomeButton(this, user);
        Dimension size = hb.getPreferredSize();
        hb.setBounds(14 * getWidth() / 15 - size.width, getHeight() / 15 + size.height,
                size.width, size.height);
        headerButtons.add(hb);
    }

    @Override
    public void update() {
        setYear(model.getYear());
        setMonth(model.getMonth());
        this.firstDay = LocalDate.of(year, month, 1);
        this.lastDay = LocalDate.of(year, month, firstDay.lengthOfMonth());
        panelGrid.removeAll();
        layoutDays();

    }

    public void setYear(int year){
        this.year = year;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public static void main(String[] args){
        new CalendarGUI(8, 2023, 1);
    }
}
