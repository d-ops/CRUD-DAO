import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // String flightId = "2";
         String flightId = "2 OR 1 = 1; Drop database info;"; // SQL injection
        List<Long> list = getTicketsByFlightId(flightId);
        System.out.println(list);
    }

    private static List<Long> getTicketsByFlightId(String flightId) throws SQLException {
        String sql = """
                SELECT id from ticket where flight_id = %s
                """.formatted(flightId);
        List<Long> list = new ArrayList<>();

        try (Connection connection = ConnectionManager.open();
        Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                // list.add(resultSet.getLong("id"));
                list.add(resultSet.getObject("id", Long.class)); // null safe (Optional)
            }
        }

        return list;
    }
}
