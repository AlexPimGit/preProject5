package service;

import Config.DBConfig;
import DAO.UserDAO;
import Interfaces.Service;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService implements Service {
    //надо получить ДАО для доступа к подключению и действиям к БД с одним connection из DBconfig
    private static UserDAO getUserDAO() {
        return new UserDAO(DBConfig.getMysqlConnection());
    }


    public UserService() {
    }

    public void crateDataBase() {
        getUserDAO().createTable();
    }

    public List<User> getAllUsers() {
        return getUserDAO().getAllUsers();
    }

    public void deleteUser(Long id) {
        getUserDAO().deleteUser(id);
    }

    public void addUser(User user) {
        getUserDAO().addUser(user);
    }

    public User getUserById(long id) {
        return getUserDAO().getUserById(id);
    }

    public void changeUser(User user) {
        getUserDAO().changeUser(user);
    }

}
