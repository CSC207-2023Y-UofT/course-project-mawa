package InterfaceAdapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;
/**
 * Unit test for HomeButtonPresenter class.
 */
public class TestHomeButtonPresenter {

    private Page mockGUI;
    private GUIElement mockButton;
    private HomeButtonPresenter presenter;

    @BeforeEach
    public void setUp() {
        mockGUI = mock(Page.class);
        mockButton = mock(GUIElement.class);
        presenter = new HomeButtonPresenter(mockGUI, mockButton);
    }

    @Test
    public void actionPerformed() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        when(mockEvent.getSource()).thenReturn(mockButton);

        presenter.actionPerformed(mockEvent);

        verify(mockButton).nextPage();
        verify(mockGUI).dispose();
    }
}
