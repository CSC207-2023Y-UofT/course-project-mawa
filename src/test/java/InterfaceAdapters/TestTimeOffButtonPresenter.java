package InterfaceAdapters;

import UseCases.NotificationFileReader;
import UseCases.ShiftFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestTimeOffButtonPresenter {

    private GUIElement mockButton;
    private NotificationFileReader mockNotificationFileReader;
    private ShiftFileReader mockShiftFileReader;
    private TimeOffButtonPresenter presenter;

    @BeforeEach
    public void setUp() {
        mockButton = mock(GUIElement.class);
        mockNotificationFileReader = mock(NotificationFileReader.class);
        mockShiftFileReader = mock(ShiftFileReader.class);
        presenter = new TimeOffButtonPresenter(mockButton, 1);
        presenter.notifReader = mockNotificationFileReader;
        presenter.shiftReader = mockShiftFileReader;
    }

    @Test
    public void testGetDate() {
        LocalDateTime shiftDate = LocalDateTime.of(2023, 8, 11, 12, 30);
        when(mockShiftFileReader.getDate(1)).thenReturn(shiftDate);

        LocalDateTime result = presenter.getDate();

        assertEquals(shiftDate, result);
        verify(mockShiftFileReader).getDate(1);
    }

    @Test
    public void testGetDuration() {
        float shiftDuration = 8.5f;
        when(mockShiftFileReader.getDuration(1)).thenReturn(shiftDuration);

        float result = presenter.getDuration();

        assertEquals(shiftDuration, result);
        verify(mockShiftFileReader).getDuration(1);
    }

    @Test
    public void actionPerformedNoNotif() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        ArrayList<Integer> existingNotif = new ArrayList<>();
        when(mockNotificationFileReader.getIds(1)).thenReturn(existingNotif);
        when(mockEvent.getSource()).thenReturn(mockButton);

        presenter.actionPerformed(mockEvent);

        verify(mockNotificationFileReader).getIds(1);
        verify(mockButton).nextPage();
    }

    @Test
    public void actionPerformedExistingNotif() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        ArrayList<Integer> existingNotif = new ArrayList<>();
        existingNotif.add(1);
        existingNotif.add(2);
        when(mockNotificationFileReader.getIds(1)).thenReturn(existingNotif);
        when(mockEvent.getSource()).thenReturn(mockButton);

        presenter.actionPerformed(mockEvent);

        verify(mockNotificationFileReader).getIds(1);
        verify(mockButton).getContent();
    }
}
