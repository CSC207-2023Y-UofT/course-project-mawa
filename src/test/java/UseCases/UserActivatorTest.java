package UseCases;

import UseCases.*;
import Entities.*;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class UserActivatorTest {

    private User user;

    private EmptyAppValidator eav = new EmptyAppValidator("l");

    private UserActivator a = new UserActivator("a");


    @Before
    public void SetUp(){

        UserInteractor ui = new UserInteractor("m");
        if (eav.isEmpty()){
            user = new User("Boitor", "William", "Male", "william.boitor@gmail.com", "Senior Actuarial Analyst",
                    1, 6475504453L, "2003-01-06", new char[]{'w', 'i', 'l', 'l', 'i', 'a', 'm', '1', '2', '3'}, "Salary Worker",
                    150000.00F);
            ui.writeData(user);
        } else{
            user = ui.readData().get(0);
        }

    }

    @Test(timeout = 50)
    public void TestActivationChange(){
        a.changeActivation(1);
        User newUser = a.idToUser(1);
        assertEquals(!user.isActive(), newUser.isActive());
    }

}
