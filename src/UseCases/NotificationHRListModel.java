package UseCases;
import javax.swing.*;

public class NotificationHRListModel extends DefaultListModel<String> {
    public String[] Notifications;
    public DefaultListModel<String> listModel = new DefaultListModel<String>();
    public int user;
    public String userName;

    public NotificationHRListModel(int userID){
        user = userID;
    }


}
