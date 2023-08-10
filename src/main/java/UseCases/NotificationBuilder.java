package UseCases;

import Entities.*;

import java.time.LocalDateTime;

public class NotificationBuilder{

    UserNotificationInteractor uni = new UserNotificationInteractor();

    public void createRequest(int shift, String message, int sender, int reciever){
        UserFileReader ufr = new UserFileReader();
        if (ufr.getType(sender).equals("HR")){
            uni.writeData(new UserNotificationResponse(sender, reciever, shift, message, LocalDateTime.now()));
        } else {
            uni.writeData(new UserNotificationRequest(sender, reciever, shift, message, LocalDateTime.now()));
        }
    }

}
