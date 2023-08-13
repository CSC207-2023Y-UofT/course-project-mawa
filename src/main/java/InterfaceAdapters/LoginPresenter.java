package InterfaceAdapters;

import UseCases.EmptyAppValidator;
import UseCases.LoginValidator;
/**
 * The LoginPresenter class handles the presentation logic for the login process.
 * It interacts with the user interface and uses LoginValidator to authenticate users.
 */
public class LoginPresenter {
    private Page gui;

    /**
     * Constructs a LoginPresenter object.
     *
     * @param gui The user interface associated with the presenter.
     */
    public LoginPresenter(Page gui){
        this.gui = gui;
    }

    /**
     * Validates the user's credentials and returns the authenticated user's ID.
     *
     * @param userId The user's ID.
     * @param pwd The user's password as a character array.
     * @return The authenticated user's ID, or nonsense value if credentials are invalid.
     */
    public int handleUser(int userId, char[] pwd){
        int id = new LoginValidator().validateCredentials(userId, pwd);
        return id;
    }
    /**
     * Checks if the application starts with making HR account due to no users being in the
     * data file.
     *
     * @return true if the application starts with making HR account, false otherwise.
     */
    public boolean startWithHR(){
        EmptyAppValidator eav = new EmptyAppValidator();
        return eav.isEmpty();
    }

}
