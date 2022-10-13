import util.ConnectionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobRunner {
    public static void main(String[] args) throws SQLException, IOException {
       saveBlob();
    }

    private static void saveBlob() throws SQLException, IOException {
        String sql = """
                UPDATE aircraft set image = ? 
                where id = 2
                """;

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            Blob blob = connection.createBlob();
            blob.setBytes(1, Files.readAllBytes(Path.of("resources", "boeing777.jpg")));

            preparedStatement.setBlob(1, blob);
            preparedStatement.executeUpdate();
            connection.commit();

            System.out.println("success");
        }
    }
}
