package UseCases;

import Entities.*;

/**
 * The UserFactory class facilitates the creation of new user accounts.
 * It manages the creation of user objects by interacting with UserInteractor.
 */
public class UserFactory {

    private int numUsers; // Keeps track of the total number of users.

    private boolean isTest; // Indicates if the application is in a test environment.

    /**
     * Default constructor that initializes the UserFactory for non-test usage.
     * It sets the initial value of numUsers based on existing user data.
     */
    public UserFactory() {
        UserInteractor ui = new UserInteractor();
        numUsers = ui.readData().size();
        isTest = false;
    }

    /**
     * Constructor that allows specifying a test environment.
     * It sets the initial value of numUsers based on existing user data in the test environment.
     *
     * @param test A string identifier indicating the use of a test environment.
     */
    public UserFactory(String test) {
        UserInteractor ui = new UserInteractor(test);
        numUsers = ui.readData().size();
        isTest = true;
    }

    /**
     * Creates a new user account.
     *
     * @param surname     The user's last name.
     * @param firstname   The user's first name.
     * @param gender      The user's gender.
     * @param birthYear   The user's birth year.
     * @param birthMonth  The user's birth month.
     * @param birthDate   The user's birth date.
     * @param phoneNumber The user's phone number.
     * @param email       The user's email address.
     * @param role        The user's role (e.g., employee, manager).
     * @param type        The type of user (e.g., HR, regular user).
     * @param password    The user's password.
     * @param pay         The user's pay (applicable for certain roles).
     */
    public void makeUser(String surname, String firstname, String gender, String birthYear, String birthMonth,
                         String birthDate, long phoneNumber, String email, String role, String type, String password,
                         float pay) {
        String bday = birthYear + "-" + birthMonth + "-" + birthDate;
        UserInteractor uInt;

        // Initialize UserInteractor based on the test environment
        if (isTest) {
            uInt = new UserInteractor("w");
        } else {
            uInt = new UserInteractor();
        }

        // Convert password to character array
        char[] p = new char[password.length()];
        for (int i = 0; i < password.length(); i++) {
            p[i] = password.charAt(i);
        }

        // Create and write the new user object
        uInt.writeData(new User(surname, firstname, gender, email, role, numUsers + 1, phoneNumber, bday, p, type, pay));
        numUsers += 1; // Increment the total number of users
    }

    public int getNumUsers(){return this.numUsers;}

}
