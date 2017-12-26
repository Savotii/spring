
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MyTest {

    @Mock
    Product product;

    @InjectMocks
    ProductDAOImpl productDAO;

    @Test
    public void TestServices() {

        Product product = mock(Product.class);

        productDAO = mock(ProductDAOImpl.class);

        when(productDAO.createdToMock(product)).thenReturn(product);

        Product p = (Product)productDAO.createdToMock(product);

        assertEquals(p, product);

    }


}