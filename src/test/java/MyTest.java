
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.dao.DAO;
import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import com.andersen.spring.impl.ProductDAOImpl;
import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserDAOImpl;
import com.andersen.spring.impl.UserServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class MyTest {

    @Mock
    Product product;

    @Mock
    User user;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @InjectMocks
    ProductDAOImpl productDAO;

    @InjectMocks
    UserDAOImpl userDAO;

    @Test
    public void TestServices() {

        UserService userServiceImpl = mock(UserServiceImpl.class);
        ProductService productServiceImpl = mock(ProductServiceImpl.class);

        productDAO = mock(ProductDAOImpl.class);
        when( productServiceImpl.create(product)).thenReturn(product);

        userDAO = mock(UserDAOImpl.class);
        when(userServiceImpl.create(user)).thenReturn(user);
    }


}
