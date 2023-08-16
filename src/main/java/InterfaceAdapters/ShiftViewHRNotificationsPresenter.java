package InterfaceAdapters;
import UseCases.ShiftViewHRModel;

import javax.swing.*;

public class ShiftViewHRNotificationsPresenter {

    ShiftViewHRModel HRModel;
    public ShiftViewHRNotificationsPresenter(int shiftId){
        HRModel = new ShiftViewHRModel(shiftId);
    }

    public ShiftViewHRNotificationsPresenter(int userId, int notificationId){
        HRModel = new ShiftViewHRModel(notificationId, userId);
    }
    public DefaultListModel<String> employeesOnShiftList(){
        return HRModel.getEmployeesOnShiftList();
    }
    public DefaultListModel<String> employeesNotOnShiftList(){
        return HRModel.getEmployeesNotOnShiftList();
    }
    public JLabel getDateLabel(){
        /*
        Gets the date of the current shift.
         */
        return HRModel.getShiftDateLabel();
    }
    public JLabel getTimeLabel(){
        /*
        Gets the time of the current shift.
         */
        return HRModel.getShiftTimeLabel();
    }

    public void updateEmployeesOnShiftList(String selected){
        /*
        Edit DefaultListModel, a part of JSwing JList, to visually add employee to on shift list.
         */
        HRModel.getEmployeesNotOnShiftList().removeElement(selected);
        HRModel.getEmployeesOnShiftList().addElement(selected);
    }
    public void updateEmployeesNotOnShiftList(String selected){
        /*
        Edit DefaultListModel, a part of JSwing JList, to visually add employee to not on shift list.
         */
        HRModel.getEmployeesNotOnShiftList().addElement(selected);
        HRModel.getEmployeesOnShiftList().removeElement(selected);
    }

    public void updateShiftEmployeesAndNotification(){
        /*
        Updates whether a employee is on the current shift, using their locations of the GUI list. Updates
        the notification to resolved, if the notification who requested the notification, is not still on the shift.
         */
        HRModel.updateShiftAndNotification();
    }
    public void updateShiftEmployees(){
        /*
        Updates whether a employee is on the current shift, using their locations of the GUI list.
        */
        HRModel.updateShift();
    }

}
