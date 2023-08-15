package InterfaceAdapters;

public class UserNotificationHRPresenter extends UserNotificationPresenter{

    public UserNotificationHRPresenter(int userId){
        super(userId);
    }

    public void denyUpdateListModel(String selected){
        /*
        Updates default list model, if a notification was denied. Also updates database.
         */
        unresolvedList.listModel.removeElement(selected);
        unresolvedList.updateList(selected, true, resolvedList.listModel);
    }

    public int NotificationID(String notification){
        /*
        Returns the notification, that a string refers to.
         */
        return unresolvedList.getNotificationID(notification);
    }
}
