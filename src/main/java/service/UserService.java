package service;

import DAO.UserDAO;
import database.DBConfig;
import model.User;

import java.util.List;

public class UserService implements UserCRUD {
    //надо получить ДАО для доступа к подключению и действиям к БД с одним connection из DBconfig
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
