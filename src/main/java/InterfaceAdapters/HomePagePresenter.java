package InterfaceAdapters;

import UseCases.UserFileReader;

import java.util.ArrayList;

/**
 * The HomePagePresenter class handles presenting home page buttons for display.
 * It creates a list of button labels based on the user's role for UI rendering.
 */
public class HomePagePresenter {

    private UserFileReader ufr = UserFileReader.getInstance();

    /**
     * Creates a list of home page buttons' labels for display.
     *
     * @param id The ID of the user for whom to create the home page buttons.
     * @return An ArrayList of button labels based on the user's role.
     */
    public ArrayList<String> makeHomeButtons(int id) {

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Schedule"); // Always display the "Schedule" button
        labels.add("Notification Center"); // Always display the "Notification Center" button

        // Determine the user's role and add corresponding buttons
        if (ufr.getType(id).equals("HR")) {
            labels.add("Manage Employees"); // For HR users
        } else {
            labels.add("View Payment History"); // For non-HR users
        }

        return labels;
    }
}
