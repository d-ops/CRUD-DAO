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
        try(Connection connection = ConnectionManager.open();
            PreparedStatement deleteFlightStatementId = connection.prepareStatement(deleteFlightSql);
            PreparedStatement deleteTicketStatementId = connection.prepareStatement(deleteTicketSql)){
            deleteFlightStatementId.setLong(1, flightId);
            deleteTicketStatementId.setLong(1, flightId);

            deleteTicketStatementId.executeUpdate();
            if (true){
                throw new RuntimeException("Exception. Transaction is not atomaric");
            }
            deleteFlightStatementId.executeUpdate();
        }

    }
}
