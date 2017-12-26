import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.entity.Product;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        ProductService productService =  (ProductService)ctx.getBean("ProductServiceImpl");

        Product p = (Product)ctx.getBean("Product");
        p.setTitle("Тест продукт");
        p.setDescription("Описание продукта тратататататата");
        p.setPrice(10);
        p.setUserId(1);

        Product newProduct = productService.create(p);

        productService.delete(5);
    }
}
