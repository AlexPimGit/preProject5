package service;

import DAO.UserJdbcDAO;
import util.DBConfigJDBC;
import model.User;

import java.util.List;

public class UserJdbcServiceImpl implements UserService {

    private UserJdbcDAO userJdbcDAO = new UserJdbcDAO(DBConfigJDBC.getMysqlConnection());

    public UserJdbcServiceImpl() {
    }

    public List<User> getAllUsers() {
        return userJdbcDAO.getAllUsers();
    }

    public void deleteUser(Long id) {
        userJdbcDAO.deleteUser(id);
    }

    public void addUser(User user) {
        userJdbcDAO.addUser(user);
    }

    public User getUserById(long id) {
        return userJdbcDAO.getUserById(id);
    }

    public void changeUser(User user) {
        userJdbcDAO.changeUser(user);
    }

}
