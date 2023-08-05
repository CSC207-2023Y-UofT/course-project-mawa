package InterfaceAdapters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UseCases.*;

public class UserNotificationPresenter {
    public Page gui;
    public NotificationHRListModel unresolvedList;
    public NotificationHRListModel resolvedList;
    public int user;

    public UserNotificationPresenter(int userID, Page gui, NotificationHRListModel unresolvedList,
                                 NotificationHRListModel resolvedList){
        this.user = userID;
        this.unresolvedList = unresolvedList;
        this.resolvedList = resolvedList;
        this.gui = gui;
    }

    public void rescheduleUpdateListModel(String selected){
        unresolvedList.listModel.removeElement(selected);
        resolvedList.listModel.add(0, selected);

    }
    public void denyUpdateListModel(String selected){
        unresolvedList.listModel.removeElement(selected);
        resolvedList.listModel.add(0, selected);

    }

}