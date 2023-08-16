package UseCases;


import Entities.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;




public class UserActivatorTest {

    User user;

    private EmptyAppValidator eav = new EmptyAppValidator("l");

    private UserActivator a = new UserActivator("a");

    private int id;


    @BeforeEach
    public void SetUp(){

        UserFactory uf = new UserFactory(".");
        UserInteractor ui = new UserInteractor(".");
        uf.makeUser("Boitor", "William", "Male", "2003", "01",
                "06", 6475504453L, "william.boitor@gmail.com", "Senior Actuarial Analyst",
                "Salary Worker", "william123", 150000F);
        user = ui.readData().get(ui.readData().size() - 1);
        id = user.getUserNum();

    }

    @Test
    public void TestActivationChange(){
        a.changeActivation(id);
        User newUser = a.idToUser(id);
        assertEquals(!user.isActive(), newUser.isActive());
    }

}
