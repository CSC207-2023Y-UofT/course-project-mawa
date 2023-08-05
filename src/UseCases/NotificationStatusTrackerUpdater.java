package UseCases;
import Entities.UserNotification;

public class NotificationStatusTrackerUpdater {
    int user;
    UserNotification[][] notifications;
    public NotificationStatusTrackerUpdater(int userID){
        user = userID;
    }

}
