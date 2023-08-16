package UseCases;

import UseCases.*;
import Entities.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;


public class NotificationBuilderTest {

        private int id1;

        private int id2;

        private int shiftID;

        NotificationBuilder nb = new NotificationBuilder(".");

        @BeforeEach
        public void Setup() throws InvalidTimeException {
                ShiftMaker s = new ShiftMaker(LocalDateTime.of(2023, 9, 8, 05, 55),
                        2.0F, "l");
                s.makeShift();
                ShiftInteractor si = new ShiftInteractor("l");
                shiftID = si.readData().size();
                UserFactory uf = new UserFactory("p");
                uf.makeUser("Smith", "Alan", "Male", "2003", "01",
                        "06", 6475784453L, "alan.smith@gmail.com", "Janitor",
                        "Wage Worker", "alan1234", 25.14F);
                id1 = uf.getNumUsers();
                uf.makeUser("Smith", "John", "Male", "1990", "01",
                        "25", 6475784450L, "john.smith@gmail.com", "HR Work",
                        "HR", "john123", 0);
                id2 = uf.getNumUsers();
        }

        @Test
        public void TestCreateNotification(){
                nb.createRequest(shiftID, "123", id1, id2);
                nb.getFileReader().update();
                UserNotificationInteractor uni = new UserNotificationInteractor(".");
                UserNotification notif = uni.readData().get(uni.readData().size() - 1);
                assertEquals(notif.getMessage(), "123");
                assertEquals(notif.getShiftId(), shiftID);
                assertEquals(notif.getRecipientId(), id2);
                assertEquals(notif.getSenderId(), id1);
                assertEquals(notif.getResolvedStatus(), false);
        }


}
