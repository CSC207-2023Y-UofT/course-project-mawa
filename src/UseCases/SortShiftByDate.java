package UseCases;

import Entities.Shift;

import java.util.Comparator;

public class SortShiftByDate implements Comparator<Shift> {

    private UserInteractor userInteractor = new UserInteractor();

    @Override
    public int compare(Shift s1, Shift s2) {
        int dateCompare = s1.getTime().compareTo(s2.getTime());
        int roleCompare = UserInteractor.getUser(s1.getCoworkers().get(0)).getRoleName().compareTo(
                UserInteractor(s2.getCoworkers().get(0)).getRoleName());
        return (dateCompare == 0) ? roleCompare
                : dateCompare;
    }
}
