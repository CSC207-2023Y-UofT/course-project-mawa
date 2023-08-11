package UseCases;
import Entities.Shift;
import Entities.User;
import Entities.UserNotification;
import Entities.UserNotificationResponse;

import java.util.ArrayList;

public class NotificationStatusTrackerUpdater {
    int user;
    UserNotification[][] notifications;
    String[] resolved;
    String[] unresolved;

    public NotificationStatusTrackerUpdater(int userID){
        user = userID;
        notifications = getSortedResolvedAndUnresolvedNotifications(userID);
        resolved = NotificationsToString(notifications[0], userID);
        unresolved = NotificationsToString(notifications[1], userID);

    }
    public String[] getResolvedArray(){
        return this.resolved;
    }
    public String[] getUnresolvedArray(){
        return this.unresolved;
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
        UserNotification[] sortedResolved = UserNotification.sortByResolvedDate(resolved);
        UserNotification[] sortedUnresolved = UserNotification.sortByCreatedDate(unresolved);
        return new UserNotification[][]{sortedResolved, sortedUnresolved};
    }

    public String[] NotificationsToString(UserNotification[] notifications, int userID){
        UserFileReader userFilerReader = UserFileReader.getInstance();
        ShiftInteractor shiftInteractor = new ShiftInteractor();
        User user = userFilerReader.getUser(userID);
        ArrayList<String> noti = new ArrayList<>();
        for(UserNotification n: notifications){
            User recipient = userFilerReader.getUser(n.getRecipientId());
            String recipientUserName = recipient.getFirstname()+ " " + recipient.getSurname();
            User sender = userFilerReader.getUser(n.getSenderId());
            String senderUserName = sender.getFirstname() + " " + sender.getSurname();
            Shift shift = shiftInteractor.getShiftByID(n.getShiftId());
            String item = "";
            if (n.getResolvedStatus() && n.getDenyStatus()) {
                if (user.getType().equals("HR")){
                    item = recipientUserName + " has denied "+ senderUserName +"'s request for time off on your " +shift.getDuration()+ " hour shift on " +  shift.getTime().getYear()+ "-" + shift.getTime().getMonthValue()+ "-" +shift.getTime().getDayOfMonth();
                }else {
                    item = recipientUserName + " has denied your request for time off on your " + shift.getDuration() + " hour shift on " + shift.getTime().getYear() + "-" + shift.getTime().getMonthValue() + "-" + shift.getTime().getDayOfMonth();
                }
                noti.add(item);
            }else if(n.getResolvedStatus() && !n.getDenyStatus()){
                if(user.getType().equals("HR")){
                    item = recipientUserName + " has accepted "+ senderUserName +"'s request for time off on your " + shift.getDuration() + " hour shift on " + shift.getTime().getYear() + "-" + shift.getTime().getMonthValue() + "-" + shift.getTime().getDayOfMonth();
                }
                else {
                    item = recipientUserName + " has accepted your request for time off on your " + shift.getDuration() + " hour shift on " + shift.getTime().getYear() + "-" + shift.getTime().getMonthValue() + "-" + shift.getTime().getDayOfMonth();
                }
                noti.add(item);
            }else if(!n.getResolvedStatus()){
                item = senderUserName + " has requested time of on their " +shift.getDuration()+ " hour shift on " +  shift.getTime().getYear()+ "-" + shift.getTime().getMonthValue()+ "-" +shift.getTime().getDayOfMonth();
                noti.add(item);
            }
        }

        String[] stringNotifications = new String[noti.size()];
        stringNotifications = noti.toArray(stringNotifications);
        return stringNotifications;
    }

    public UserNotification notificationUpdater(String notification, boolean deny){
        UserNotificationInteractor db = new UserNotificationInteractor();
        for(int i = 0; i < this.unresolved.length; i++){
            if (notification.equalsIgnoreCase(this.unresolved[i])){
                UserNotification item = this.notifications[1][i];
                if (deny){
                    item.deny();
                    db.update(item);
                    return item;
                }else{
                    item.resolve();
                    db.update(item);
                }
            }
        }

        return null;
    }

    public UserNotification userNotificationFromString(String notification){
        for(int i = 0; i < this.unresolved.length; i++){
            if (notification.equalsIgnoreCase(this.unresolved[i])){
                return this.notifications[1][i];
            }
        }
        return null;
    }
}