package InterfaceAdapters;
import UseCases.ShiftViewHRModel;

import javax.swing.*;
import java.awt.*;
public class ShiftViewHRNotificationsPresenter {
    JFrame frame;
    ShiftViewHRModel HRModel;


    public ShiftViewHRNotificationsPresenter(JFrame view, ShiftViewHRModel model){
        frame = view;
        HRModel = model;
    }
    public void addShiftLabels(JPanel panel){
        JLabel shiftDateLabel = HRModel.getShiftDateLabel();
        JLabel shiftTimeLabel = HRModel.getShiftTimeLabel();
        JPanel shiftTitlePanel = new JPanel();
        shiftTitlePanel.setLayout(new GridLayout(2,1));
        shiftTitlePanel.add(shiftDateLabel);
        shiftTitlePanel.add(shiftTimeLabel);
        shiftDateLabel.setHorizontalAlignment(JLabel.CENTER);
        shiftTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        shiftDateLabel.setFont(new Font(shiftDateLabel.getFont().getName(), shiftDateLabel.getFont().getStyle(), 20));
        shiftTimeLabel.setFont(new Font(shiftDateLabel.getFont().getName(), shiftDateLabel.getFont().getStyle(), 15));
        panel.add(shiftTitlePanel, BorderLayout.PAGE_START);
    }

    public void updateEmployeesOnShiftList(String selected){
        HRModel.getEmployeesNotOnShiftList().removeElement(selected);
        HRModel.getEmployeesOnShiftList().addElement(selected);
    }
    public void updateEmployeesNotOnShiftList(String selected){
        HRModel.getEmployeesNotOnShiftList().addElement(selected);
        HRModel.getEmployeesOnShiftList().removeElement(selected);
    }

    public void updateShiftEmployeesandNotification(){
        HRModel.updateShiftandNotification();
    }

}
