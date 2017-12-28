
import com.andersen.spring.entity.Product;
import com.andersen.spring.impl.ProductDAOImpl;
import com.andersen.spring.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

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
    public void testServiceCreate()
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

    @Test
    public void testServiceGetAll()
    {

        List<Product> productList = new LinkedList<>();
        productList.add(new Product("titl","desription1", 10, 1));
        productList.add(new Product("title","desription2", 10, 1));
        productList.add(new Product("titles","desription3", 10, 1));
        productList.add(new Product("titles1","desription3", 10, 1));
        productList.add(new Product("ti","desription4", 10, 1));

        when(productDAO.getProductsByTitle("title")).thenReturn(productList);
        when(productDAO.getAll()).thenReturn(productList);

        List<Product> resultList = productService.getProductsByTitle("title");

        assertEquals(resultList.get(0).getTitle(), "title");
        assertEquals(resultList.get(1).getTitle(), "titles");
        assertEquals(resultList.get(2).getTitle(), "titles1");
    }

}
