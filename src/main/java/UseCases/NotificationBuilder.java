package UseCases;

import Entities.*;
import java.time.LocalDateTime;

/**
 * The NotificationBuilder class is responsible for creating and sending user notifications,
 * whether they are notifications of request or response type.
 */
public class NotificationBuilder {

    private UserNotificationInteractor uni;

    private UserFileReader ufr;

    public NotificationBuilder(){
        this.uni = new UserNotificationInteractor();
        this.ufr = UserFileReader.getInstance();
    }

    public NotificationBuilder(String test){
        this.uni = new UserNotificationInteractor(test);
        this.ufr = new UserFileReader(test);
    }

    /**
     * Creates and sends a user notification.
     *
     * @param shift     The ID of the shift associated with the notification.
     * @param message   The content of the notification message.
     * @param sender    The ID of the user sending the notification.
     * @param receiver  The ID of the user receiving the notification.
     */
    public void createRequest(int shift, String message, int sender, int receiver) {

        // Check the sender's user type to determine the notification type
        if (ufr.getType(sender).equals("HR")) {
            // If the sender is an HR user, create a response-type notification
            uni.writeData(new UserNotificationResponse(sender, receiver, shift, message, LocalDateTime.now()));
        } else {
            // If the sender is not an HR user, create a request-type notification
            uni.writeData(new UserNotificationRequest(sender, receiver, shift, message, LocalDateTime.now()));
        }
    }

    public UserFileReader getFileReader(){return ufr;}
}
