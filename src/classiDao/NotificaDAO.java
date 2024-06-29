package classiDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import classi.Notifica;

public class NotificaDAO {
    private Connection conn;

    public NotificaDAO(Connection conn) {
        this.conn = conn;
    }

    
    
    public List<Notifica> getNotificheForUser(int idUtente) throws SQLException {
        List<Notifica> notifiche = new ArrayList<>();

        String query = "SELECT * FROM notifica WHERE idutente=? ORDER BY data_notifica ASC ,orario_notifica ASC";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
        	stmt.setInt(1,idUtente);
        	
        	ResultSet rs=stmt.executeQuery();
        	while(rs.next()) {
        		Notifica n =new Notifica(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getTimestamp(5),rs.getTimestamp(6),rs.getString(7),rs.getString(8));
        		notifiche.add(n);
        	}
        	return notifiche;
        }
        
    }
    
    

    
	String INSERT_IN_NOTIFICA="INSERT INTO notifica (idutente,idpost,data_notifica,orario_notifica,descrizione_notifica,tipo) VALUES (?,?,?,?,?,?)";
    String GET_ID_POST="SELECT idpost FROM post WHERE idutente=? AND idgruppo=? AND descrizione=? AND data_pubblicazione=? AND orario_pubblicazione=?";

    

}