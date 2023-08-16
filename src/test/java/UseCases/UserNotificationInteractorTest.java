package UseCases;

/*
import Entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserNotificationInteractorTest {


    UserNotificationInteractor ui = new UserNotificationInteractor("p");

    int id1;

    int id2;

    int shiftID;

    UserNotification notif;

    UserNotification read;


    @BeforeEach
    public void Setup() throws InvalidTimeException {
        //Create a notification to read/write.
        UserFactory uf = new UserFactory("p");
        uf.makeUser("Smith", "Alan", "Male", "2003", "01",
                "06", 6475784453L, "alan.smith@gmail.com", "Janitor",
                "Wage Worker", "alan1234", 25.14F);
        id1 = uf.getNumUsers();
        uf.makeUser("Smith", "John", "Male", "1990", "01",
                "25", 6475784450L, "john.smith@gmail.com", "HR Work",
                "HR", "john123", 0);
        id2 = uf.getNumUsers();
        ShiftMaker s = new ShiftMaker(LocalDateTime.of(2023, 9, 8, 05, 55),
                2.0F, "l");
        s.makeShift();
        ShiftInteractor si = new ShiftInteractor("l");
        shiftID = si.readData().size();
        notif = new UserNotificationRequest(id1, id2, shiftID, "123",
                LocalDateTime.of(2023, 9, 8, 05, 55));


    }

    @Test
    public void TestReadWrite(){
        //Create a notification, write/read it, and verify that the information matches.
        ui.writeData(notif);
        read = ui.readData().get((ui.readData().size() - 1));
        assertEquals(notif.getSenderId(), read.getSenderId());
        assertEquals(notif.getNotifId(), read.getNotifId());
        assertEquals(notif.getRecipientId(), read.getRecipientId());
        assertEquals(notif.getResolvedStatus(), read.getResolvedStatus());
        assertEquals(notif.getMessage(), read.getMessage());
        assertEquals(notif.getDate(), read.getDate());
    }

    @Test
    public void TestUpdate(){
        //Resolve a notification, and verify that it is changed in the file.
        notif.resolve();
        ui.update(notif);
        ArrayList<UserNotification> notifs = ui.readData();
        UserNotification newNotif = notifs.get(notifs.size() - 1);
        assertTrue(newNotif.getResolvedStatus());


    }



}
*/