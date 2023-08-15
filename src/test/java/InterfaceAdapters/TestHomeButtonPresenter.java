package InterfaceAdapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;

public class TestHomeButtonPresenter {

    private Page mockGui;
    private GUIElement mockButton;
    private HomeButtonPresenter presenter;

    @BeforeEach
    public void setUp() {
        mockGui = mock(Page.class);
        mockButton = mock(GUIElement.class);
        presenter = new HomeButtonPresenter(mockGui, mockButton);
    }

    @Test
    public void actionPerformed() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        when(mockEvent.getSource()).thenReturn(mockButton);

        presenter.actionPerformed(mockEvent);

        verify(mockButton).nextPage();
        verify(mockGui).dispose();
    }
}
