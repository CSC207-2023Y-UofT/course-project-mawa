package UseCases;


import Entities.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class UserInteractorTest {


    UserInteractor ui = new UserInteractor("p");


    @Test
    public void TestReadWriting(){

        User user = new User("Heffley", "Greg", "Male", "diaryofawimpykid@gmail.com", "Middle Schooler",
                ui.readData().size() + 1, 6475508953L, "2003-02-08", new char[]{'g', 'r', 'e', 'g', '1', '2', '3'}, "Salary Worker",
                150000.00F);
        ui.writeData(user);
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

    @Test
    public void TestUpdate(){

        User user = new User("Heffley", "Frank", "Male", "diaryofawimpydad@gmail.com", "Greg's Dad",
                ui.readData().size() + 1, 6475598953L, "1977-02-08", new char[]{'f', 'r', 'a', 'n', 'k', '1', '2', '3'}, "Salary Worker",
                150000.00F);
        ui.writeData(user);
        user.setActive(false);
        ui.update(user);
        ArrayList<User> users = ui.readData();
        User newUser = users.get(users.size() - 1);
        assertFalse(newUser.isActive());


    }



}
