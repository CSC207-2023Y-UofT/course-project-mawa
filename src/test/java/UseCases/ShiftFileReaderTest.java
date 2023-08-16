package UseCases;

import Entities.Shift;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for ShiftFileReader class.
 */
public class ShiftFileReaderTest {
    private ShiftFileReader reader;
    private ShiftInteractor interactor;
    private List<Shift> list;
    private List<Integer> idList;
    @BeforeEach
    public void setUp() throws IOException {
        new FileWriter("testShifts.ser", false).close();
        reader = new ShiftFileReader("test");
        interactor = new ShiftInteractor("test");
        list = Instancio.ofList(Shift.class).size(10).create();
        idList = new ArrayList<>();
        for (Shift s:list){
            interactor.writeData(s);
            idList.add(s.getShiftId());
        }
        reader.update();
    }
    @Test
    public void testUpdate(){
        reader.update();
        assertEquals(reader.getIds().size(), list.size(), "update should provide new data.");
    }

    @Test
    public void testCheckShift(){
        reader.checkShift(idList.get(0));
        assertEquals(list.get(0).getShiftId(), reader.getShift(idList.get(0)).getShiftId(),
                "checkShift should produce the same shift as indicated by the id inputted.");
    }
    @Test
    public void testGetCoworkers(){
        assertEquals(list.get(0).getCoworkers(), reader.getCoworkers(idList.get(0)),
                "The coworker id list fetched from ShiftFileReader should be the same" +
                        "as the object's coworker attribute.");
    }
    @Test
    public void testGetAllIds(){
        assertEquals(idList, reader.getIds(),
                "The id lst retrieved from ShiftFileReader should be the same as a list " +
                        "of ids from the complete shift list.");
    }

    @Test
    public void testGetIdsByEmpId(){
        int empId = 444;
        list.get(1).addCoworker(empId);
        list.get(3).addCoworker(empId);
        list.get(9).addCoworker(empId);
        for (Shift s:list){
            interactor.update(s);
        }
        reader.update();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(list.get(1).getShiftId());
        expected.add(list.get(3).getShiftId());
        expected.add(list.get(9).getShiftId());
        assertEquals(expected, reader.getIds(empId));
    }

}
