package FrameworksAndDrivers;

import InterfaceAdapters.LoginValidator;
import InterfaceAdapters.Page;

import java.util.Objects;

public class LoginPresenter {
    private Page gui;

    public LoginPresenter(Page gui){
        this.gui = gui;
    }

    public void handleUser(int userId, char[] pwd){
        String type = new LoginValidator().validateCredentials(userId, pwd);
        if(Objects.equals(type, "")){
            gui.update();
        } else if (Objects.equals(type, "HR")){
            new HRHomePage(userId);
        } else{
            new EmployeeHomePage(userId);
        }
    }

}
