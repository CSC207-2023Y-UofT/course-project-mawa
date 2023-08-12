package UseCases;

import Entities.UserNotification;
import UseCases.NotificationFileReader;
import UseCases.UserNotificationInteractor;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNotificationFileReader {
    private NotificationFileReader reader;
    private UserNotificationInteractor interactor;
    private List<UserNotification> list;
    private List<Integer> idList;
    @BeforeEach
    public void setUp() throws IOException {
        new FileWriter("notifications.ser", false).close();
        reader = NotificationFileReader.getInstance();
        interactor = new UserNotificationInteractor();
        list = Instancio.ofList(UserNotification.class).size(10).create();
        idList = new ArrayList<>();
        for (UserNotification s:list){
            interactor.writeData(s);
            idList.add(s.getNotifId());
        }
    }
    @Test
    public void testUpdate(){
        reader.update();
        assertEquals(reader.getIds().size(), list.size(), "update should provide new data.");
    }

    @Test
    public void testCheckUserNotification(){
        int idx = 7;
        reader.checkNotification(idList.get(idx));
        assertEquals(list.get(idx).getNotifId(), reader.getUserNotification(idList.get(idx)).getNotifId(),
                "checkNotification should produce the same UserNotification as indicated by the id inputted.");
    }
    @Test
    public void testGetDateCreated(){
        assertEquals(list.get(2).getDate(), reader.getDateCreated(idList.get(2)),
                "The date fetched from UserNotificationFileReader should be the same" +
                        "as the object's date attribute.");
    }
    @Test
    public void testGetAllIds(){
        assertEquals(idList, reader.getIds(),
                "The id lst retrieved from UserNotificationFileReader should be the same as a list " +
                        "of ids from the complete UserNotification list.");
    }

    @Test
    public void testGetIdsByDeny(){
        list.get(1).deny();
        list.get(3).deny();
        list.get(9).deny();
        for (UserNotification s:list){
            interactor.update(s);
        }
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(list.get(1).getNotifId());
        expected.add(list.get(3).getNotifId());
        expected.add(list.get(9).getNotifId());
        ArrayList<Integer> result = reader.getDenyIds(true);
        boolean assertion = true;
        for (int i:expected){
            if (!result.contains(i)){
                assertion = false;
            }
        }
        assertTrue(assertion);
    }

}