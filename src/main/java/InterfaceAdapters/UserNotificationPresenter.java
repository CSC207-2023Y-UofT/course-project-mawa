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
        /*
        Returns a DefaultListModel of all unresolved notifications either created by or directed at this user.
         */
        return this.unresolvedList.getListModel();
    }

    public DefaultListModel<String> getResolvedDefaultList(){
                /*
        Returns a DefaultListModel of all resolved notifications either created by or directed at this user.
         */
        return this.resolvedList.getListModel();
    }

}