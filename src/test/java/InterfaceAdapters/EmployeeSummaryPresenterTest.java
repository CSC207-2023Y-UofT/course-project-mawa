package InterfaceAdapters;

import UseCases.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeSummaryPresenterTest {

    int id1;

    int id2;

    EmployeeSummaryPresenter p = new EmployeeSummaryPresenter(".");



    @BeforeEach
    public void Setup(){
        UserFactory uf = new UserFactory("p");
        uf.makeUser("Smith", "Samantha", "Female", "2003", "01",
                "06", 6475789453L, "samantha.smith@gmail.com", "Receptionist",
                "Salary Worker", "samantha123", 100000F);
        id1 = uf.getNumUsers();
        uf.makeUser("Smith", "David", "Male", "2006", "01",
                "25", 6475787150L, "david.smith@gmail.com", "Errand Boy",
                "Volunteer", "david123", 0);
        id2 = uf.getNumUsers();
        p.getFr().update();
    }

    @Test
    public void TestArrayCreationNonVolunteer(){
        Object[] attributes = p.makeEmployeePanel(id1);
        assertEquals("Samantha", attributes[0]);
        assertEquals("Smith", attributes[1]);
        assertEquals("Female", attributes[2]);
        assertEquals("samantha.smith@gmail.com", attributes[3]);
        assertEquals("6475789453", attributes[4]);
        assertEquals("Receptionist", attributes[5]);
        assertEquals(Integer.toString(id1), attributes[6]);
        assertEquals("2003-01-06", attributes[7]);
        assertEquals("Salary Worker", attributes[8]);
        assertEquals("100000.0", attributes[9]);
        assertEquals(id1, attributes[10]);

    }

    @Test
    public void TestArrayCreationVolunteer(){
        Object[] attributes = p.makeEmployeePanel(id2);
        assertEquals("David", attributes[0]);
        assertEquals("Smith", attributes[1]);
        assertEquals("Male", attributes[2]);
        assertEquals("david.smith@gmail.com", attributes[3]);
        assertEquals("6475787150", attributes[4]);
        assertEquals("Errand Boy", attributes[5]);
        assertEquals(Integer.toString(id2), attributes[6]);
        assertEquals("2006-01-25", attributes[7]);
        assertEquals("Volunteer", attributes[8]);
        assertEquals("0.0", attributes[9]);
        assertEquals(0, attributes[10]);

    }


}
