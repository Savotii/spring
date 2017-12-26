
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.dao.DAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserServiceImpl;
import com.google.gson.Gson;
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

    @Test
    public void TestServices() {

        UserService userServiceImpl = mock(UserServiceImpl.class);
        ProductService productServiceImpl = mock(ProductServiceImpl.class);


        Product product = new Product("title", "description", 10D, 1);
        when(productServiceImpl.create(product) != null).thenReturn();

        assertEquals(productServiceImpl.create(product), productServiceImpl.getById(product.getId()));

    }


}
