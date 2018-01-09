
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import com.andersen.spring.impl.product.ProductDAOImpl;
import com.andersen.spring.impl.product.ProductServiceImpl;
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

        User  user = new User("Test", "kkk@mail.ru", "0009393");
        when(productDAO.create(product)).thenReturn(new Product("title", "description", 10, user));
        Product result = productService.create(product);

        when(product.getTitle()).thenReturn("title");
        when(product.getDescription()).thenReturn("description");
        when(product.getPrice()).thenReturn(10d);
        when(product.getUser()).thenReturn(user);
        when(product.getId()).thenReturn(1l);

        assertEquals(result.getTitle(), product.getTitle());
        assertEquals(result.getDescription(), product.getDescription());
        assertEquals(result.getPrice(), product.getPrice() , 0);
        assertEquals(result.getUser().getId() , product.getUser().getId());

    }

    @Test
    public void testServiceGetAll()
    {

        User  user = new User("Test", "kkk@mail.ru", "0009393");
        List<Product> productList = new LinkedList<>();
        productList.add(new Product("titl","desription1", 10, user));
        productList.add(new Product("title","desription2", 10, user));
        productList.add(new Product("titles","desription3", 10, user));
        productList.add(new Product("titles1","desription3", 10, user));
        productList.add(new Product("ti","desription4", 10, user));

        when(productDAO.getAll()).thenReturn(productList);

        List<Product> resultList = productService.getProductsByTitle("title");

        assertEquals(resultList.get(0).getTitle(), "title");
        assertEquals(resultList.get(1).getTitle(), "titles");
        assertEquals(resultList.get(2).getTitle(), "titles1");
    }

}
