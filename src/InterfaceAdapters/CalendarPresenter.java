package InterfaceAdapters;

import FrameworksAndDrivers.Page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class CalendarPresenter implements ItemListener, ActionListener {
    private int year, month, user;
    private String[] yearRange;
    private Page gui;
    private HashMap<String, Object> selectableObjects = new HashMap<String, Object>();
    private CalendarModel model;

    public CalendarPresenter(int year, int month, int user, Page gui, CalendarModel model){
        this.year = year;
        this.month = month;
        this.user = user;
        this.gui = gui;
        this.model = model;
        yearRange = new String[]{String.valueOf(year - 3), String.valueOf(year - 2),
                String.valueOf(year - 1), String.valueOf(year),
                String.valueOf(year + 1)};
    }

    public String[] getYearRange(){
        return yearRange;
    }

    public void addSelectableObject(String k, Object v){
        selectableObjects.put(k, v);
    }



    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED){
            if(e.getItem() == selectableObjects.get(CalendarConstants.yearSelector)){
                model.setYear(Integer.parseInt(
                        (String) Objects.requireNonNull(((JComboBox<String>) e.getItem()).getSelectedItem())));
                gui.update();
                year = model.getYear();

            } else if(e.getItem() == selectableObjects.get(CalendarConstants.monthSelector)){
                model.setMonth(Integer.parseInt(
                        (String) Objects.requireNonNull(((JComboBox<String>) e.getItem()).getSelectedItem())));
                gui.update();
                month = model.getMonth();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof DayCell){
            new DayView(LocalDate.of(year, month, ((DayCell) e.getSource()).getDay()), ((DayCell) e.getSource()).getWeekday(),
                    ((DayCell) e.getSource()).getPayday(), ((DayCell) e.getSource()).getShifts(), user);
            gui.dispose();
        }
    }
}
