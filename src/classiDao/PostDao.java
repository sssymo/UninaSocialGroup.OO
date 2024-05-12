package classiDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import classi.Post;
public class PostDao {

    private static Connection conn;

    public PostDao(Connection conn) {
        this.conn = conn;
    }
    
    
    
    static String getnpost="select count(idpost) from post where idgruppo=?";
    public static int GetNumPost(int idg) {
    	
    	try(PreparedStatement stmt = conn.prepareStatement(getnpost)){
    	stmt.setInt(1, idg);
    	ResultSet rs=stmt.executeQuery();
    	while(rs.next()) {
    		return rs.getInt(1);
    	}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return 0;
    }
    

    
    static String getnpostinviatidautente="select count(idpost) from post where idgruppo=? and idutente=?";
    public static int GetNumPost(int idg,int idu) {
    	
    	try(PreparedStatement stmt = conn.prepareStatement(getnpostinviatidautente)){
    	stmt.setInt(1, idg);
    	stmt.setInt(2, idu);
    	ResultSet rs=stmt.executeQuery();
    	while(rs.next()) {
    		return rs.getInt(1);
    	}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return 0;
    }
    
    private static final String INSERT_POST = "INSERT INTO post ( idutente ,idgruppo ,descrizione,data_pubblicazione,orario_pubblicazione) VALUES ( ?, ?, ?,?,?)";
    public static boolean InserisciPost(int currentUser,int idgruppo, String descpost,Timestamp currentTime) {
    	try(PreparedStatement stmt = conn.prepareStatement(INSERT_POST)){
    		stmt.setInt(1, currentUser);
    		stmt.setInt(2, idgruppo);
    		stmt.setString(3, descpost);
    		
    		stmt.setTimestamp(4, currentTime);
    		stmt.setTimestamp(5, currentTime);
    		stmt.executeUpdate();   
    		
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;}

    	//questa query recupera tutti i post 
    	//per un utente (quelli nella home, quindi senza filtri sul gruppo)
final String RECUPERA_POST = "SELECT * FROM post as p where p.idgruppo IN (SELECT i.idgruppo FROM iscrizione as i WHERE i.idutente=?)";    
public List<Post> RecuperaPost(int idutente){
	ArrayList<Post> posts=new ArrayList<>();
	try(PreparedStatement stmt = conn.prepareStatement(RECUPERA_POST)){
		stmt.setInt(1, idutente);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			int idpost=rs.getInt("idpost");
			int iduutente=rs.getInt("idutente");//utente che ha fatt o post
			int idgruppo=rs.getInt("idgruppo");
			String desc=rs.getString("descrizione");
			Date data_pubblicazione=rs.getDate("data_pubblicazione");
			Time ora_pubblicazione =rs.getTime("orario_pubblicazione");
			//per foto non so come fare 
			Post p =new Post(idpost,iduutente,idgruppo,desc,data_pubblicazione,ora_pubblicazione);
			posts.add(p);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return posts;
 }

//questa query recupera tutti i post 
//per un utente (con filtri sul gruppo) con overlogading
final static String RECUPERA_POST2 = "SELECT * FROM post as p where p.idgruppo=?";    
public static List<Post> RecuperaPost(int idutente,int idgruppo){
	ArrayList<Post> posts=new ArrayList<>();
	try(PreparedStatement stmt = conn.prepareStatement(RECUPERA_POST2)){
		stmt.setInt(1, idgruppo);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			int idpost=rs.getInt("idpost");
			int iduutente=rs.getInt("idutente");//utente che ha fatt o post
			int idgruuppo=rs.getInt("idgruppo");
			String desc=rs.getString("descrizione");
			Date data_pubblicazione=rs.getDate("data_pubblicazione");
			Time ora_pubblicazione =rs.getTime("orario_pubblicazione");
			//per foto non so come fare 
			Post p =new Post(idpost,iduutente,idgruuppo,desc,data_pubblicazione,ora_pubblicazione);
			posts.add(p);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return posts;
}}
