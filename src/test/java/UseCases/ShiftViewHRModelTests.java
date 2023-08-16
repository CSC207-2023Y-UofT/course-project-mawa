package UseCases;

import Entities.Shift;
import Entities.User;
import Entities.UserNotification;
import Entities.UserNotificationRequest;
import org.junit.jupiter.api.Test;

import javax.management.Notification;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftViewHRModelTests {
    @Test
    void testGetShiftFromNotificationID(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",101, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.writeData(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(12);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,101);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        UserNotification notification = new UserNotificationRequest(101,101,101, String.valueOf(1), LocalDateTime.now().plusMinutes(1));
        notifications.add(notification);
        interactornotifications.update(notification);
        ShiftViewHRModel model = new ShiftViewHRModel(notification.getNotifId(),user.getUserNum());
        assertEquals(shift.getShiftId(), model.shift.getShiftId());
    }
    @Test
    void testGetUsers(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",1039, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.update(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(1039);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,1039);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        UserNotification notification = new UserNotificationRequest(1039,1039,1039, String.valueOf(1), LocalDateTime.now().plusMinutes(1));
        interactornotifications.update(notification);
        ShiftViewHRModel model = new ShiftViewHRModel(notification.getNotifId(),user.getUserNum());
        assertEquals(shift.getShiftId(), model.shift.getShiftId());
    }
    @Test
    void testUpdateShift(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",103, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.update(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(103);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,103);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        UserNotification notification = new UserNotificationRequest(103,103,103, String.valueOf(1), LocalDateTime.now().plusMinutes(1));
        interactornotifications.update(notification);
        ShiftViewHRModel model = new ShiftViewHRModel(notification.getNotifId(),user.getUserNum());
        model.getEmployeesOnShiftList().removeElement(0);
        for(int i=0; i < model.getEmployeesOnShiftList().size(); i++){
            System.out.println(model.shift.getCoworkers().get(i));
            model.getEmployeesOnShiftList().remove(i);
        }
        model.updateShift();
        ShiftFileReader sfr = new ShiftFileReader("test");
        assertEquals(0, sfr.getShift(103).getCoworkers().size());
    }

    @Test
    void testUpdateShiftAndNotification(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",105, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.update(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(105);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,105);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        UserNotification notification = new UserNotificationRequest(105,105,105, String.valueOf(1), LocalDateTime.now().plusMinutes(1));
        interactornotifications.update(notification);
        ShiftViewHRModel model = new ShiftViewHRModel(notification.getNotifId(),user.getUserNum());
        model.getEmployeesOnShiftList().removeElement(0);
        for(int i=0; i < model.getEmployeesOnShiftList().size(); i++){
            System.out.println(model.shift.getCoworkers().get(i));
            model.getEmployeesOnShiftList().remove(i);
        }
        model.updateShiftAndNotification();
        ShiftFileReader sfr = new ShiftFileReader("test");
        NotificationFileReader nfr = NotificationFileReader.getInstance();
        assertEquals(0, sfr.getShift(105).getCoworkers().size());
        ArrayList<UserNotification> nf = interactornotifications.readData();
        for (int t=0; t< nf.size(); t++){
            if (nf.get(t).getNotifId() == 105){
                assertEquals(nf.get(t).getResolvedStatus(), true);
            }
        }
    }

}
