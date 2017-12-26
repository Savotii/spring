
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.dao.DAO;
import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.impl.ProductDAOImpl;
import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MyTest {

    @Mock
    ProductService productServiceImpl;

    @Mock
    UserService userServiceImpl;

    @Mock
    Product product;

    @Mock
    DAO productDAO;

    @Test
    public void TestServices() {

        UserService userServiceImpl = mock(UserServiceImpl.class);
        ProductService productServiceImpl = mock(ProductServiceImpl.class);

        Product product = mock(Product.class);

        productDAO = mock(ProductDAOImpl.class);

        Product pr = new Product("title", "description", 10D, 1);
        productDAO.created(pr);
        when( productDAO.created(pr)).thenReturn(product);

        assertEquals(productServiceImpl.create(product), product);

    }


}
