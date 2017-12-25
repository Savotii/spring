import jdbc.MySqlHelper;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        //

        MySqlHelper sqlHelper = new MySqlHelper();
        Connection connection = sqlHelper.createConnection();



    }
}