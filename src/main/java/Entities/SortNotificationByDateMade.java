package Entities;

import java.util.Comparator;

public class SortNotificationByDateMade implements Comparator<UserNotification> {
    @Override
    public int compare(UserNotification s1, UserNotification s2) {
        int dateCompare = s1.getDate().compareTo(s2.getDate());
        return dateCompare;
    }
}
