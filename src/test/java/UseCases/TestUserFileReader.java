package UseCases;

import Entities.User;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Unit test for UserFileReader class.
 */
public class TestUserFileReader {
    private UserFileReader reader;
    private UserInteractor interactor;
    private List<User> list;
    private List<Integer> idList;
    @BeforeEach
    public void setUp() throws IOException {
        new FileWriter("testUsers.ser", false).close();//clear test user file
        reader = new UserFileReader("test");
        interactor = new UserInteractor("test");
        list = Instancio.ofList(User.class).size(10).create();
        idList = new ArrayList<>();
        for (User s:list){
            interactor.writeData(s);
            idList.add(s.getUserNum());
        }
        reader.update();
    }
    @Test
    public void testUpdate(){
        reader.update();
        assertEquals(list.size(), reader.getIds().size(), "update should provide new data.");
    }

    @Test
    public void testCheckUser(){
        reader.checkUser(idList.get(0));
        assertEquals(list.get(0).getUserNum(), reader.getUser(idList.get(0)).getUserNum(),
                "checkUser should produce the same User as indicated by the id inputted.");
    }
    @Test
    public void testGetType(){
        assertEquals(list.get(0).getType(), reader.getType(idList.get(0)),
                "The User type fetched from UserFileReader should be the same" +
                        "as the object's type attribute.");
    }
    @Test
    public void testGetAllIds(){
        assertEquals(idList, reader.getIds(),
                "The id lst retrieved from UserFileReader should be the same as a list " +
                        "of ids from the complete User list.");
    }

    @Test
    public void testGetIdsByActive(){
        boolean active = false;
        list.get(1).setActive(false);
        list.get(3).setActive(false);
        list.get(9).setActive(false);
        for (User s:list){
            interactor.update(s);
        }
        reader.update();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(list.get(1).getUserNum());
        expected.add(list.get(3).getUserNum());
        expected.add(list.get(9).getUserNum());
        for (int i:expected){
            assertTrue(reader.getIds(active).contains(i));
        }
    }

}