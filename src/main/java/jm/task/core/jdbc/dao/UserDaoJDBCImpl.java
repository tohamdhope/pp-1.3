package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "CREATE TABLE IF NOT EXISTS User " +
                    " (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastName VARCHAR(255) , " +
                    " age INT)";
            statement.executeUpdate(SQL);
            System.out.println("Create table complete.");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "DROP TABLE IF EXISTS User";
            statement.executeUpdate(SQL);
            System.out.println("Table has been delete.");
        } catch (SQLException sqlException) {
            System.out.println("Table delete is failed ...");
            sqlException.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO User (name, lastName, age) " +
                    "VALUES (?, ?, ?)"
            );
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User has been adding to table.");

        } catch (SQLException sqlException) {
            System.out.println("Adding user failed ...");
            sqlException.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            String SQL = "DELETE FROM User WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User has been delete.");
        } catch (SQLException sqlException) {
            System.out.println("User delete failed ...");
            sqlException.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM User")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "TRUNCATE TABLE User";
            statement.executeUpdate(SQL);
            System.out.println("All users from table has been delete.");
        } catch (SQLException sqlException) {
            System.out.println("Delete all users from table failed ...");
            sqlException.printStackTrace();
        }
    }
}
