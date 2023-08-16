package InterfaceAdapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
/**
 * Unit test for MakeShiftFormPresenter class.
 */
public class MakeShiftFormPresenterTest {

    private GUIElement mockTimeField;
    private GUIElement mockDurationField;
    private GUIElement mockSubmitButton;
    private GUIElement mockCancelButton;
    private Page mockGUI;
    private Page mockForm;
    private MakeShiftFormPresenter presenter;

    @BeforeEach
    public void setUp() {
        mockTimeField = mock(GUIElement.class);
        mockDurationField = mock(GUIElement.class);
        mockSubmitButton = mock(GUIElement.class);
        mockCancelButton = mock(GUIElement.class);
        mockGUI = mock(Page.class);
        mockForm = mock(Page.class);

        presenter = new MakeShiftFormPresenter(mockTimeField, mockDurationField,
                mockSubmitButton, mockCancelButton, LocalDate.now(), mockGUI, mockForm);
    }

    @Test
    public void actionPerformedValidInput() {
        when(mockSubmitButton.getContent()).thenReturn("Submit");
        when(mockTimeField.getContent()).thenReturn("12:00");
        when(mockDurationField.getContent()).thenReturn("4.5");

        presenter.actionPerformed(new ActionEvent(mockSubmitButton,
                ActionEvent.ACTION_PERFORMED, null));

        verify(mockSubmitButton).nextPage();
        verify(mockGUI).update();
        verify(mockForm, never()).update();
    }

    @Test
    public void actionPerformedInvalidInput() {
        when(mockSubmitButton.getContent()).thenReturn("Submit");
        when(mockTimeField.getContent()).thenReturn("23:00");
        when(mockDurationField.getContent()).thenReturn("4.5");

        presenter.actionPerformed(new ActionEvent(mockSubmitButton,
                ActionEvent.ACTION_PERFORMED, null));

        verify(mockSubmitButton, never()).nextPage();
        verify(mockGUI, never()).update();
        verify(mockForm).update();
    }

    @Test
    public void actionPerformedNextPage() {
        when(mockCancelButton.getContent()).thenReturn("Cancel");

        presenter.actionPerformed(new ActionEvent(mockCancelButton, ActionEvent.ACTION_PERFORMED, null));

        verify(mockCancelButton).nextPage();
        verify(mockGUI, never()).update();
        verify(mockForm, never()).update();
    }
}
