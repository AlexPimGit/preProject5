package service;

import model.User;

import java.util.List;

public interface UserService {
    List getAllUsers();

    void deleteUser(Long id);

    void addUser(User user);

    User getUserById(long id);

    User getUserByName(String name);

    User getUserByNamePassword(String name, String password);

    void changeUser(User user);

    boolean checkUserPassword(String name, String password);


}


