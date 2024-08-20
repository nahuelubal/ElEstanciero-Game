package ar.edu.utn.frc.tup.lciii.model.auth;

import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;
import ar.edu.utn.frc.tup.lciii.model.session.Session;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.repository.user.UserRepository;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private Auth auth;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserName("lucas");
        user.setPassword("123456");
        userEntity = new UserEntity();
        userEntity.setUserName("joaquin");
        userEntity.setPassword("123456");
    }
    @Test
    public void testConstructor() {
        UserRepository userRepository = mock(UserRepository.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        Auth auth = new Auth(userRepository, modelMapper);

        assertNotNull(auth);
        assertEquals(userRepository, auth.userRepository);
        assertEquals(modelMapper, auth.modelMapper);
    }
    @Test
    public void testSingletonInstanceCreation() {
        Auth firstInstance = Auth.getInstance(userRepository, modelMapper);
        Auth secondInstance = Auth.getInstance(userRepository, modelMapper);

        assertNotNull(firstInstance);
        assertNotNull(secondInstance);
        assertEquals(firstInstance, secondInstance);
    }
    @Test
    void testLoginSuccess() {
        when(userRepository.findByUsername(user.getUserName())).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, User.class)).thenReturn(user);
        ResponseHandler response = auth.login(user);
        assertTrue(response.getSuccess());
        assertEquals("Successfully login!", response.getMessage());
        assertTrue(Session.getIsUserAuthenticated());
        assertEquals(user, Session.getUser());
    }

    @Test
    void testLoginWrongPassword() {
        user.setPassword("wrongPassword");
        when(userRepository.findByUsername(user.getUserName())).thenReturn(Optional.of(userEntity));
        ResponseHandler response = auth.login(user);
        assertFalse(response.getSuccess());
        assertEquals("Wrong password", response.getMessage());
    }

    @Test
    void testLoginUserNotFound() {
        when(userRepository.findByUsername(user.getUserName())).thenReturn(Optional.empty());

        ResponseHandler response = auth.login(user);

        assertFalse(response.getSuccess());
        assertEquals("User not found", response.getMessage());
    }

    @Test
    void testLogoutSuccess() {
        Session.setIsUserAuthenticated(true);
        Session.setUser(user);
        ResponseHandler response = auth.logout();
        assertTrue(response.getSuccess());
        assertEquals("Successfully logout!", response.getMessage());
        assertFalse(Session.getIsUserAuthenticated());
        assertNull(Session.getUser());
    }

    @Test
    void testLogoutNotLoggedIn() {
        Session.setIsUserAuthenticated(false);
        ResponseHandler response = auth.logout();
        assertFalse(response.getSuccess());
        assertEquals("You are not logged in", response.getMessage());
    }
    @Test
    void testRegisterUserAlreadyExists() {
        when(userRepository.findByUsername(user.getUserName())).thenReturn(Optional.of(userEntity));
        ResponseHandler response = auth.register(user);
        assertFalse(response.getSuccess());
        assertEquals("User already exists", response.getMessage());
    }

    @Test
    void testChangePasswordSuccess() {
        Session.setUser(user);
        Session.setIsUserAuthenticated(true);
        when(userRepository.findById(user.getIdUser())).thenReturn(userEntity);
        ResponseHandler response = auth.changePassword(user.getPassword(), "newPassword");
        assertTrue(response.getSuccess());
        assertEquals("Successfully password changed!", response.getMessage());
        assertEquals("newPassword", userEntity.getPassword());
    }

    @Test
    void testChangePasswordCurrentPasswordDoesNotMatch() {
        Session.setUser(user);
        Session.setIsUserAuthenticated(true);
        when(userRepository.findById(user.getIdUser())).thenReturn(userEntity);
        ResponseHandler response = auth.changePassword("contrasenia", "nuevacontra");
        assertFalse(response.getSuccess());
        assertEquals("Current password does not match", response.getMessage());
    }

    @Test
    void testLogout(){
        Session.setUser(user);
        Session.setIsUserAuthenticated(true);
        when(userRepository.findById(user.getIdUser())).thenReturn(userEntity);
        ResponseHandler response = auth.logout();
        assertEquals(response.getMessage() ,"Successfully logout!");
    }

    @Test
    void testLogoutWithError(){
        Session.setIsUserAuthenticated(false);
        ResponseHandler response = auth.logout();
        assertEquals(response.getMessage() , "You are not logged in");
    }
}