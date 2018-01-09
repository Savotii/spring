import com.andersen.spring.dao.BasketDAO;
import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.dao.UserAccountDAO;
import com.andersen.spring.dao.UserDAO;
import com.andersen.spring.entity.Basket;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.exceptions.InsufficientFunds;
import com.andersen.spring.facade.MarketFacade;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        UserAccountDAO uad = (UserAccountDAO) ctx.getBean("userAccountDAOImpl");

        ProductDAO pdi = (ProductDAO)ctx.getBean("productDAOImpl");

        UserDAO us = (UserDAO) ctx.getBean("userDAOImpl");

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

        for (Product product: pdi.getProductsByTitle("еп")) {
            System.out.println(product);
        }

        Product product = pdi.create(new Product("Котлетка", "Полуфабрикат оч вкусной котлетки. затащит полюбому.", 10d, user));
        System.out.println("create: " + product);

        product = pdi.getById(product.getId());
        System.out.println("getId: " + product);

        product.setPrice(45);
        product = pdi.update(product);
        System.out.println("update: " + product);

        for (Product p: pdi.getProductsByUserId(1)) {
            System.out.println("ByUserId: " + p);
        }

      /*  boolean result = pdi.delete(product.getId());
        System.out.println("delete: " + result);*/

        User user1 = new User();
        user1.setPhoneNumber("3333443");
        user1.setEmail("sdfsf@mail.ru");
        user1.setName("Yurij");

        User user2 = us.create(user1);
        System.out.println("create user: " + user2);

        user2 = us.getById(user2.getId());
        System.out.println("getByID : " + user2);

        user2.setEmail("eeee@yandex.ru");
        user2 = us.update(user2);
        System.out.println("update: " + user2);


        MarketFacade marketFacade = (MarketFacade)ctx.getBean("marketFacadeImpl");

        User seller = product.getUser();

        List<UserAccount> buyerAccountList = uad.getAccountsByUserId(3);
        List<UserAccount> sellerAccountList = uad.getAccountsByUserId(seller.getId());

        UserAccount buyerAccount = buyerAccountList.get(0);
        UserAccount sellerAccount = sellerAccountList.get(0);

        System.out.println("Количество денег у покупателя: " + buyerAccount.getAmount());
        System.out.println("Количество денег у продавца: " + sellerAccount.getAmount());
        marketFacade.buyProduct(user, product, buyerAccount, sellerAccount);
        System.out.println("Количество денег у покупателя после транзации: " + uad.getById(buyerAccount.getId()).getAmount());
        System.out.println("Количество денег у продавца после транзации: " + uad.getById(sellerAccount.getId()).getAmount());
    }
}
