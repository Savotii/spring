import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.entity.Product;
import com.andersen.spring.facade.MarketStorage;
import com.andersen.spring.jdbc.MySqlHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        //

        /*MySqlHelper sqlHelper = new MySqlHelper();
        Connection connection = sqlHelper.createConnection();*/

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        Connection connection = (Connection)ctx.getBean("MySqlConnection");

        ProductService productService = (ProductService)ctx.getBean("ProductService");
        UserService userService = (UserService)ctx.getBean("UserService");

        MarketStorage marketStorage = (MarketStorage)ctx.getBean("MarketStorage");



    }
}