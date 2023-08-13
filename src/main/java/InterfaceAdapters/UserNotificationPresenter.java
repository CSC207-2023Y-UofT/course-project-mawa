package InterfaceAdapters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UseCases.*;

public abstract class UserNotificationPresenter {
    public NotificationHRListModel unresolvedList;
    public NotificationHRListModel resolvedList;
    public int user;

    public UserNotificationPresenter(int userID){
        this.user = userID;
        this.unresolvedList = new NotificationHRListModel(user, false);
        this.resolvedList = new NotificationHRListModel(user, true);
        this.resolvedList.populateList();
        this.unresolvedList.populateList();
    }
    public DefaultListModel<String> getUnresolvedDefaultList(){
        return this.unresolvedList.getListModel();
    }

    public DefaultListModel<String> getResolvedDefaultList(){
        return this.resolvedList.getListModel();
    }

}