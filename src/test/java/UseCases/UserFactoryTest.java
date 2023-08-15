package UseCases;

import UseCases.*;
import Entities.*;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserFactoryTest {

    UserInteractor ui;
    UserFactory uf;
    /*A user created with the user's constructor will be compared to a user obtained from the databse, which is created
    * by UserFactory. The test should verify that their attributes are identical.*/
    User user;
    User userFac;

    
    @Before
    public void setUp(){
        ui = new UserInteractor("k");
        uf = new UserFactory("j");
        int n = ui.readData().size();
        user = new User("Boitor", "William", "Male", "william.boitor@gmail.com", "Senior Actuarial Analyst",
                n + 1, 6475504453L, "2003-01-06", new char[]{'w', 'i', 'l', 'l', 'i', 'a', 'm', '1', '2', '3'}, "Salary Worker",
                150000.00F);
        uf.makeUser("Boitor", "William", "Male", "2003", "01",
                "06", 6475504453L, "william.boitor@gmail.com", "Senior Actuarial Analyst",
                "Salary Worker", "william123", 150000F);
        ArrayList<User> users = ui.readData();
        userFac = users.get(users.size() - 1);

    }

    @Test(timeout = 50)
    public void testEqual(){
        assertEquals(user.getSurname(), userFac.getSurname());
        assertEquals(user.getFirstname(), userFac.getFirstname());
        assertEquals(user.getGender(), userFac.getGender());
        assertEquals(user.getDob(), userFac.getDob());
        assertEquals(user.getEmail(), userFac.getEmail());
        assertEquals(user.getRole(), userFac.getRole());
        assertEquals(user.getPay(), userFac.getPay());
        assertEquals(user.getUserNum(), userFac.getUserNum());
        assertEquals(user.getPassword(), userFac.getPassword());
    }



}
