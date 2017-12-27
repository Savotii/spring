import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductDAO productDAO;

    @Mock
    Product product;

    @Test
    public void testService()
    {
        when(productService.create(product)).thenReturn(new Product("title", "description", 10.5D, 1));
        assertEquals(new Product("title", "description", 10.5D, 2), productService.create(product));
    }

}
