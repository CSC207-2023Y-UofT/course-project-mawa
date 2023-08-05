package UseCases;
import javax.swing.*;

public class NotificationHRListModel extends DefaultListModel<String> {
    public NotificationStatusTrackerUpdater status;
    public DefaultListModel<String> listModel = new DefaultListModel<String>();
    public int user;
    public String userName;

    public NotificationHRListModel(int userID){
        user = userID;
        status = new NotificationStatusTrackerUpdater(userID);

    }


}
