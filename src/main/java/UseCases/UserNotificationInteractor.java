package UseCases;

import java.io.*;
import java.util.ArrayList;
import Entities.*;

/**
 * The UserNotificationInteractor class manages reading, writing, updating, and retrieving user notification data.
 * It implements the Interactor interface for UserNotification objects.
 */
public class UserNotificationInteractor implements Interactor<UserNotification> {

    private String fileName; // Stores the name of the user notification data file.

    /**
     * Default constructor that initializes the UserNotificationInteractor with the default notification data file name.
     */
    public UserNotificationInteractor() {
        this.fileName = FileNameConstants.NOTIFICATION_FILE_NAME;
    }

    /**
     * Constructor that specifies the file name to be that of testing data.
     *
     * @param isTest A string identifier indicating the use of a test notification data file.
     */
    public UserNotificationInteractor(String isTest) {
        this.fileName = "testNotifications.ser";
    }

    /**
     * Reads user notification data from the notification data file.
     *
     * @return An ArrayList of UserNotification objects read from the file.
     */
    public ArrayList<UserNotification> readData() {
        ArrayList<UserNotification> notifList = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            notifList.addAll((ArrayList<UserNotification>) input.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return notifList;
    }

    /**
     * Updates an existing user notification in the notification data file.
     *
     * @param n The updated UserNotification object.
     */
    @Override
    public void update(UserNotification n) {
        ArrayList<UserNotification> notifs = this.readData();
        notifs.removeIf(notif -> n.getNotifId() == notif.getNotifId());
        notifs.add(n);

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(notifs);
            output.close();
            NotificationFileReader.getInstance().update();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Writes a new user notification object to the notification data file.
     *
     * @param notification The UserNotification object to be written to the file.
     */
    public void writeData(UserNotification notification) {
        ArrayList<UserNotification> notifList = this.readData();
        notifList.add(notification);

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(notifList);
            output.close();
            NotificationFileReader.getInstance().update();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves user notifications associated with a specific user ID.
     *
     * @param userID The ID of the user for whom to retrieve notifications.
     * @return An ArrayList of UserNotification objects related to the given user ID.
     */
    public ArrayList<UserNotification> getNotificationByUserID(int userID) {
        ArrayList<UserNotification> notifications = this.readData();
        ArrayList<UserNotification> userNotifications = new ArrayList<>();
        for (UserNotification n : notifications) {
            if (n.getSenderId() == userID || n.getRecipientId() == userID) {
                userNotifications.add(n);
            }
        }
        return userNotifications;
    }
}
