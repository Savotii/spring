
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


    }
}
