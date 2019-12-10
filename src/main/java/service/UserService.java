package service;

import DAO.UserDAO;
import util.DBConfig;
import model.User;

import java.util.List;

public class UserService implements UserServiceImpl {

    private UserDAO userDAO = new UserDAO(DBConfig.getMysqlConnection());

    public UserService() {
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    public void changeUser(User user) {
        userDAO.changeUser(user);
    }

}
