package InterfaceAdapters;
import Entities.Shift;
import FrameworksAndDrivers.ShiftView;
import UseCases.ShiftViewHRModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ShiftViewHRNotificationsPresenter {
    JFrame frame;
    ShiftViewHRModel HRModel;


    public ShiftViewHRNotificationsPresenter(JFrame view, ShiftViewHRModel model){
        frame = view;
        HRModel = model;
    }
    public void addShiftLabels(){
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
        frame.add(shiftTitlePanel, BorderLayout.PAGE_START);
    }

}
