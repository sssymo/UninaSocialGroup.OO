import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import database.DbConnection;

public class richiestaDAO {
    private static richiestaDAO instance;
    private final String INSERT_RICHIESTA_SQL = "INSERT INTO richiesta (username, id_utente, id_gruppo, data_ora) VALUES (?, ?, ?, ?)";

    private richiestaDAO() {}

    public static richiestaDAO getInstance() {
        if (instance == null) {
            instance = new richiestaDAO();
        }
        return instance;
    }

    public void insertRichiesta(String username, int idUtente, LocalDateTime localDateTime) throws SQLException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RICHIESTA_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, idUtente);
            preparedStatement.setInt(3, localDateTime);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
        }
    }
}