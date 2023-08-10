package UseCases;

import Entities.*;
import InterfaceAdapters.ShiftFileReader;
import InterfaceAdapters.UserFileReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NotificationBuilder{

    public UserNotification createRequest(int shift, String message, int sender, int reciever){
        UserFileReader ufr = new UserFileReader();
        if (ufr.getType(sender).equals("HR")){
            return new UserNotificationResponse(sender, reciever, shift, message, LocalDateTime.now());
        } else {
            return new UserNotificationRequest(sender, reciever, shift, message, LocalDateTime.now());
        }
    }

}
