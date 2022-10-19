package dao;

import entity.Flight;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {
    private static final FlightDao INSTANCE = new FlightDao();

    private FlightDao() {

    }

    public static FlightDao getINSTANCE() {
        return INSTANCE;
    }

    private final String FIND_BY_ID = """
            select
            id,
            flight_no,
            departure_date,
            departure_airport_code,
            arrival_date,
            arrival_airport_code,
            aircraft_id,
            status
            from flight
            where id = ?
            """;


    @Override
    public List<Flight> findAll() {
        return null;
    }

    public Optional<Flight> findById(Long id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Flight flight = null;
            if (resultSet.next()) {
                flight = new Flight(
                        resultSet.getLong("id"),
                        resultSet.getString("flight_no"),
                        resultSet.getTimestamp("departureDate").toLocalDateTime(),
                        resultSet.getString("departure_airport_code"),
                        resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                        resultSet.getString("arrival_airport_code"),
                        resultSet.getInt("aircraft_id"),
                        resultSet.getString("status")
                );
            }
            return Optional.ofNullable(flight);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }
}
