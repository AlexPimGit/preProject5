package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    List getAllUsers();

    void addUser(User user);

    User getUserByName(String name);

    User getUserById(long id);

    void deleteUser(Long id);

    void changeUser(User user);

}
