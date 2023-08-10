import Entities.User;
import Entities.UserNotification;
import Entities.UserNotificationRequest;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestUserNotificationSortFunctions {
    public ArrayList<UserNotification> notifications;
    public UserNotification[] notifications2;
    @BeforeAll
    void setup(){
        notifications = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            notifications.add(new UserNotificationRequest(124,2134,1234,"af", LocalDateTime.now().plusMinutes(1)));
        }
        notifications2 = UserNotification.sortByCreatedDate(notifications);
    }
    @Test
    void testSortCreatedDate(){
        for(int i = 0; i < 100; i++){
            assert(notifications2[0].getDate().equals(notifications.get(100)));
        }

    }
}
