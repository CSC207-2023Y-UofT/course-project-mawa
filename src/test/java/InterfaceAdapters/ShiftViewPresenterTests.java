package InterfaceAdapters;

import Entities.Shift;
import Entities.User;
import Entities.UserNotification;
import Entities.UserNotificationRequest;
import UseCases.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftViewPresenterTests {
    @Test
    void testUpdateShiftForShiftView(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",1045, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.update(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(1045);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,1045);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        UserNotification notification = new UserNotificationRequest(1045,1045,1045, String.valueOf(1), LocalDateTime.now().plusMinutes(1));

        interactornotifications.update(notification);
        ShiftViewHRNotificationsPresenter presenter = new ShiftViewHRNotificationsPresenter(notification.getNotifId(),user.getUserNum());
        ShiftViewHRModel model = presenter.HRModel;
        for(int i=0; i < model.getEmployeesOnShiftList().size(); i++){
            model.getEmployeesOnShiftList().remove(i);
        }
        presenter.updateShiftEmployees();
        ShiftFileReader sfr = ShiftFileReader.getInstance();
        assertEquals(1, sfr.getShift(1045).getCoworkers().size());
    }

    @Test
    void testUpdateShiftAndNotificationForShiftView(){
        long l = 1234332144;
        char[] ll = {'i', 'b'};
        User user = new User("john","mich","male","dog@email.com", "driver",1056, l, "1992-12-01", ll,  "dob", 12.1f);
        UserInteractor interactoruser = new UserInteractor();
        interactoruser.update(user);
        UserNotificationInteractor interactornotifications = new UserNotificationInteractor();
        ArrayList<UserNotification> notifications = new ArrayList<>();
        ArrayList<Integer> kk = new ArrayList<>();
        kk.add(1056);
        Shift shift = new Shift(LocalDateTime.now(), kk,123f,1056);
        ShiftInteractor inter = new ShiftInteractor();
        inter.update(shift);
        UserNotification notification = new UserNotificationRequest(1056,1056,1056, String.valueOf(1), LocalDateTime.now().plusMinutes(1));
        interactornotifications.update(notification);
        ShiftViewHRModel model = new ShiftViewHRModel(notification.getNotifId(),user.getUserNum());
        model.getEmployeesOnShiftList().removeElement(0);
        for(int i=0; i < model.getEmployeesOnShiftList().size(); i++){
            System.out.println(model.shift.getCoworkers().get(i));
            model.getEmployeesOnShiftList().remove(i);
        }
        ShiftViewHRNotificationsPresenter presenter = new ShiftViewHRNotificationsPresenter(notification.getNotifId(),user.getUserNum());
        presenter.updateShiftEmployeesAndNotification();
        ShiftFileReader sfr = ShiftFileReader.getInstance();
        NotificationFileReader nfr = NotificationFileReader.getInstance();
        assertEquals(1, sfr.getShift(1056).getCoworkers().size());
        ArrayList<UserNotification> nf = interactornotifications.readData();
        for (int t=0; t< nf.size(); t++){
            if (nf.get(t).getNotifId() == 105){
                assertEquals(nf.get(t).getResolvedStatus(), true);
            }
        }
    }

}
