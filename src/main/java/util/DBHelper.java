package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;

public class DBHelper {
    private static final Logger LOGGER = Logger.getLogger(DBHelper.class.getName());
    private static String driverJdbc = "com.mysql.jdbc.Driver";
    private static String urlJdbc = "jdbc:mysql://localhost:3306/db_example?";
    private static String userName = "root";
    private static String userPassword = "Java_mentor022";
    private static DBHelper dbHelper;
    private static SessionFactory sessionFactory;

    private DBHelper() {
    }

    public static DBHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", driverJdbc);
        configuration.setProperty("hibernate.connection.url", urlJdbc + "serverTimezone=UTC");
        LOGGER.log(Level.WARNING, "This is LOGGER. The {0} is successfully eptas", urlJdbc);
        configuration.setProperty("hibernate.connection.username", userName);
        configuration.setProperty("hibernate.connection.password", userPassword);
        LOGGER.log(Level.WARNING, "Everything is OK. Username ={0} and Password = {1} are true", new Object[]{userName, userPassword});
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    // For JDBC/////////////////////////////////////
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(driverJdbc).newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append(urlJdbc).
                    append("user=").append(userName).
                    append("&").append("password=").
                    append(userPassword);
            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}





