package InterfaceAdapters;

import UseCases.ShiftFileReader;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDayCellPresenter {
    private DayCellPresenter dcp;

    @Test
    public void testGetYCoords(){
        ArrayList<Integer> shifts = new ArrayList<>();
        shifts.add(1);
        shifts.add(2);
        dcp =  Instancio.of(DayCellPresenter.class)
                .set(field(DayCellPresenter.class, "shifts"), shifts)
                .create();
        dcp.height = 10000;
        LocalDateTime time1 = LocalDateTime.of(2023, 8, 11, 9, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 8, 11, 13, 0);
        ShiftFileReader mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public LocalDateTime getDate(int id) {
                if (id == 1) return time1;
                else if (id == 2) return time2;
                return null;
            }
        };

        dcp.reader = mockShiftFileReader;
        ArrayList<Integer> ycoords = dcp.getYcoords();

        assertEquals(2, ycoords.size());
        assertEquals(2625, ycoords.get(0));
        assertEquals(3791, ycoords.get(1));

    }

}
