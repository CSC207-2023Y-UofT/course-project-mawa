package UseCases;

import Entities.UserNotification;

public class NotificationSorter implements Sorter<UserNotification> {
    @Override
    public UserNotification[] sort(int id) {
        return new UserNotification[0];
    }
}
