package InterfaceAdapters;

import UseCases.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;


public class CompleteUserListPresenterTest {


        int id1;

        int id2;

        CompleteUserListPresenter p = new CompleteUserListPresenter("l");

        @BeforeEach
        public void Setup(){
                UserFactory uf = new UserFactory("p");
                uf.makeUser("Smith", "Alan", "Male", "2003", "01",
                        "06", 6475784453L, "alan.smith@gmail.com", "Janitor",
                        "Wage Worker", "alan1234", 25.14F);
                id1 = uf.getNumUsers();
                uf.makeUser("Smith", "John", "Male", "1990", "01",
                        "25", 6475784450L, "john.smith@gmail.com", "HR Work",
                        "HR", "john123", 0);
                id2 = uf.getNumUsers();
                p.getFileReader().update();
        }

        @Test
        public void TestArrayCreationEmployee(){
                //Verify that the resulting array has the correct attributes.
                Object[] attributes = p.makeUserPanel(id1);
                assertEquals("Alan", attributes[0]);
                assertEquals("Smith", attributes[1]);
                assertEquals("Male", attributes[2]);
                assertEquals("alan.smith@gmail.com", attributes[3]);
                assertEquals("6475784453", attributes[4]);
                assertEquals("Janitor", attributes[5]);
                assertEquals(Integer.toString(id1), attributes[6]);
                assertEquals("2003-01-06", attributes[7]);
                assertEquals("Wage Worker", attributes[8]);
                assertEquals("25.14", attributes[9]);
                assertEquals("Yes", attributes[10]);
                assertEquals(id1, attributes[11]);

        }

        @Test
        public void TestArrayCreationHR(){
                //Verify that the resulting array has the correct attributes.
                Object[] attributes = p.makeUserPanel(id2);
                assertEquals("John", attributes[0]);
                assertEquals("Smith", attributes[1]);
                assertEquals("Male", attributes[2]);
                assertEquals("john.smith@gmail.com", attributes[3]);
                assertEquals("6475784450", attributes[4]);
                assertEquals("HR Work", attributes[5]);
                assertEquals(Integer.toString(id2), attributes[6]);
                assertEquals("1990-01-25", attributes[7]);
                assertEquals("HR", attributes[8]);
                assertEquals("0.0", attributes[9]);
                assertEquals("Yes", attributes[10]);
                assertEquals(0, attributes[11]);
        }


}
