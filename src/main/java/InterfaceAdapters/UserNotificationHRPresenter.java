package InterfaceAdapters;

public class UserNotificationHRPresenter extends UserNotificationPresenter{

    public UserNotificationHRPresenter(int userId){
        super(userId);
    }

    public void denyUpdateListModel(String selected){
        unresolvedList.listModel.removeElement(selected);
        unresolvedList.updateList(selected, true, resolvedList.listModel);
    }

    public int NotificationID(String notification){
        return unresolvedList.getNotificationID(notification);
    }
}
