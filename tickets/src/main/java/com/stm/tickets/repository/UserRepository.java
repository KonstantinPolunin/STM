package com.stm.tickets.repository;

import com.stm.tickets.models.User;

import java.sql.SQLException;

public interface UserRepository {
    void registerUser(User user) throws SQLException;
    User findUserByLogin(String login);
    boolean isAdmin(String login);
}
