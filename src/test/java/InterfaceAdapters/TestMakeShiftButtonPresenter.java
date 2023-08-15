package InterfaceAdapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;
/**
 * Unit test for MakeShiftButtonPresenter class.
 */
class TestMakeShiftButtonPresenter {
    private GUIElement mockButton;
    private MakeShiftButtonPresenter presenter;
    @BeforeEach
    void setUp() {
        mockButton = mock(GUIElement.class);
        presenter = new MakeShiftButtonPresenter(mockButton);
    }

    @Test
    void actionPerformedMakeShiftButton() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        when(mockEvent.getSource()).thenReturn(mockButton);
        presenter.actionPerformed(mockEvent);

        verify(mockButton).nextPage();
    }

    @Test
    void actionPerformedOtherButtonClicked() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        when(mockEvent.getSource()).thenReturn(mock(GUIElement.class));
        presenter.actionPerformed(mockEvent);

        verify(mockButton, never()).nextPage();
    }
}
