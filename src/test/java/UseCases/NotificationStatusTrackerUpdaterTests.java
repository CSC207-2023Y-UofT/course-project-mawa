package UseCases;
import Entities.Shift;
import Entities.User;
import org.junit.jupiter.api.Test;
import Entities.UserNotification;
import Entities.UserNotificationRequest;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationStatusTrackerUpdaterTests {
    @Test
    void testGetSortedResolvedAndUnresolvedNotificationsResolved(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",12, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.writeData(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(12);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,1111);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        for (int i = 0; i < 10; i++){
            UserNotification notification = new UserNotificationRequest(12,12,1111, String.valueOf(i), LocalDateTime.now().plusMinutes(1));
            notification.resolve();
            notifications.add(notification);
            interactornotifications.update(notification);
        }
        NotificationStatusTrackerUpdater status = new NotificationStatusTrackerUpdater(user.getUserNum());
        UserNotification[][] notifications2 = status.getSortedResolvedAndUnresolvedNotifications(user.getUserNum());
        for(int m = 0; m < 10; m++){
            assertEquals(notifications2[0][m].getMessage(), notifications.get(9-m).getMessage());
        }
    }
    @Test
    void testGetSortedResolvedAndUnresolvedNotificationsUnresolved(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",12, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.writeData(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(12);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,1111);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        for (int i = 0; i < 10; i++){
            UserNotification notification = new UserNotificationRequest(12,12,1111, String.valueOf(i), LocalDateTime.now().plusMinutes(1));
            //notification.resolve();
            notifications.add(notification);
            interactornotifications.update(notification);
        }
        NotificationStatusTrackerUpdater status = new NotificationStatusTrackerUpdater(user.getUserNum());
        UserNotification[][] notifications2 = status.getSortedResolvedAndUnresolvedNotifications(user.getUserNum());
        for(int m = 0; m < 10; m++){
            assertEquals(notifications2[1][m].getMessage(), notifications.get(9-m).getMessage());
        }
    }
    @Test
    void testGetSortedResolvedAndUnresolvedNotificationsDenied(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",12, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.writeData(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(12);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,1111);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        for (int i = 0; i < 10; i++){
            UserNotification notification = new UserNotificationRequest(12,12,1111, String.valueOf(i), LocalDateTime.now().plusMinutes(1));
            notification.deny();
            notifications.add(notification);
            interactornotifications.update(notification);
        }
        NotificationStatusTrackerUpdater status = new NotificationStatusTrackerUpdater(user.getUserNum());
        UserNotification[][] notifications2 = status.getSortedResolvedAndUnresolvedNotifications(user.getUserNum());
        for(int m = 0; m < 10; m++){
            assertEquals(notifications2[0][m].getMessage(), notifications.get(9-m).getMessage());
        }
    }
    @Test
    void testNotificationUpdater(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",12, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.writeData(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(12);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,1111);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        for (int i = 0; i < 10; i++){
            UserNotification notification = new UserNotificationRequest(12,12,1111, String.valueOf(i), LocalDateTime.now().plusMinutes(1));
            notifications.add(notification);
            interactornotifications.update(notification);
        }
        NotificationStatusTrackerUpdater status = new NotificationStatusTrackerUpdater(user.getUserNum());
        for(int i = 0; i < 10; i++){
            status.notificationUpdater(status.getUnresolvedArray()[i], false);
        }
        NotificationStatusTrackerUpdater status2 = new NotificationStatusTrackerUpdater(user.getUserNum());
        assertEquals(status2.getUnresolvedArray().length, 0);
    }
    @Test
    void testUserNotificationFromString(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",12, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.writeData(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(12);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,1111);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        for (int i = 0; i < 10; i++){
            UserNotification notification = new UserNotificationRequest(12,12,1111, String.valueOf(i), LocalDateTime.now().plusMinutes(1));
            notification.deny();
            notifications.add(notification);
            interactornotifications.update(notification);
        }
        NotificationStatusTrackerUpdater status = new NotificationStatusTrackerUpdater(user.getUserNum());
        String[] notifications2 = status.getResolvedArray();
        ArrayList<UserNotification> list = new ArrayList<>();
        for(int m = 0; m < 10; m++){
            list.add(status.userNotificationFromString(notifications2[m]));
        }
        assertEquals(list.size(), notifications.size());
    }
}

