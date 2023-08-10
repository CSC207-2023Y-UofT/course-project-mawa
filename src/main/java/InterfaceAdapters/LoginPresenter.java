package InterfaceAdapters;

import UseCases.EmptyAppValidator;
import UseCases.LoginValidator;

public class LoginPresenter {
    private Page gui;

    public LoginPresenter(Page gui){
        this.gui = gui;
    }

    public int handleUser(int userId, char[] pwd){
        int id = new LoginValidator().validateCredentials(userId, pwd);
        return id;
    }

    public boolean startWithHR(){
        EmptyAppValidator eav = new EmptyAppValidator();
        return eav.isEmpty();
    }

}
