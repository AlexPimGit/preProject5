package service;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBConfigHibernate;
import util.DBConfigJDBC;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userServiceImpl;
    private SessionFactory sessionFactory;

    public UserServiceImpl() {
    }

    private UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //private UserDAO userDAO = new UserJdbcDAO(DBConfigJDBC.getMysqlConnection());//For JDBC

    private UserDAO userDAO = new UserHibernateDAO(DBConfigHibernate.getSessionFactory().openSession());

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl(DBConfigHibernate.getSessionFactory());
        }
        return userServiceImpl;
    }


    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void changeUser(User user) {
        userDAO.changeUser(user);
    }
}
