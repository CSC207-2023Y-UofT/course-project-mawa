package InterfaceAdapters;

import UseCases.ShiftFileReader;
import UseCases.UserFileReader;
import UseCases.UserTypeConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestShiftCellPresenter {

    private GUIElement mockButton;
    private ShiftCellPresenter presenter;
    private UserFileReader mockUserFileReader;
    private ShiftFileReader mockShiftFileReader;

    @BeforeEach
    public void setUp() {
        mockButton = mock(GUIElement.class);
        presenter = new ShiftCellPresenter(mockButton, 1, 2);
        mockUserFileReader = mock(UserFileReader.class);
        mockShiftFileReader = mock(ShiftFileReader.class);
        presenter.userReader = mockUserFileReader;
        presenter.shiftReader = mockShiftFileReader;
    }

    @Test
    public void isHRUserIsHR() {
        when(mockUserFileReader.getType(2)).thenReturn(UserTypeConstants.HR);

        boolean result = presenter.isHR();

        assertTrue(result);
        verify(mockUserFileReader).getType(2);
    }

    @Test
    public void isHRUserIsNotHR() {
        when(mockUserFileReader.getType(2)).thenReturn(UserTypeConstants.WAGE_WORKER);

        boolean result = presenter.isHR();

        assertFalse(result);
        verify(mockUserFileReader).getType(2);
    }

    @Test
    public void testGetString() {
        LocalDateTime shiftTime = LocalDateTime.of(2023, 8, 11, 12, 30);
        when(mockShiftFileReader.getDate(1)).thenReturn(shiftTime);

        String result = presenter.getString();

        assertEquals("12:30", result);
        verify(mockShiftFileReader).getDate(1);
    }

    @Test
    public void testActionPerformed() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        when(mockEvent.getSource()).thenReturn(mockButton);

        presenter.actionPerformed(mockEvent);

        verify(mockButton).nextPage();
    }
}
