package com.stm.tickets.repository;

import com.stm.tickets.models.Transporter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TransporterRepositoryImpl implements TransporterRepository {

    private final DataSource dataSource;

    public TransporterRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addTransporter(Transporter transporter) {
        String sql = "insert into stm.transporters (id, name, phone_number) values (?, ?, ?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, transporter.getId());
            statement.setString(2, transporter.getName());
            statement.setString(3, transporter.getPhoneNumber());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTransporter(Long id, Transporter transporter) {
        String sql = "UPDATE stm.transporters SET name = ?, phone_number = ? WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(3, transporter.getId());
            statement.setString(1, transporter.getName());
            statement.setString(2, transporter.getPhoneNumber());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTransporter(Long id) {
        String sql = "DELETE FROM stm.transporters WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
