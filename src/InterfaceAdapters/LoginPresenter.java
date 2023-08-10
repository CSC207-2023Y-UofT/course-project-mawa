package InterfaceAdapters;

import java.util.Objects;

public class LoginPresenter {
    private Page gui;

    public LoginPresenter(Page gui){
        this.gui = gui;
    }

    public int handleUser(int userId, char[] pwd){
        int type = new LoginValidator().validateCredentials(userId, pwd);
        return type;
    }

}
