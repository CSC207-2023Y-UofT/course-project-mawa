package UseCases;

import UseCases.*;
import Entities.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmptyAppValidatorTest {

    @Test(timeout = 50)
    public void testCheckingNonEmpty(){

        //As there are no use cases that we can use to guarantee that the
        UserFactory uf = new UserFactory();
        uf.makeUser("Sheen", "Charlie", "Male", "1955", "03",
                "10", 6475504489L, "charlie.sheen@gmail.com", "Actor",
                "Salary Worker", "charlie123", 250000F);
        EmptyAppValidator eav = new EmptyAppValidator();
        assertFalse(eav.isEmpty());


    }
}
