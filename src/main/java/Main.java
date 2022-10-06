import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        String sql = """
                select * from ticket;
                 """;
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            System.out.println(connection.getTransactionIsolation());
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getLong("id"));
                System.out.println(result.getString("passenger_no"));
                System.out.println(result.getBigDecimal("cost"));
                System.out.println("-----------------------------");
            }
            System.out.println(result);
        }
    }
}
