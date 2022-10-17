package dao;

import entity.Ticket;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.*;

public class TicketDao {
    private final String SQL_DELETE = """
            DELETE from ticket
            where id = ?
            """;
    private final String SQL_CREATE = """
            INSERT into ticket("passenger_no", passenger_name, flight_id, seat_no, cost) 
            values (?, ?, ?, ?, ?)
            """;


    private static final TicketDao INSTANCE = new TicketDao();

    private TicketDao() {

    }

    public static TicketDao getINSTANCE() {
        return INSTANCE;
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Ticket save(Ticket ticket){
        try (Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement =
                connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, ticket.getPassengerNo());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlightId());
            preparedStatement.setString(4, ticket.getSeatNo());
            preparedStatement.setBigDecimal(5, ticket.getCost());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                ticket.setId(generatedKeys.getLong("id"));
            }
            return ticket;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
