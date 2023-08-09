package InterfaceAdapters;


import FrameworksAndDrivers.Page;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Objects;


public class CalendarPresenter implements ItemListener {
    private int year, month, user;
    private String[] yearRange;
    private Page gui;
    private CalendarModel model;
    private GUIElement yearSelector, monthSelector;

    public CalendarPresenter(int year, int month, int user, Page gui, CalendarModel model,
                             GUIElement yearSelector, GUIElement monthSelector){
        this.year = year;
        this.month = month;
        this.user = user;
        this.gui = gui;
        this.model = model;
        this.monthSelector = monthSelector;
        this.yearSelector = yearSelector;
        yearRange = new String[]{String.valueOf(year - 3), String.valueOf(year - 2),
                String.valueOf(year - 1), String.valueOf(year),
                String.valueOf(year + 1)};
    }

    public String[] getYearRange(){
        return yearRange;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED){
            if(e.getItem() == yearSelector){
                model.setYear(Integer.parseInt(Objects.requireNonNull(yearSelector.getContent())));
                gui.update();
                year = model.getYear();

            } else if(e.getItem() == monthSelector){
                model.setMonth(Integer.parseInt(Objects.requireNonNull(monthSelector.getContent())));
                gui.update();
                month = model.getMonth();
            }
        }
    }
}
