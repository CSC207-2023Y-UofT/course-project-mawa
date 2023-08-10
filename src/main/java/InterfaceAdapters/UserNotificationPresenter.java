package InterfaceAdapters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UseCases.*;

public class UserNotificationPresenter {
    public NotificationHRListModel unresolvedList;
    public NotificationHRListModel resolvedList;
    public int user;

    public UserNotificationPresenter(int userID, NotificationHRListModel unresolvedList,
                                     NotificationHRListModel resolvedList){
        this.user = userID;
        this.unresolvedList = unresolvedList;
        this.resolvedList = resolvedList;
        this.resolvedList.populateList();
        this.unresolvedList.populateList();
    }

    public void rescheduleUpdateListModel(String selected){
        unresolvedList.listModel.removeElement(selected);
        resolvedList.listModel.add(0, selected);
        unresolvedList.updateList(selected, false);
    }
    public void denyUpdateListModel(String selected){
        unresolvedList.listModel.removeElement(selected);
        resolvedList.listModel.add(0, selected);
        unresolvedList.updateList(selected, true);

    }

}