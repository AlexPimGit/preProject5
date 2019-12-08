package service;

import model.User;

import java.util.List;

public interface UserCRUD {
    List getAllUsers();

    void deleteUser(Long id);

    void addUser(User user);

    User getUserById(long id);

    void changeUser(User user);
}


