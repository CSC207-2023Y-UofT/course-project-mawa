package InterfaceAdapters;
import Entities.User;
import UseCases.UserFileReader;

public class NotificationMiddleManPresenter {
    int userId;
    public NotificationMiddleManPresenter(int userID){
        userId = userID;
    }
    public boolean isHR(){
        UserFileReader reader = UserFileReader.getInstance();
        return userId == reader.getHRId();
    }
}
