package UseCases;
import Entities.Shift;
import Entities.User;
import Entities.UserNotification;

import java.util.ArrayList;

public class NotificationStatusTrackerUpdater {
    int user;
    UserNotification[][] notifications;

    UserController uc = new UserController();
    String[] resolved;
    String[] unresolved;

    public NotificationStatusTrackerUpdater(int userID){
        user = userID;
        notifications = getSortedResolvedAndUnresolvedNotifications(userID);
        resolved = NotificationsToString(notifications[0], userID);
        unresolved = NotificationsToString(notifications[1], userID);

    }

    public UserNotification[][] getSortedResolvedAndUnresolvedNotifications(int userID){
        UserNotificationInteractor db = new UserNotificationInteractor();
        ArrayList<UserNotification> list = db.getNotificationByUserID(userID);
        ArrayList<UserNotification> resolved = new ArrayList<>();
        ArrayList<UserNotification> unresolved = new ArrayList<>();
        for(UserNotification n: list){
            if (n.getResolvedStatus()){
                resolved.add(n);
            }
            else{
                unresolved.add(n);
            }
        }
        UserNotification[] sortedResolved = UserNotification.sortByCreatedDate(resolved);
        UserNotification[] sortedUnresolved = UserNotification.sortByCreatedDate(unresolved);
        return new UserNotification[][]{sortedResolved, sortedUnresolved};
    }

    public String[] NotificationsToString(UserNotification[] notifications, int userID){
        UserInteractor userInteractor = new UserInteractor();
        ShiftInteractor shiftInteractor = new ShiftInteractor();
        ArrayList<String> noti = new ArrayList<>();
        for(UserNotification n: notifications){
            User recipient = uc.idToUser(n.getRecipientId());
            String recipientUserName = recipient.getFirstname()+ " " + recipient.getSurname();
            User sender = uc.idToUser(n.getSenderId());
            String senderUserName = sender.getFirstname() + " " + sender.getSurname();
            Shift shift = shiftInteractor.getShiftByID(n.getShiftId());
            if (n.getResolvedStatus() && n.getDenyStatus()) {
                String item = recipientUserName + "has denied your request for time off on your " +shift.getDuration()+ "hour shift on " + shift.getTime().toString();
                noti.add(item);
            }else if(n.getResolvedStatus() && !n.getDenyStatus()){
                String item = recipientUserName + "has accepted your request for time off on your " +shift.getDuration()+ "hour shift on " + shift.getTime().toString();
                noti.add(item);
            }else if(!n.getResolvedStatus()){
                String item = senderUserName + "Has requested time of on their " +shift.getDuration()+ "hour shift on " + shift.getTime().toString();
                noti.add(item);
            }
        }

        String[] stringNotifications = new String[noti.size()];
        stringNotifications = noti.toArray(stringNotifications);
        return stringNotifications;
    }

}
