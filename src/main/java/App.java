import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.entity.Product;
import com.andersen.spring.impl.ProductServiceImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        ProductServiceImpl productService =  (ProductServiceImpl)ctx.getBean("ProductServiceImpl");

        Product newProduct = productService.getProduct();
        Product new2 = productService.getProduct();
        System.out.println(newProduct);
        System.out.println(new2);
        System.out.println(newProduct.equals(new2));
        System.out.println(newProduct == new2);
        Product p = (Product)ctx.getBean("Product");

        System.out.println(newProduct.equals(new2) && newProduct.equals(p));
        System.out.println(newProduct == new2 && newProduct == p);
    }
}
