package DAO;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserHibernateDAO implements UserDAO {
    private Logger LOGGER = Logger.getLogger(UserHibernateDAO.class.getName());
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            LOGGER.log(Level.ALL, "Level All", e);
        }
        return users;
    }


    public boolean isUserExists(User user) {
        boolean result = false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE id= :idParam");
            query.setParameter("idParam", user.getId());
            User isUserExists = (User) query.uniqueResult();
            transaction.commit();
            if (isUserExists != null) result = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.ALL, "Level All", e);
        }
        return result;
    }


    @Override
    public void addUser(User user) {
        Transaction transaction = null;
        if (!isUserExists(user)) {
            try {
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.log(Level.ALL, "Level All", e);
            }
            session.close();
        }
    }


    @Override
    public User getUserByName(String name) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE name= :nameParam");
        query.setParameter("nameParam", name);
        User currentUser = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return currentUser;
    }

    @Override
    public User getUserById(long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE id= :idParam");
        query.setParameter("idParam", id);
        User currentUser = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return currentUser;
    }

    @Override
    public void deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        User currentUser = (User) session.get(User.class, id);
        session.delete(currentUser);
        transaction.commit();
        session.close();
    }

    @Override
    public void changeUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();

    }
}
