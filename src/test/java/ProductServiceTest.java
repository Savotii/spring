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
        assertEquals(new Product("title", "description", 10.5D, 1), productService.create(product));

        when(productService.update(product)).thenReturn(new Product("title", "description", 10.6D, 1));
        assertEquals(new Product("title", "description", 10.6D, 1), productService.update(product));

        when(productService.getById(1)).thenReturn(new Product("title", "description", 10.6D, 1));
        assertEquals(new Product("title", "description", 10.6D, 1), productService.getById(1));

        when(productService.delete(1)).thenReturn(true);
        assertEquals(true, productService.delete(1));
    }

}
