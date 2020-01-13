package DAO;

import model.User;
import org.hibernate.*;
import util.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserHibernateDAO implements UserDAO {
    private Logger LOGGER = Logger.getLogger(UserHibernateDAO.class.getName());
    private SessionFactory sessionFactory = DBHelper.getSessionFactory();


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            LOGGER.log(Level.WARNING, "All users are not available", e);
        }
        return users;
    }

    public boolean isUserExists(User user) {
        boolean result = false;
        Transaction transaction;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE id= :idParam");
            query.setParameter("idParam", user.getId());
            User isUserExists = (User) query.uniqueResult();
            transaction.commit();
            if (isUserExists != null) result = true;
            session.close();
        } catch (HibernateException e) {
            LOGGER.log(Level.WARNING, "User does not exist", e);
        }
        return result;
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = null;
        Session session = null;
        if (!isUserExists(user)) {
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
                session.close();
            } catch (HibernateException e) {
                transaction.rollback();
                LOGGER.log(Level.WARNING, "User not added", e);
            }
        }
    }

    @Override
    public User getUserByName(String name) {
        Transaction transaction = null;
        Session session = null;
        User currentUser = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE name= :nameParam");
            query.setParameter("nameParam", name);
            currentUser = (User) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            LOGGER.log(Level.WARNING, "User not found", e);
        }
        return currentUser;
    }

    @Override
    public User getUserByNamePassword(String name, String password) {
        Transaction transaction = null;
        Session session = null;
        User currentUser = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE name= :nameParam AND password= :passwordParam");
            query.setParameter("nameParam", name);
            query.setParameter("passwordParam", password);
            currentUser = (User) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            LOGGER.log(Level.WARNING, "User not found", e);
        }
        return currentUser;
    }

    @Override
    public User getUserById(long id) {
        Transaction transaction = null;
        Session session = null;
        User currentUser = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE id= :idParam");
            query.setParameter("idParam", id);
            currentUser = (User) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            LOGGER.log(Level.WARNING, "User not found", e);
        }
        return currentUser;
    }

    @Override
    public void deleteUser(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User currentUser = (User) session.get(User.class, id);
            session.delete(currentUser);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, "User not deleted", e);
        }
        if (session != null) {
            session.close();
        }
    }

    @Override
    public void changeUser(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOGGER.log(Level.WARNING, "User not changed", e);
        }
        session.close();
    }

    @Override
    public boolean checkUserPassword(String name, String password) {
        try {
            String checkPassword = getUserByName(name).getPassword();
            if (checkPassword.equals(password)) {
                return true;
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, "User not found", e);
        }
        return false;
    }
}
