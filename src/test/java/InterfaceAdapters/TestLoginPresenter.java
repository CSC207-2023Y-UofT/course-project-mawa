package InterfaceAdapters;

import Entities.User;
import UseCases.EmptyAppValidator;
import UseCases.LoginValidator;
import UseCases.UserFileReader;
import UseCases.UserInteractor;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestLoginPresenter {

    private Page mockGui;
    private LoginPresenter presenter;
    private User user = Instancio.create(User.class);

    @BeforeEach
    public void setUp() throws IOException {
        mockGui = mock(Page.class);
        presenter = new LoginPresenter(mockGui);
        new FileWriter("testUsers.ser", false).close();
        UserInteractor interactor = new UserInteractor("test");
        interactor.writeData(user);
        presenter.loginValidator.empDB = new UserFileReader("test");
    }

    @Test
    public void handleUserValidCredentials(){
        int result = presenter.handleUser(user.getUserNum(), user.getPassword());
        assertEquals(user.getUserNum(), result);
    }

    @Test
    public void handleUserInvalidCredentials() throws IOException {
        new FileWriter("testUsers.ser", false).close();
        int userId = 123;
        char[] password = "password".toCharArray();

        int result = presenter.handleUser(userId, password);

        assertEquals(-1, result);
    }

    @Test
    public void startWithHREmpty() throws IOException {
        new FileWriter("testUsers.ser", false).close();
        EmptyAppValidator mockEmptyAppValidator = mock(EmptyAppValidator.class);
        mockEmptyAppValidator.ui = new UserInteractor("test");
        when(mockEmptyAppValidator.isEmpty()).thenReturn(true);
        presenter.eav = mockEmptyAppValidator;
        boolean result = presenter.startWithHR();

        assertTrue(result);
    }

    @Test
    public void startWithHRNonEmpty(){
        User user = Instancio.create(User.class);
        UserInteractor interactor = new UserInteractor("test");
        interactor.writeData(user);
        EmptyAppValidator mockEmptyAppValidator = mock(EmptyAppValidator.class);
        mockEmptyAppValidator.ui = new UserInteractor("test");
        when(mockEmptyAppValidator.isEmpty()).thenReturn(false);

        boolean result = presenter.startWithHR();

        assertFalse(result);
    }
}
