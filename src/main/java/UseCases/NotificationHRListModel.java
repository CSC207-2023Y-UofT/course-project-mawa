package UseCases;
import javax.swing.*;

public class NotificationHRListModel extends DefaultListModel<String> {
    public NotificationStatusTrackerUpdater status;
    public DefaultListModel<String> listModel = new DefaultListModel<String>();
    public int user;
    public String userName;
    public boolean resolvedList;

    public NotificationHRListModel(int userID, boolean resolved){
        user = userID;
        resolvedList = resolved;
        status = new NotificationStatusTrackerUpdater(userID);
    }

    public void populateList(){
        if (this.resolvedList){
            for(String s: status.resolved){
                this.listModel.addElement(s);
            }
        }else{
            for(String s: status.unresolved){
                this.listModel.addElement(s);
            }
        }
    }


}
