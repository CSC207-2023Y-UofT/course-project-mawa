package UseCases;

import Entities.Shift;
import Entities.User;

import java.util.ArrayList;

/**
 * The UserActivator class handles the activation and deactivation of user accounts.
 * It interacts with User and Shift objects and provides methods for changing user activation status.
 */
public class UserActivator {

    private UserFileReader ufr = UserFileReader.getInstance(); // Provides access to user data.

    private boolean isTest; // Indicates if the application is in a test environment.

    /**
     * Default constructor that sets the UserActivator for non-test usage.
     */
    public UserActivator() {
        isTest = false;
    }

    /**
     * Constructor that allows specifying a test environment.
     *
     * @param t A string identifier indicating the use of a test environment.
     */
    public UserActivator(String t) {
        isTest = true;
    }

    /**
     * Changes the activation status of a user.
     *
     * @param IDNum The ID of the user whose activation status needs to be changed.
     */
    public void changeActivation(int IDNum) {
        UserInteractor ui;
        ShiftInteractor si;

        // Initialize UserInteractor and ShiftInteractor based on the test environment
        if (isTest) {
            ui = new UserInteractor("l");
            si = new ShiftInteractor(".");
        } else {
            ui = new UserInteractor();
            si = new ShiftInteractor();
        }

        ArrayList<Shift> shifts = si.readData();
        if (ufr.getActive(IDNum)) {
            // Deactivate the user
            User user = this.idToUser(IDNum);
            for (Shift shift : shifts) {
                shift.removeCoworker(IDNum);
                si.update(shift);
            }
            user.setActive(false);
            ui.update(user);
        } else {
            // Activate the user
            User user = this.idToUser(IDNum);
            user.setActive(true);
            ui.update(user);
        }

    }

    /**
     * Converts a user ID to a User object.
     *
     * @param idNum The ID of the user to retrieve.
     * @return The User object corresponding to the given ID, or null if not found.
     */
    public User idToUser(int idNum) {
        UserInteractor ui;

        // Initialize UserInteractor based on the test environment
        if (isTest) {
            ui = new UserInteractor("l");
        } else {
            ui = new UserInteractor();
        }

        ArrayList<User> users = ui.readData();
        for (User user : users) {
            if (user.getUserNum() == idNum) {
                return user;
            }
        }

        return null;
    }
}
