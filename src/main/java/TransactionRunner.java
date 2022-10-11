import util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionRunner {
    public static void main(String[] args) throws SQLException {
        long flightId = 8;
        //language=PostgreSQL
        String deleteFlightSql = "DELETE from flight where id = " + flightId;
        //language=PostgreSQL
        String deleteTicketSql = "DELETE from ticket where flight_id = " + flightId;

        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionManager.open();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            statement.addBatch(deleteTicketSql);
            statement.addBatch(deleteFlightSql);

            int[] result = statement.executeBatch();

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
