import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRunner {
    public static void main(String[] args) throws SQLException {
        long flightId = 9;
        //language=PostgreSQL
        String deleteFlightSql = "DELETE from flight where id = ?";
        //language=PostgreSQL
        String deleteTicketSql = "DELETE from ticket where flight_id = ?";

        Connection connection = null;
        PreparedStatement deleteFlightStatementId = null;
        PreparedStatement deleteTicketStatementId = null;

        try {
            connection = ConnectionManager.open();
            deleteFlightStatementId = connection.prepareStatement(deleteFlightSql);
            deleteTicketStatementId = connection.prepareStatement(deleteTicketSql);

            connection.setAutoCommit(false);

            deleteFlightStatementId.setLong(1, flightId);
            deleteTicketStatementId.setLong(1, flightId);

            deleteTicketStatementId.executeUpdate();
//            if (true) {
//                throw new RuntimeException("Exception. Transaction is not atomaric");
//            } // transaction demo
            deleteFlightStatementId.executeUpdate();
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

            if (deleteTicketStatementId != null) {
                deleteTicketStatementId.close();
            }

            if (deleteFlightStatementId != null) {
                deleteFlightStatementId.close();
            }
        }
    }
}
