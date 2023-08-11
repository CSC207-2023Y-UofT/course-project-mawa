package InterfaceAdapters;

import UseCases.UserActivator;

public class UserController {

    public void changeActivation (int IDnum){
        UserActivator ua = new UserActivator();
        ua.changeActivation(IDnum);
    }

}
