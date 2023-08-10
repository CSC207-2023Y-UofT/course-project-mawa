package Entities;

import java.util.Comparator;

public class SortUserByLN implements Comparator<User> {
    @Override
    public int compare(User s1, User s2) {
        int lnCompare = s1.getSurname().compareToIgnoreCase(s2.getSurname());
        return lnCompare;
    }
}
