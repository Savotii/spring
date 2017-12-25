package com.andersen.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlHelper {

    private static String url = "jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String login = "root";
    private static String password = "1111";

    private static String driverClassName = "com.mysql.jdbc.Driver;";

    private static Connection connection = null;

    public static Connection createConnection() {
        //"jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void setUrl(String url) {
        MySqlHelper.url = url;
    }

    public static void setLogin(String login) {
        MySqlHelper.login = login;
    }

    public static void setPassword(String password) {
        MySqlHelper.password = password;
    }

    public static void setDriverClassName(String driverClassName) {
        MySqlHelper.driverClassName = driverClassName;
    }


}
