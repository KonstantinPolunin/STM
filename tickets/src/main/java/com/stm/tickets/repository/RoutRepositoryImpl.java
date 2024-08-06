package com.stm.tickets.repository;

import com.stm.tickets.models.Rout;
import com.stm.tickets.util.Util;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class RoutRepositoryImpl implements RoutRepository {
    @Override
    public void addRout(Rout rout) {
        String sql = "insert into stm.routs (id, departure ,destination, duration, transporter_id) values (?, ?, ?, ?, ?)";
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, rout.getId());
            statement.setString(2, rout.getDeparture());
            statement.setString(3, rout.getDestination());
            statement.setInt(4, rout.getDuration());
            statement.setLong(5, rout.getTransporter().getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateRout(Long id, Rout rout) {
        String sql = "UPDATE stm.routs SET departure = ?, destination = ?, duration = ?, transporter_id = ? WHERE id = ?";
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(5, rout.getId());
            statement.setString(1, rout.getDeparture());
            statement.setString(2, rout.getDestination());
            statement.setInt(3, rout.getDuration());
            statement.setLong(4, rout.getTransporter().getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteRout(Long id) {
        String sql = "DELETE FROM stm.routs WHERE id = ?";
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
