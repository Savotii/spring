
import com.andersen.spring.entity.Product;
import com.andersen.spring.impl.ProductDAOImpl;
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
    ProductDAOImpl productDAO;

    @Mock
    Product product;

    @Test
    public void testService()
    {

        when(productDAO.create(product)).thenReturn(new Product("title", "description", 10, 1));
        Product result = productService.create(product);

        when(product.getTitle()).thenReturn("title");
        when(product.getDescription()).thenReturn("description");
        when(product.getPrice()).thenReturn(10d);
        when(product.getUserId()).thenReturn(1l);
        when(product.getId()).thenReturn(1l);

        assertEquals(result.getTitle(), product.getTitle());
        assertEquals(result.getDescription(), product.getDescription());
        assertEquals(result.getPrice(), product.getPrice() , 0);
        assertEquals(result.getUserId() , product.getUserId());

    }

}
