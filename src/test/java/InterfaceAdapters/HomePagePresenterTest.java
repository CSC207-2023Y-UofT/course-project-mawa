package InterfaceAdapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import UseCases.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class HomePagePresenterTest {


    int id1;

    int id2;

    HomePagePresenter hp = new HomePagePresenter("y");

    @BeforeEach
    public void Setup(){
        UserFactory uf = new UserFactory("l");
        uf.makeUser("Boitor", "William", "Male", "2003", "01",
                "06", 6475504453L, "william.boitor@gmail.com", "Senior Actuarial Analyst",
                "Salary Worker", "william123", 150000F);
        id1 = uf.getNumUsers();

        uf.makeUser("Jones", "Alan", "Male", "2003", "01",
                "06", 6475504453L, "alan.jones@gmail.com", "HR Man",
                "HR", "alan123", 0);
        id2 = uf.getNumUsers();
        hp.getFileReader().update();

    }



    @Test
    public void TestCorrectPanelEmployee(){
        assertEquals(hp.makeHomeButtons(id1).get(0), "Schedule");
        assertEquals(hp.makeHomeButtons(id1).get(1), "Notification Center");
        assertEquals(hp.makeHomeButtons(id1).get(2), "View Payment History");
    }

    @Test
    public void TestCorrectPanelHR(){
        assertEquals(hp.makeHomeButtons(id2).get(0), "Schedule");
        assertEquals(hp.makeHomeButtons(id2).get(1), "Notification Center");
        assertEquals(hp.makeHomeButtons(id2).get(2), "Manage Employees");
    }

}
