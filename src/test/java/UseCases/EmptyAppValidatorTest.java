package UseCases;

import UseCases.*;
import Entities.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class EmptyAppValidatorTest {

    @BeforeEach
    public void Setup(){
        UserFactory uf = new UserFactory("t");
        uf.makeUser("Sheen", "Charlie", "Male", "1955", "03",
                "10", 6475504489L, "charlie.sheen@gmail.com", "Actor",
                "Salary Worker", "charlie123", 250000F);
    }

    @Test
    public void testCheckingNonEmpty(){
        EmptyAppValidator eav = new EmptyAppValidator("w");
        assertFalse(eav.isEmpty());


    }
}
