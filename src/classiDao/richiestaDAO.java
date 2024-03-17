package classiDao;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import classi.richiesta;

public class richiestaDAO {
    private static final String INSERT_RICHIESTA_SQL = "INSERT INTO richiesta (username, id_utente, id_gruppo, data_ora) VALUES (?, ?, ?, ?)";
    private static final String GET_RICHIESTE_FOR_USER_SQL = "SELECT * FROM richiesta WHERE id_utente = ?";

    public static richiestaDAO getInstance(Connection conn) {
        return new richiestaDAO(conn);
    }

	private Connection conn;

    public richiestaDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertRichiesta(String username, int idUtente, int idGruppo, java.sql.Timestamp localDateTime) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_RICHIESTA_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, idUtente);
            preparedStatement.setInt(3, idGruppo);
            preparedStatement.setTimestamp(4, localDateTime);
            preparedStatement.executeUpdate();
        }
    }

    public List<richiesta> getRichiesteForUser(int idUtente) throws SQLException {
        List<richiesta> richieste = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GET_RICHIESTE_FOR_USER_SQL)) {
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    richiesta richiesta = new richiesta(
                            rs.getInt("id_utente"),
                            rs.getInt("id_gruppo"),
                            rs.getTimestamp("data_ora").toLocalDateTime()
                    );
                    richieste.add(richiesta);
                }
            }
        }
        return richieste;
    }
}