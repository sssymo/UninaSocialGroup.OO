package classiDao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PGTimestamp;

import classi.richiesta;

public class richiestaDAO {
    private static final String INSERT_RICHIESTA_SQL = "INSERT INTO richiesta (idrichiedente, idgruppo, data_richiesta , orario_richiesta, accettata) VALUES (?, ?, ?, ?,?)";
    private static final String GET_RICHIESTE_FOR_USER_SQL = "SELECT * FROM richiesta WHERE id_utente = ?";

    public static richiestaDAO getInstance(Connection conn) {
        return new richiestaDAO(conn);
    }

	private Connection conn;

    public richiestaDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertRichiesta(String currentUser, String i) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_RICHIESTA_SQL)) {
            //funziona
            preparedStatement.setString(1, currentUser);
            preparedStatement.setString(2, i);
            
            
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            
            
            preparedStatement.setTimestamp(3, currentTime);
            preparedStatement.setTimestamp(4, currentTime);
            
         
            preparedStatement.setBoolean(5, false);
            
          
            preparedStatement.executeUpdate();
            
        
            return true;
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