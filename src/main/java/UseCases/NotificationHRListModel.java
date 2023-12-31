package UseCases;
import Entities.UserNotification;

import javax.swing.*;

public class NotificationHRListModel extends DefaultListModel<String> {
    public NotificationStatusTrackerUpdater status;
    public DefaultListModel<String> listModel = new DefaultListModel<String>();
    public int user;
    public boolean resolvedList;

    public NotificationHRListModel(int userID, boolean resolved){
        user = userID;
        resolvedList = resolved;
        status = new NotificationStatusTrackerUpdater(userID);
    }

    public DefaultListModel<String> getListModel(){
        return this.listModel;
    }

    public void populateList(){
        /*
        Populates DefaultListModel list depending on whether this model is a
        resolved list model or unresolved list model.
         */
        if (this.resolvedList){
            for(String s: status.getResolvedArray()){
                this.listModel.addElement(s);
            }
        }else{
            for (int i = 0; i < status.getUnresolvedArray().length; i++){
                this.listModel.addElement(status.getUnresolvedArray()[i]);
            }
        }
    }
    public int getNotificationID(String notification){
        /*
        Returns notification that string refers to.
         */
        return status.userNotificationFromString(notification).getNotifId();
    }
    public void updateList(String notification, boolean deny, DefaultListModel<String> notifications){
        /*
        Updates UserNotification entity to resolved, if boolean deny is false,
        otherwise sets entity to resolved and denied.
         */
        Object n = status.notificationUpdater(notification, deny);
        if (n!=null){
            UserNotification[] notif = {(UserNotification) n};
            notifications.add(0, status.NotificationsToString(notif, user)[0]);
        }
        status = new NotificationStatusTrackerUpdater(user);
    }


}