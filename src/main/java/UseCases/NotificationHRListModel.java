package UseCases;
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
            for(String s: status.getUnresolvedArray()){
                this.listModel.addElement(s);
            }
        }
    }
    public void updateList(String notification, boolean deny){
        /*
        Updates UserNotification entity to resolved, if boolean deny is false,
        otherwise sets entity to resolved and denied.
         */
        status.notificationUpdater(notification, deny);
        status = new NotificationStatusTrackerUpdater(user);
    }


}