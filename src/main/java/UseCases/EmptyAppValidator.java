package UseCases;

/**
 * The EmptyAppValidator class is responsible for validating whether an application
 * has any users or is empty.
 */
public class EmptyAppValidator {

    private UserInteractor ui; // Handles interaction with user data.

    /**
     * Default constructor that initializes an instance of UserInteractor for regular usage.
     */
    public EmptyAppValidator() {
        this.ui = new UserInteractor();
    }

    /**
     * Constructor that initializes an instance of UserInteractor for a test environment.
     *
     * @param isTest A string indicating if the application is in a test environment.
     */
    public EmptyAppValidator(String isTest) {
        this.ui = new UserInteractor(isTest);
    }

    /**
     * Checks whether the application is empty, i.e., if there are no existing users.
     *
     * @return True if the application has no users, false otherwise.
     */
    public boolean isEmpty() {
        // Return whether the number of existing users is 0.
        return (ui.readData().size() == 0);
    }
}
