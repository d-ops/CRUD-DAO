import util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // String flightId = "2";
        Long flightId = 2L;
        List<Long> list = getTicketsByFlightId(flightId);
        System.out.println(list);

        List<Long> result = getIdBetweenDates(LocalDate.of(2020, 10, 1).atStartOfDay(), LocalDateTime.now());
        System.out.println(result);
    }

    private static List<Long> getIdBetweenDates(LocalDateTime start, LocalDateTime end) throws SQLException {
        String sql = """
                select id from flight where departure_date between ? and ?
                """;
        List<Long> result = new ArrayList<>();
        try(Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setFetchSize(50);
            preparedStatement.setQueryTimeout(10);
            System.out.println(preparedStatement);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(start));
            System.out.println(preparedStatement);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(end));
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultSet.getObject("id", Long.class));
            }
        }
        return result;
    }

    private static List<Long> getTicketsByFlightId(Long flightId) throws SQLException {
        String sql = """
                SELECT id from ticket where flight_id = ?
                """;
        List<Long> list = new ArrayList<>();

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, flightId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // list.add(resultSet.getLong("id"));
                list.add(resultSet.getObject("id", Long.class)); // null safe (Optional)
            }
        }

        return list;
    }
}
