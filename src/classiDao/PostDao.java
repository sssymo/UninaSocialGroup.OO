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
	
}

// Query to retrieve the post with the highest number of likes
final static String GET_POST_WITH_MAX_LIKES = "SELECT * FROM post  WHERE idgruppo = ? ORDER BY num_like DESC LIMIT 1";

public Post getPostWithMaxLikes() {
	try (PreparedStatement stmt = conn.prepareStatement(GET_POST_WITH_MAX_LIKES)) {
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int idpost = rs.getInt("idpost");
			int idutente = rs.getInt("idutente");
			int idgruppo = rs.getInt("idgruppo");
			String descrizione = rs.getString("descrizione");
			Date data_pubblicazione = rs.getDate("data_pubblicazione");
			Time orario_pubblicazione = rs.getTime("orario_pubblicazione");
			int numero_like = rs.getInt("num_like");
			int numero_commenti = rs.getInt("num_commenti"); 
			return new Post(idpost, idutente, idgruppo, descrizione, data_pubblicazione, orario_pubblicazione, numero_like, numero_commenti);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

// Query to retrieve the post with the highest number of comments
final static String GET_POST_WITH_MAX_COMMENTS = "SELECT * FROM post  WHERE idgruppo = ? ORDER BY num_commenti DESC LIMIT 1";

public static int getPostWithMaxComments(int idg) {
	try (PreparedStatement stmt = conn.prepareStatement(GET_POST_WITH_MAX_COMMENTS)) {
		stmt.setInt(1, idg);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			int idpost = rs.getInt("idpost");
			int idutente = rs.getInt("idutente");
			int idgruppo = rs.getInt("idgruppo");
			String descrizione = rs.getString("descrizione");
			Date data_pubblicazione = rs.getDate("data_pubblicazione");
			Time orario_pubblicazione = rs.getTime("orario_pubblicazione");
			//return new Post(idpost, idutente, idgruppo, descrizione, data_pubblicazione, orario_pubblicazione);
		return rs.getInt("num_commenti");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
}


// Query to retrieve the post with the lowest number of likes
final static String GET_POST_WITH_MIN_LIKES = "SELECT * FROM post  WHERE idgruppo = ? ORDER BY num_like ASC LIMIT 1";
public static int getPostWithMinLikes(int idg) {
	try (PreparedStatement stmt = conn.prepareStatement(GET_POST_WITH_MIN_LIKES)) {
		stmt.setInt(1, idg);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int idpost = rs.getInt("idpost");
			int idutente = rs.getInt("idutente");
			int idgruppo = rs.getInt("idgruppo");
			String descrizione = rs.getString("descrizione");
			Date data_pubblicazione = rs.getDate("data_pubblicazione");
			Time orario_pubblicazione = rs.getTime("orario_pubblicazione");
			int numero_like = rs.getInt("num_like");
			return numero_like;
		//	int numero_commenti = rs.getInt("num_commenti"); 
			//return new Post(idpost, idutente, idgruppo, descrizione, data_pubblicazione, orario_pubblicazione, numero_like, numero_commenti);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return  0;
}

// Query to retrieve the post with the lowest number of comments
final static String GET_POST_WITH_MIN_COMMENTS = "SELECT * FROM post WHERE idgruppo = ? ORDER BY num_commenti ASC LIMIT 1";
public static int getPostWithMinComments(int idgruppo) {
	try (PreparedStatement stmt = conn.prepareStatement(GET_POST_WITH_MIN_COMMENTS)) {
		stmt.setInt(1, idgruppo);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int idpost = rs.getInt("idpost");
			int idutente = rs.getInt("idutente");
			String descrizione = rs.getString("descrizione");
			Date data_pubblicazione = rs.getDate("data_pubblicazione");
		Time orario_pubblicazione = rs.getTime("orario_pubblicazione");
		return rs.getInt("num_commenti");
	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return 0;
  }

public static int getMaxLikesByGroupCreationDate(int idGruppo, Date dataCreazione) {
	try (PreparedStatement stmt = conn.prepareStatement(GET_POST_WITH_MAX_LIKES)) {
		stmt.setInt(1,idGruppo);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
	
			return  rs.getInt("num_like");
}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
}
}