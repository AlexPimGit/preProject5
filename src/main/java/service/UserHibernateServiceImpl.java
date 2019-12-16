package service;

import DAO.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBConfigHibernate;


import java.util.List;

public class UserHibernateServiceImpl implements UserService {
    private static UserHibernateServiceImpl userHibernateServiceImpl;

    private SessionFactory sessionFactory;

    private UserHibernateServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserHibernateServiceImpl getInstance() {
        if (userHibernateServiceImpl == null) {
            userHibernateServiceImpl = new UserHibernateServiceImpl(DBConfigHibernate.getSessionFactory());
        }
        return userHibernateServiceImpl;
    }

    @Override
    public List<User> getAllUsers() {
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsers();
    }

    public void deleteUser(Long id) {
        new UserHibernateDAO(sessionFactory.openSession()).deleteUser(id);
    }

    public void addUser(User user) {
        new UserHibernateDAO(sessionFactory.openSession()).addUser(user);
    }

    public User getUserById(long id) {
        return new UserHibernateDAO(sessionFactory.openSession()).getUserById(id);
    }

    public void changeUser(User user) {
        new UserHibernateDAO(sessionFactory.openSession()).changeUser(user);
    }
}
