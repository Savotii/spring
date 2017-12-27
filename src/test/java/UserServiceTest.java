
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.dao.UserDAO;
import com.andersen.spring.entity.User;
import com.andersen.spring.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDAO userDAO;

    @Mock
    User user;

    @Test
    public void testService()
    {

        when(userService.create(user)).thenReturn(new User("test", "email.test", "+380000000"));
        assertEquals(new User("test", "email.test", "+380000000"), userService.create(user));

        when(userService.update(user)).thenReturn(new User("test", "email.test", "+3811111111"));
        assertEquals(new User("test", "email.test", "+3811111111"), userService.update(user));

        when(userService.getById(1)).thenReturn(new User("test", "email.test", "+380000000"));
        assertEquals(new User("test", "email.test", "+380000000"), userService.getById(1));

        when(userService.delete(1)).thenReturn(true);
        assertEquals(true, userService.delete(1));


    }
}
