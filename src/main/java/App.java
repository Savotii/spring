import com.andersen.spring.controllers.AccountService;
import com.andersen.spring.dao.UserAccountDAO;
import com.andersen.spring.dao.UserDAO;
import com.andersen.spring.entity.User;
import com.andersen.spring.entity.UserAccount;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        UserAccountDAO uad = (UserAccountDAO) ctx.getBean("UserAccountDAOImpl");

        User user = new User();
        user.setId(1);
        user.setEmail("sasdf@mail.ru");
        user.setName("Yurij");
        user.setPhoneNumber("80666666");

        UserAccount ua = new UserAccount();
        ua.setId(1);
        ua.setAmount(500d);
        ua.setId(1);
        ua.setAccountsNumber(1232233343);
        ua.setUser(user);

        UserAccount account = uad.create(ua);

        UserAccount userAccount = uad.getById(1);

        List<UserAccount> accounts = uad.getAccounts(1);
    }
}
