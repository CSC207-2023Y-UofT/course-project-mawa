package InterfaceAdapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.ActionEvent;
import java.time.Month;

import static org.mockito.Mockito.*;


public class TestCalendarPresenter {

    @Mock
    private Page mockGui;
    @Mock
    private CalendarModel mockModel;
    @Mock
    private GUIElement mockYearSelector;
    @Mock
    private GUIElement mockMonthSelector;
    private CalendarPresenter calendarPresenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        calendarPresenter = new CalendarPresenter(mockGui, mockModel, mockYearSelector, mockMonthSelector);
    }

    @Test
    public void testActionPerformedYearSelector() {
        when(mockYearSelector.getContent()).thenReturn("2023");

        ActionEvent mockActionEvent = mock(ActionEvent.class);
        when(mockActionEvent.getSource()).thenReturn(mockYearSelector);

        calendarPresenter.actionPerformed(mockActionEvent);

        verify(mockModel).setYear(ArgumentMatchers.eq(2023));
        verify(mockGui).update();
    }

    @Test
    public void testActionPerformedMonthSelector() {
        when(mockMonthSelector.getContent()).thenReturn("AUGUST");
        ActionEvent mockActionEvent = mock(ActionEvent.class);
        when(mockActionEvent.getSource()).thenReturn(mockMonthSelector);

        calendarPresenter.actionPerformed(mockActionEvent);

        verify(mockModel).setMonth(ArgumentMatchers.eq(Month.AUGUST.getValue()));
        verify(mockGui).update();
    }
}
