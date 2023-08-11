import Entities.Shift;
import InterfaceAdapters.DayCellPresenter;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDateTime;

import static org.instancio.Select.field;

public class TestDayCellPresenter {
    private DayCellPresenter dcp;
    /*@BeforeAll
    public void setUp(){
        dcp =  Instancio.of(DayCellPresenter.class)
                .set(field(Shift::getTime), LocalDateTime.of())
                .create();

    }*/
}
