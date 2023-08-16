package InterfaceAdapters;

import UseCases.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
