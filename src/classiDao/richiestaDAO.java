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
    private static final String INSERT_RICHIESTA_SQL = "INSERT INTO richiesta (idrichiedente, idgruppo, data_richiesta , orario_richiesta) VALUES (?, ?, ?, ?)";
    private static final String GET_RICHIESTE_FOR_USER_SQL = "SELECT * FROM richiesta WHERE idrichiedente = ?";

    public static richiestaDAO getInstance(Connection conn) {
        return new richiestaDAO(conn);
    }

	private static Connection conn;

    public richiestaDAO(Connection conn) {
        this.conn = conn;
    }

    
    

    public boolean insertRichiesta(int currentUser, int i) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_RICHIESTA_SQL)) {
            //funziona
            preparedStatement.setInt(1, currentUser);
            preparedStatement.setInt(2, i);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(4, currentTime);
            preparedStatement.setTimestamp(3, currentTime);
            preparedStatement.executeUpdate();
            return true;
        }
    }
    
    private final static String QVediRichiesteDiIscrizioneAiTuoiGruppi="SELECT r.idrichiedente,r.idgruppo,r.data_richiesta FROM richiesta as r,gruppo as g,crea as c WHERE c.idgruppo=g.idgruppo AND c.idutente=? AND r.idgruppo=c.idgruppo  ";
public static List<richiesta> VediRichiesteDiIscrizioneAiTuoiGruppi(int currentUser) throws SQLException {
	ArrayList<richiesta> notifichedirichiestaiscrizioneaituoigruppi = new ArrayList<>();
	try (PreparedStatement stmt = conn.prepareStatement(QVediRichiesteDiIscrizioneAiTuoiGruppi)) {
		stmt.setInt(1, currentUser);
		ResultSet s=stmt.executeQuery();
		while(s.next()) {
			richiesta r= new richiesta(s.getInt(1),s.getInt(2));
			notifichedirichiestaiscrizioneaituoigruppi.add(r);
		}
	}
	return notifichedirichiestaiscrizioneaituoigruppi;
	
	
}

    public List<richiesta> getRichiesteForUser(int idUtente) throws SQLException {
        List<richiesta> richieste = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GET_RICHIESTE_FOR_USER_SQL)) {
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    richiesta richiesta = new richiesta(
                            rs.getInt("idutente"),
                            rs.getInt("idgruppo"),
                            rs.getTimestamp("data_ora").toLocalDateTime()
                            
                    );
                    richieste.add(richiesta);
                }
            }
        }
        return richieste;
    }


}