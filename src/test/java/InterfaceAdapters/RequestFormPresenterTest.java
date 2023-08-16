package InterfaceAdapters;

import static org.mockito.Mockito.*;

import UseCases.NotificationBuilder;
import UseCases.UserFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
/**
 * Unit test for RequestFormPresenter  class.
 */
public class RequestFormPresenterTest {

    private RequestFormPresenter requestFormPresenter;
    private GUIElement submitButton;
    private GUIElement cancelButton;
    private GUIElement reasonField;
    private UserFileReader userFileReader;
    private NotificationBuilder notificationBuilder;

    @BeforeEach
    public void setUp() {
        submitButton = mock(GUIElement.class);
        cancelButton = mock(GUIElement.class);
        reasonField = mock(GUIElement.class);
        userFileReader = mock(UserFileReader.class);
        notificationBuilder = mock(NotificationBuilder.class);
        int shift = 1;
        int employee = 4;
        requestFormPresenter = new RequestFormPresenter(submitButton, cancelButton,
                shift, reasonField, employee);
        requestFormPresenter.reader = userFileReader;
        requestFormPresenter.nb = notificationBuilder;
    }

    @Test
    public void testSubmitButtonActionPerformed() {
        when(userFileReader.getHRId()).thenReturn(7);
        when(reasonField.getContent()).thenReturn("Reason");

        ActionEvent submitEvent = new ActionEvent(submitButton,
                ActionEvent.ACTION_PERFORMED, null);
        requestFormPresenter.actionPerformed(submitEvent);
        // Verify that the createRequest method was called with the expected arguments
        verify(notificationBuilder).createRequest(eq(1), eq("Reason"),
                eq(4), eq(7));
        // Verify that nextPage() method was called
        verify(submitButton).nextPage();
        // Verify that nextPage() method was not called
        verify(cancelButton, never()).nextPage();
    }

    @Test
    public void testCancelButtonActionPerformed() {
        ActionEvent cancelEvent = new ActionEvent(cancelButton,
                ActionEvent.ACTION_PERFORMED, null);
        requestFormPresenter.actionPerformed(cancelEvent);

        // Verify that nextPage() method was called on the cancelButton GUIElement
        verify(cancelButton).nextPage();
        // Verify that nextPage() method was not called on the submitButton GUIElement
        verify(submitButton, never()).nextPage();
    }
}
