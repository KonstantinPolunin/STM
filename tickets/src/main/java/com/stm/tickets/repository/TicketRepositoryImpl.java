package com.stm.tickets.repository;


import com.stm.tickets.models.Rout;
import com.stm.tickets.models.Ticket;
import com.stm.tickets.models.Transporter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    private final DataSource dataSource;

    public TicketRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Ticket> findAvailableTickets(String departure, String destination, String transporter,
                                             LocalDateTime time, int page, int size) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT t.id, t.rout_id ,t.time, t.seat, t.price, r.departure, r.destination, r.duration, r.transporter_id, s.name, s.phone_number FROM stm.tickets t" +
                " JOIN stm.routs r ON t.rout_id = r.id" +
                " JOIN stm.transporters s ON r.transporter_id = s.id" +
                " WHERE t.is_bought = false";

        if (departure != null && !departure.isEmpty()) {
            sql += " AND r.departure LIKE ?";
        }
        if (destination != null && !destination.isEmpty()) {
            sql += " AND r.destination LIKE ?";
        }
        if (transporter != null && !transporter.isEmpty()) {
            sql += " AND s.name LIKE ?";
        }
        if (time != null) {
            sql += " AND t.time >= ?";
        }

        sql += " LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            int index = 1;

            if (departure != null && !departure.isEmpty()) {
                statement.setString(index++, "%" + departure + "%");
            }
            if (destination != null && !destination.isEmpty()) {
                statement.setString(index++, "%" + destination + "%");
            }
            if (transporter != null && !transporter.isEmpty()) {
                statement.setString(index++, "%" + transporter + "%");
            }
            if (time != null) {
                statement.setObject(index++, time);
            }

            statement.setInt(index++, size);
            statement.setInt(index++, (page - 1) * size);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(resultSet.getLong("id"));
                    ticket.setTime(resultSet.getObject("time", LocalDateTime.class));
                    ticket.setSeat(resultSet.getInt("seat"));
                    ticket.setPrice(resultSet.getInt("price"));

                    Rout rout = new Rout();
                    rout.setId(resultSet.getLong("rout_id"));
                    rout.setDeparture(resultSet.getString("departure"));
                    rout.setDestination(resultSet.getString("destination"));
                    rout.setDuration(resultSet.getInt("duration"));

                    Transporter newTransporter = new Transporter();
                    newTransporter.setId(resultSet.getLong("transporter_id"));
                    newTransporter.setName(resultSet.getString("name"));
                    newTransporter.setPhoneNumber(resultSet.getString("phone_number"));

                    rout.setTransporter(newTransporter);
                    ticket.setRout(rout);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public void buyTicket(Long ticketId, Long userId) {
        String sql = "UPDATE stm.tickets SET is_bought = TRUE, user_id = ? WHERE id = ? AND is_bought = FALSE";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(2, ticketId);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Ticket findById(Long id) {
        String sql = "select * from stm.tickets where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(resultSet.getLong("id"));
                    ticket.setTime(resultSet.getObject("time", LocalDateTime.class));
                    ticket.setSeat(resultSet.getInt("seat"));
                    ticket.setPrice(resultSet.getInt("price"));
                    ticket.setIsBought(resultSet.getBoolean("is_bought"));
                    ticket.setUserId(resultSet.getLong("user_id"));
                    return ticket;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Ticket> findByUserId(Long userId) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM stm.tickets t" +
                " WHERE t.is_bought = true AND t.user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(resultSet.getLong("id"));
                    ticket.setUserId(resultSet.getLong("user_id"));
                    ticket.setTime(resultSet.getObject("time", LocalDateTime.class));
                    ticket.setSeat(resultSet.getInt("seat"));
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public void deleteTicket(Long id) {
        String sql = "DELETE FROM stm.tickets WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateTicket(Long id, Ticket ticket) {
        String sql = "UPDATE stm.tickets SET rout_id = ?, time = ?, seat = ?, price = ? WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(5, ticket.getId());
            statement.setLong(1, ticket.getRout().getId());
            statement.setObject(2, ticket.getTime());
            statement.setInt(3, ticket.getSeat());
            statement.setInt(4, ticket.getPrice());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTicket(Ticket ticket) {
        String sql = "insert into stm.tickets (id, rout_id ,time, seat, price) values (?, ?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, ticket.getId());
            statement.setLong(2, ticket.getRout().getId());
            statement.setObject(3, ticket.getTime());
            statement.setInt(4, ticket.getSeat());
            statement.setInt(5, ticket.getPrice());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


