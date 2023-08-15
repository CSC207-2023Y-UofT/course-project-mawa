import Entities.User;
import Entities.UserNotification;
import Entities.UserNotificationRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserNotificationSortFunctions {

    @Test
    void testSortCreatedDate(){
        ArrayList<UserNotification> notifications = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            notifications.add(new UserNotificationRequest(124,2134,1234,"af", LocalDateTime.now().plusMinutes(1)));
        }
        UserNotification[] notifications2 = UserNotification.sortByCreatedDate(notifications);
        for(int i = 0; i < 100; i++){
            assertEquals(notifications2[i].getDate(),notifications.get(99-i).getDate());
        }
    }
    @Test
    void testSortResolvedDate(){
        ArrayList<UserNotification> notifications = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            UserNotification notification = new UserNotificationRequest(124,2134,1234,"af", LocalDateTime.now().plusMinutes(1));
            notification.resolve();
            notifications.add(notification);
        }
        UserNotification[] notifications2 = UserNotification.sortByCreatedDate(notifications);
        for(int i = 0; i < 100; i++){
            assertEquals(notifications2[i].getResolvedAt(),notifications.get(999-i).getResolvedAt());
        }

    }
}
