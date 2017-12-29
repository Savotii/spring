package com.andersen.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlHelper {

    private String url = "jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf-8";
    private String login = "root";
    private String password = "1111";

    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    private Connection connection = null;

    public Connection createConnection() {
        //"jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void init()
    {
        createConnection();

        try {
            // Создаем таблицы
            Statement statement = connection.createStatement();

            statement.addBatch("CREATE TABLE IF NOT EXISTS PRODUCTS (id INTEGER auto_increment PRIMARY KEY , title VARCHAR(50), description VARCHAR(200), price DOUBLE, userId INTEGER )");
            statement.addBatch("CREATE TABLE IF NOT EXISTS  USERS (id INTEGER auto_increment PRIMARY  KEY , name VARCHAR (50), email VARCHAR (50), phoneNumber VARCHAR(100))");

            // Добавляем таблицу аккаунт
            statement.addBatch("CREATE TABLE IF NOT EXISTS ACCOUNTS (id INTEGER  auto_increment PRIMARY  KEY, accountsNumber INTEGER NOT NULL, amount DOUBLE, ownerId INTEGER NOT NULL)");
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        closeConnection();
    }
}
