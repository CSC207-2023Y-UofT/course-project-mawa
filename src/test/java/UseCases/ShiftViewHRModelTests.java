package UseCases;

import Entities.Shift;
import Entities.User;
import Entities.UserNotification;
import Entities.UserNotificationRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftViewHRModelTests {
    @Test
    void testGetShiftFromNotificationID(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",12, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.writeData(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(12);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,-11);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        UserNotification notification = new UserNotificationRequest(12,12,-11, String.valueOf(1), LocalDateTime.now().plusMinutes(1));
        ShiftViewHRModel model = new ShiftViewHRModel(notification.getNotifId(),user.getUserNum());
        model.getShiftFromNotificationId();
        assertEquals(model.shift.getShiftId(), shift.getShiftId());
    }
}
