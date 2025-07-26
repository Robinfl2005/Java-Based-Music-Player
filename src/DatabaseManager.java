

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:music.db";

    // Method to connect to the database
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Method to create tables if they don't exist
    public static void initializeDatabase() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            String createAlbumsTable = "CREATE TABLE IF NOT EXISTS albums (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "artist TEXT)";

            String createSongsTable = "CREATE TABLE IF NOT EXISTS songs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "duration REAL," +
                    "album_name TEXT)";

            stmt.execute(createAlbumsTable);
            stmt.execute(createSongsTable);

            System.out.println("Database is ready.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

