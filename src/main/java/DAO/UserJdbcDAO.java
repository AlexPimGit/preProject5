package DAO;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserJdbcDAO implements UserDAO {
    private Logger LOGGER = Logger.getLogger(UserJdbcDAO.class.getName());
    private Connection connection = DBHelper.getConnection();

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT * FROM users");
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String nickname = resultSet.getString("nickname");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                User user = new User(id, name, nickname, role, password);
                list.add(user);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "All users are not available", e);
        }
        return list;
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (name, nickname, role, password) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "User not added", e);
        }
    }

    public User getUserByName(String name) {
        User user = new User();
        String query = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "User not found", e);
        }
        return user;
    }

    @Override
    public User getUserByNamePassword(String name, String password) {
        User user = new User();
        String query = "SELECT * FROM users WHERE name = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "User not found", e);
        }
        return user;
    }

    @Override
    public User getUserById(long id) {
        User user = new User();
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            user.setName(resultSet.getString("name"));
            user.setNickname(resultSet.getString("nickname"));
            user.setRole(resultSet.getString("role"));
            user.setPassword(resultSet.getString("password"));
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "User not found", e);
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "User not deleted", e);
        }
    }

    @Override
    public void changeUser(User user) {
        String update = "UPDATE users SET name = ?, nickname = ?, role = ?, password = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "User not changed", e);
        }
    }

    @Override
    public boolean checkUserPassword(String name, String password) {
        String checkPassword = getUserByName(name).getPassword();
        if (checkPassword.equals(password)) {
            return true;
        } else return false;
    }

    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id bigint auto_increment, name varchar(256), nickname varchar(256), role varchar(256),password varchar(256), primary key (id))");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Table not created", e);
        }
    }

    public void dropTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Table not deleted", e);
        }
    }
}
