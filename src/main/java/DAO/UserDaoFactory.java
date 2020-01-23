package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoFactory {
    private static final Logger LOGGER = Logger.getLogger(UserDaoFactory.class.getName());//получаем логгер

    public static UserDAO getUserTypeDAO() {
        UserDAO userDAO = null;
        String daoTypeProperty;
        try (InputStream input = UserDaoFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                LOGGER.log(Level.WARNING, "file \"config.properties\" is not found");
                throw new IOException();
            }
            prop.load(input);
            daoTypeProperty = prop.getProperty("DAOType");
            if (daoTypeProperty == null) {
                LOGGER.log(Level.WARNING, "Sorry, unable to find some type of UserDAO.");
                throw new IOException();
            }
            switch (daoTypeProperty) {
                case ("UserHibernateDAO"):
                    userDAO = new UserHibernateDAO();
                    break;
                case ("UserJdbcDAO"):
                    userDAO = new UserJdbcDAO();
                    break;
                default:
                    throw new IOException();//переделать чтобы exception был не по умолчанию, по умолчанию должно что-то отрабатывать
            }
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Sorry, type of UserDAO is not correct.");
        }
        return userDAO;
    }
}