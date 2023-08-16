package Entities;

import Entities.UserNotification;
import Entities.UserNotificationRequest;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserNotificationSortFunctions {

    @Test
    void testSortCreatedDate(){
        ArrayList<UserNotification> notifications = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            notifications.add(new UserNotificationRequest(124,2134,1234,"af", LocalDateTime.now().plusMinutes(1)));
        }
        UserNotification[] notifications2 = UserNotification.sortByCreatedDate(notifications);
        for(int i = 0; i < 50; i++){
            assertEquals(notifications2[i].getDate(),notifications.get(49-i).getDate());
        }
    }
    @Test
    void testSortResolvedDate(){
        ArrayList<UserNotification> notifications = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            UserNotification notification = new UserNotificationRequest(124,2134,1234,"af", LocalDateTime.now().plusMinutes(1));
            notification.resolve();
            notifications.add(notification);
        }
        UserNotification[] notifications2 = UserNotification.sortByCreatedDate(notifications);
        for(int i = 0; i < 50; i++){
            assertEquals(notifications2[i].getResolvedAt().toLocalDate().toString(),notifications.get(49-i).getResolvedAt().toLocalDate().toString());
        }
    }
}
