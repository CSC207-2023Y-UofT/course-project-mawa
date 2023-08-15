package InterfaceAdapters;

import UseCases.ShiftFileReader;
import UseCases.UserFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Unit test for ShiftPresenter class.
 */
public class TestShiftPresenter {

    private ShiftFileReader mockShiftFileReader;
    private UserFileReader mockUserFileReader;
    private GUIElement mockCloseButton;
    private ShiftPresenter presenter;

    @BeforeEach
    public void setUp() {
        mockShiftFileReader = mock(ShiftFileReader.class);
        mockUserFileReader = mock(UserFileReader.class);
        mockCloseButton = mock(GUIElement.class);
        presenter = new ShiftPresenter(1, mockCloseButton, 2);
        presenter.shiftDB = mockShiftFileReader;
        presenter.userDB = mockUserFileReader;
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
    public void getCoworkerStringMultipleCoworkers() {
        ArrayList<Integer> coworkers = new ArrayList<>();
        coworkers.add(3);
        coworkers.add(4);
        when(mockShiftFileReader.getCoworkers(1)).thenReturn(coworkers);
        when(mockUserFileReader.getFirstName(3)).thenReturn("aaa");
        when(mockUserFileReader.getSurname(3)).thenReturn("bbb");
        when(mockUserFileReader.getFirstName(4)).thenReturn("ccc");
        when(mockUserFileReader.getSurname(4)).thenReturn("ddd");

        String result = presenter.getCoworkerString();

        assertEquals("aaa bbb, ccc ddd", result);
        verify(mockShiftFileReader).getCoworkers(1);
        verify(mockUserFileReader).getFirstName(3);
        verify(mockUserFileReader).getSurname(3);
        verify(mockUserFileReader).getFirstName(4);
        verify(mockUserFileReader).getSurname(4);
    }

    @Test
    public void getCoworkerStringSingleCoworker() {
        ArrayList<Integer> coworkers = new ArrayList<>();
        coworkers.add(3);
        when(mockShiftFileReader.getCoworkers(1)).thenReturn(coworkers);
        when(mockUserFileReader.getFirstName(3)).thenReturn("aaa");
        when(mockUserFileReader.getSurname(3)).thenReturn("bbb");

        String result = presenter.getCoworkerString();

        assertEquals("No coworkers", result);
        verify(mockShiftFileReader).getCoworkers(1);
        verify(mockUserFileReader).getFirstName(3);
        verify(mockUserFileReader).getSurname(3);
    }

    @Test
    public void getCoworkerStringNoCoworkers() {
        ArrayList<Integer> coworkers = new ArrayList<>();
        when(mockShiftFileReader.getCoworkers(1)).thenReturn(coworkers);

        String result= presenter.getCoworkerString();

        assertEquals("No coworkers", result);
        verify(mockShiftFileReader).getCoworkers(1);
        verify(mockUserFileReader, never()).getFirstName(anyInt());
        verify(mockUserFileReader, never()).getSurname(anyInt());
    }

    @Test
    void actionPerformed() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        when(mockEvent.getSource()).thenReturn(mockCloseButton);

        presenter.actionPerformed(mockEvent);

        verify(mockCloseButton).nextPage();
    }
}






