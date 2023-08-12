package InterfaceAdapters;

import Entities.Shift;
import InterfaceAdapters.DayCellPresenter;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.instancio.Select.field;

public class TestDayCellPresenter {
    private DayCellPresenter dcp;

    @BeforeEach
    public void setUp(){
        dcp =  Instancio.of(DayCellPresenter.class)
                .set(field(Shift::getTime), LocalDateTime.now())
                .create();

    }
}
