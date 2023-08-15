package InterfaceAdapters;

import UseCases.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

class TestMakeShiftFormPresenter {

    private GUIElement mockTimeField;
    private GUIElement mockDurationField;
    private GUIElement mockSubmitButton;
    private GUIElement mockCancelButton;
    private Page mockGUI;
    private Page mockForm;
    private MakeShiftFormPresenter presenter;

    @BeforeEach
    void setUp() {
        mockTimeField = mock(GUIElement.class);
        mockDurationField = mock(GUIElement.class);
        mockSubmitButton = mock(GUIElement.class);
        mockCancelButton = mock(GUIElement.class);
        mockGUI = mock(Page.class);
        mockForm = mock(Page.class);

        presenter = new MakeShiftFormPresenter(
                mockTimeField, mockDurationField, mockSubmitButton, mockCancelButton,
                LocalDate.now(), mockGUI, mockForm
        );
    }

    @Test
    void actionPerformedValidInput() {
        when(mockSubmitButton.getContent()).thenReturn("Submit");
        when(mockTimeField.getContent()).thenReturn("12:00");
        when(mockDurationField.getContent()).thenReturn("4.5");

        presenter.actionPerformed(new ActionEvent(mockSubmitButton, ActionEvent.ACTION_PERFORMED, null));

        verify(mockSubmitButton).nextPage();
        verify(mockGUI).update();
        verify(mockForm, never()).update();
    }

    @Test
    void actionPerformedInvalidInput() {
        when(mockSubmitButton.getContent()).thenReturn("Submit");
        when(mockTimeField.getContent()).thenReturn("23:00");
        when(mockDurationField.getContent()).thenReturn("4.5");

        presenter.actionPerformed(new ActionEvent(mockSubmitButton, ActionEvent.ACTION_PERFORMED, null));

        verify(mockSubmitButton, never()).nextPage();
        verify(mockGUI, never()).update();
        verify(mockForm).update();
    }

    @Test
    void actionPerformedNextPage() {
        when(mockCancelButton.getContent()).thenReturn("Cancel");

        presenter.actionPerformed(new ActionEvent(mockCancelButton, ActionEvent.ACTION_PERFORMED, null));

        verify(mockCancelButton).nextPage();
        verify(mockGUI, never()).update();
        verify(mockForm, never()).update();
    }
}
