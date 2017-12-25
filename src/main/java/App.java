import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserServiceImpl;
import com.andersen.spring.storage.MarketStorage;
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

        ProductServiceImpl productServiceImpl = (ProductServiceImpl)ctx.getBean("ProductServiceImpl");
        UserServiceImpl userServiceImpl = (UserServiceImpl)ctx.getBean("UserServiceImpl");
        MarketStorage marketStorage = (MarketStorage)ctx.getBean("MarketStorage");


    }
}