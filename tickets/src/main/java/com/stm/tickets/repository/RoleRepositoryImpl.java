package com.stm.tickets.repository;

import com.stm.tickets.util.Util;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public List<String> findAllRoles() {
        List<String> roles = new ArrayList<>();
        String sql = "SELECT name FROM stm.roles";
        try (Connection connection = Util.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                roles.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }
}
