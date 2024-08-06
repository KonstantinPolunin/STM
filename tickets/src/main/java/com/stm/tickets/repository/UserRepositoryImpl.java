package com.stm.tickets.repository;

import com.stm.tickets.models.User;
import com.stm.tickets.util.Util;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void registerUser(User user) {
        String sql = "insert into stm.users (first_name, last_name, login, password) values (?, ?, ?, ?)";
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getPassword());
                statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User findUserByLogin(String login) {
        String sql = "select * from stm.users where login = ?";
        try(Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    User user = new User();
                    user.setLogin(resultSet.getString("login"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isAdmin(String login) {
        String query = "SELECT r.name FROM stm.users u JOIN stm.roles r ON u.role_id = r.id WHERE u.login = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "ROLE_ADMIN".equals(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
