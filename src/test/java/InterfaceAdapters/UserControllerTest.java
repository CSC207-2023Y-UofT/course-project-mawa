package InterfaceAdapters;

import Entities.User;
import UseCases.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    private UserController uc = new UserController(".");
    private int id;


    @BeforeEach
    public void SetUp(){

        UserFactory uf = new UserFactory(".");
        UserInteractor ui = new UserInteractor(".");
        uf.makeUser("Boitor", "William", "Male", "2003", "01",
                "06", 6475504453L, "william.boitor@gmail.com", "Senior Actuarial Analyst",
                "Salary Worker", "william123", 150000F);
        id = ui.readData().size();

    }

    @Test
    public void TestChangeActivation(){
        uc.changeActivation(id);
        UserFileReader ufr = new UserFileReader(".");
        assertEquals(ufr.getActive(id), false);

    }

    @Test
    public void TestMakePayment(){
        uc.makePayment(id);
        PaymentInteractor pi = new PaymentInteractor("l");
        PaymentFileReader pfr = new PaymentFileReader(".");
        int payID = pi.readData().size();
        assertEquals(pfr.getIds(id).get(0), payID);

    }

    @Test
    public void TestUserFromInput(){
        UserInteractor ui = new UserInteractor("l");
        User user = new User("Boitor", "William", "Male", "william.boitor@gmail.com",
                "Senior Actuarial Analyst",
                ui.readData().size() + 1, 6475504453L, "2003-01-06",
                new char[]{'w', 'i', 'l', 'l', 'i', 'a', 'm', '1', '2', '3'}, "Salary Worker",
                150000.00F);
        uc.userFromInput("Boitor", "William", "Male", "2003", "01", "06",
                6475504453L, "william.boitor@gmail.com", "Senior Actuarial Analyst",
                "Salary Worker", "william123", 150000.00F);
        ArrayList<User> users = ui.readData();
        User read = users.get(users.size() - 1);

        assertEquals(user.getSurname(), read.getSurname());
        assertEquals(user.getFirstname(), read.getFirstname());
        assertEquals(user.getGender(), read.getGender());
        assertEquals(user.getDob(), read.getDob());
        assertEquals(user.getEmail(), read.getEmail());
        assertEquals(user.getRole(), read.getRole());
        assertEquals(user.getPay(), read.getPay());
        assertEquals(user.getUserNum(), read.getUserNum());
        for (int i = 0; i < user.getPassword().length; i++){
            assertEquals(user.getPassword()[i], read.getPassword()[i]);
        }
    }

}
