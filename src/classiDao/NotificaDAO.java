package classiDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import classi.notifica;

public class NotificaDAO {
    private Connection conn;

    public NotificaDAO(Connection conn) {
        this.conn = conn;
    }

    
    
    public List<notifica> getNotificheForUser(int idUtente) throws SQLException {
        List<notifica> notifiche = new ArrayList<>();
        //nella query prendo notifiche dei soli i gruppi di cui l'utente è creatore 
        String query = "SELECT * FROM notifica WHERE idgruppo IN (SELECT idgruppo FROM crea WHERE idutente=?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
        	stmt.setInt(1,idUtente);
        	
        	ResultSet rs=stmt.executeQuery();
        	while(rs.next()) {
        		notifica n =new notifica(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getTimestamp(5),rs.getTimestamp(6),rs.getString(7));
        		notifiche.add(n);
        	}
        	return notifiche;
        }
        
    }
    
    
    
    
	String INSERT_IN_NOTIFICA="INSERT INTO notifica (idutente,idpost,idgruppo,data_notifica,orario_notifica,descrizione_notifica) VALUES (?,?,?,?,?,?)";
    String GET_ID_POST="SELECT idpost FROM post WHERE idutente=? AND idgruppo=? AND descrizione=? AND data_pubblicazione=? AND orario_pubblicazione=?";
//prima recupero id del post tramite query con come argomenti gli argomenti 
    //passati alla funz e successivamente avendo l'id del post che 
    //sarebbe stato impossibile da recuperare altrimenti (almeno credo)
    //inserisco notifica 
    public boolean SendNotificaForPost(int currentUser,int idgruppo, String desc, Timestamp currentTime)
    {
	try (PreparedStatement stmt = conn.prepareStatement(GET_ID_POST)) {
		stmt.setInt(1, currentUser);
		stmt.setInt(2, idgruppo);
		stmt.setString(3,desc);
		stmt.setTimestamp(4, currentTime);
		stmt.setTimestamp(5,currentTime);
		ResultSet rs=stmt.executeQuery();
		int idpost=0;
		while(rs.next()) {
			idpost=rs.getInt(1);
		}
		System.out.println(idpost);
		
		//devo inserire una riga
		//multiple aventi come idutente l'utente che manda la notifica
		try(PreparedStatement stmt2 = conn.prepareStatement(INSERT_IN_NOTIFICA)){
			stmt2.setInt(1, currentUser);
			stmt2.setInt(2, idpost);
			stmt2.setInt(3,idgruppo);
			stmt2.setTimestamp(4, currentTime);
			stmt2.setTimestamp(5,currentTime);
			stmt2.setString(6,"aggiunto un post");
			stmt2.executeUpdate();
		
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    
    //sarebbe il punto 1 della traccia di obj per i gruppi a tre membri
    public boolean SendNotificaForAccesso(int currentUser, int idgruppo) {
		String qry="INSERT INTO notifica (idutente,idgruppo,data_notifica,orario_notifica,descrizione_notifica) VALUES (?,?,?,?,?)";
    	try(PreparedStatement s = conn.prepareStatement(qry)){
    		s.setInt(1, currentUser);
    		s.setInt(2, idgruppo);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    		s.setTimestamp(3, currentTime);
    		s.setTimestamp(4, currentTime);
    		s.setString(5,"effettuato l'accesso");
    		s.executeUpdate();
    		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return false;
    	
    }
    
    

}