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
        return HRModel.getShiftDateLabel();
    }
    public JLabel getTimeLabel(){
        return HRModel.getShiftTimeLabel();
    }

    public void updateEmployeesOnShiftList(String selected){
        HRModel.getEmployeesNotOnShiftList().removeElement(selected);
        HRModel.getEmployeesOnShiftList().addElement(selected);
    }
    public void updateEmployeesNotOnShiftList(String selected){
        HRModel.getEmployeesNotOnShiftList().addElement(selected);
        HRModel.getEmployeesOnShiftList().removeElement(selected);
    }

    public void updateShiftEmployeesAndNotification(){
        HRModel.updateShiftandNotification();
    }
    public void updateShiftEmployees(){
        HRModel.updateShift();
    }

}
