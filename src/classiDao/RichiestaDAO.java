package classiDao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


import classi.Richiesta;

public class RichiestaDAO {
    private static final String INSERT_RICHIESTA_SQL = "INSERT INTO richiesta (idrichiedente, idgruppo, data_richiesta , orario_richiesta) VALUES (?, ?, ?, ?)";
    private static final String GET_RICHIESTE_FOR_USER_SQL = "SELECT * FROM richiesta WHERE idrichiedente = ?";

    public static RichiestaDAO getInstance(Connection conn) {
        return new RichiestaDAO(conn);
    }

	private static Connection conn;

    public RichiestaDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean deleteRequest(int currentUser,int groupId) {
    	try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM richiesta where idrichiedente=? and idgruppo=?")) {
    		try (PreparedStatement preparedStatement2 = conn.prepareStatement("DELETE FROM accetta where idgruppo=?")){
        		
                preparedStatement2.setInt(1, groupId);
                preparedStatement2.executeUpdate();
    		}
    		preparedStatement.setInt(1, currentUser);
            preparedStatement.setInt(2, groupId);
            preparedStatement.executeUpdate();
            return true;
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

    	
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
    
    private final static String QVediRichiesteDiIscrizioneAiTuoiGruppi="SELECT r.idrichiedente,r.idgruppo,r.data_richiesta,orario_richiesta FROM richiesta as r,gruppo as g,crea as c WHERE c.idgruppo=g.idgruppo AND c.idutente=? AND r.idgruppo=c.idgruppo ORDER BY r.data_richiesta DESC ";
public static List<Richiesta> VediRichiesteDiIscrizioneAiTuoiGruppi(int currentUser) throws SQLException {
	ArrayList<Richiesta> notifichedirichiestaiscrizioneaituoigruppi = new ArrayList<>();
	try (PreparedStatement stmt = conn.prepareStatement(QVediRichiesteDiIscrizioneAiTuoiGruppi)) {
		stmt.setInt(1, currentUser);
		ResultSet s=stmt.executeQuery();
		while(s.next()) {
			Richiesta r= new Richiesta(s.getInt(1),s.getInt(2),s.getTimestamp(3).toLocalDateTime(),s.getTimestamp(4).toLocalDateTime());
			notifichedirichiestaiscrizioneaituoigruppi.add(r);
		}
	}
	return notifichedirichiestaiscrizioneaituoigruppi;
	
	
}
public boolean checkiscrizione(int idUtente, int idGruppo) {
	try (PreparedStatement s = conn.prepareStatement("SELECT * FROM iscrizione WHERE idutente=? AND idgruppo=?")){
		s.setInt(2, idGruppo);
		s.setInt(1, idUtente);
		ResultSet rs=s.executeQuery();
	    while(rs.next()) {
	    	return true;
	    }
	    	return false;
	   
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// TODO Auto-generated method stub
	return false;

}
    public List<Richiesta> getRichiesteForUser(int idUtente) throws SQLException {
        List<Richiesta> richieste = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GET_RICHIESTE_FOR_USER_SQL)) {
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Richiesta richiesta = new Richiesta(
                            rs.getInt("idrichiedente"),
                            rs.getInt("idgruppo"),
                            rs.getTimestamp("data_richiesta").toLocalDateTime(),rs.getTimestamp("orario_richiesta").toLocalDateTime()
                            
                    );
                    richieste.add(richiesta);
                }
            }
        }
        return richieste;
    }
    public boolean checkrichiesta(int idUtente, int idGruppo) {
        String query = "SELECT 1 FROM richiesta WHERE idrichiedente = ? AND idgruppo = ? LIMIT 1";

        try (PreparedStatement s = conn.prepareStatement(query)) {
            s.setInt(1, idUtente);
            s.setInt(2, idGruppo);

            try (ResultSet rs = s.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}