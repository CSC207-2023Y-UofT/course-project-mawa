<<<<<<<< HEAD:src/InterfaceAdapters/NotificationBuilder.java
package InterfaceAdapters;
========
package UseCases;
>>>>>>>> dfc6f85c1b5d7b2c815142d6a56f64b32074dc0d:src/UseCases/NotificationBuilder.java

import Entities.UserNotificationRequest;
import Entities.Shift;
import Entities.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotificationBuilder{

    public UserNotificationRequest createRequest(Shift[] shifts, LocalDate[] days, String message, User sender, User reciever){
        String text = sender.name;
        if (shifts.length == 0){
            text += ' is requesting the following days off: ';
            text += days[0].toString();
            for (i = 1, i < len(days), i++){
                text += ", " + days[i].toString();
            }
        } else if (len(days) == 0) {
            text += ' is requesting the following shifts off: ';
            text += shifts[0].time.toString();
            for (i = 1, i < len(days), i++){
                text += ', ' + shifts[i].time.toString();
            }
        } else{
            text += ' is requesting the following days off: ';
            text += days[0].toString();
            for (i = 1, i < len(days), i++){
                text += ', ' + days[i].toString();
            }
            text += ', and the following shifts off: ';
            text += shifts[0].time.toString();
            for (i = 1, i < len(days), i++){
                text += ', ' + shifts[i].time.toString();
            }
        }
        text += '. ' + sender.name + ' wrote: ' + message
        return new RequestNotification(text, sender, reciever, LocalDateTime.now(), shifts, days);
    }

}
