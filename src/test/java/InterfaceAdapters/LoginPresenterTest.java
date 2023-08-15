package InterfaceAdapters;

import UseCases.EmptyAppValidator;
import UseCases.LoginValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Unit test for LoginPresenter class.
 */
public class LoginPresenterTest {
    @Mock
    private Page mockGui;
    @Mock
    private LoginValidator validator;
    private LoginPresenter presenter;
    private int user = 444;
    private char[] pwd = "password".toCharArray();

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        mockGui = mock(Page.class);
        validator = mock(LoginValidator.class);
        presenter = new LoginPresenter(mockGui);
        presenter.loginValidator = validator;
    }

    @Test
    public void handleUserValidCredentials(){
        when(validator.validateCredentials(user, pwd)).thenReturn(user);
        int result = presenter.handleUser(user, pwd);
        assertEquals(user, result);
    }

    @Test
    public void handleUserInvalidCredentials() throws IOException {
        char[] pwd2 = "incorrect".toCharArray();
        when(validator.validateCredentials(user, pwd2)).thenReturn(-1);
        int result = presenter.handleUser(user, pwd2);
        assertEquals(-1, result);
    }

    @Test
    public void startWithHREmpty() throws IOException {
        EmptyAppValidator mockEmptyAppValidator = mock(EmptyAppValidator.class);
        presenter.eav = mockEmptyAppValidator;
        when(mockEmptyAppValidator.isEmpty()).thenReturn(true);
        boolean result = presenter.startWithHR();

        assertTrue(result, "Empty user file should start with HR making");
    }

    @Test
    public void startWithHRNonEmpty(){
        EmptyAppValidator mockEmptyAppValidator = mock(EmptyAppValidator.class);
        presenter.eav = mockEmptyAppValidator;
        when(mockEmptyAppValidator.isEmpty()).thenReturn(false);
        boolean result = presenter.startWithHR();
        assertFalse(result, "non empty user file should not start with HR making");
    }
}
