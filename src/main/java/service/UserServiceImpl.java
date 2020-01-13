package service;

import DAO.UserDAO;
import DAO.UserDaoFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userServiceImpl;

    private UserDAO userDAO = UserDaoFactory.getUserTypeDAO();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getService() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
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
    public User getUserByName(String name){return userDAO.getUserByName(name);}

    @Override
    public User getUserByNamePassword(String name, String password) {
        return userDAO.getUserByNamePassword(name, password);
    }

    @Override
    public void changeUser(User user) {
        userDAO.changeUser(user);
    }

    @Override
    public boolean checkUserPassword(String name, String password) {
        return userDAO.checkUserPassword(name, password);
    }
}
