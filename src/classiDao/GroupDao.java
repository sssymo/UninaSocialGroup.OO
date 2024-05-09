package classiDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classi.gruppo;
public class GroupDao {
    private static Connection connection;
    
    private static final String INSERT_GRUPPO = "INSERT INTO gruppo (nome_gruppo, data_creazione , descrizione_gruppo) VALUES ( ?, ?, ?)";
    public GroupDao(Connection connection) {
        this.connection = connection;
    }

    //funziona
    public static boolean AcceptUtenteAGruppo(int idutente,int idgruppo) {
    	try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO iscrizione (idutente,idgruppo) VALUES (?,?)")){
    		stmt.setInt(1, idutente);
    		stmt.setInt(2, idgruppo);
    		stmt.executeUpdate();
    		//successivamente elimino la richiesta
    		try(PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM richiesta WHERE idrichiedente=? AND idgruppo=?")){
        		stmt2.setInt(1, idutente);
        		stmt2.setInt(2, idgruppo);
        		stmt2.executeUpdate();
    		}
    		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;			
    }
    public static String GetCreatoreFromIdGruppo(int idgruppo) {
    	String query="SELECT utente.nickname from crea,utente where utente.idutente=crea.idutente and crea.idgruppo=?";
    	try (PreparedStatement stmt = connection.prepareStatement(query)){
    		stmt.setInt(1, idgruppo);
    		ResultSet s = stmt.executeQuery();
    		while(s.next()) {
    			return s.getString(1);
    		}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
    	}
    	return "nessun utente trovato";
		
    }
    
    public static String GetGroupNameFromId(int id) {
    	
    	try (PreparedStatement statement = connection.prepareStatement("SELECT nome_gruppo FROM gruppo WHERE idgruppo=?")) {
    		statement.setInt(1, id);
    		ResultSet r=statement.executeQuery();
    		while(r.next())
    		 return r.getString(1);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "error";
    }
    
    public static gruppo GetGroupDataFromId(int id) {
    	
    	try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM gruppo WHERE idgruppo=?")) {
    		statement.setInt(1, id);
    		ResultSet r=statement.executeQuery();
    		while(r.next()) {
    			 gruppo g=new gruppo(r.getString(2),r.getInt(1),r.getString(4),r.getDate(3));
    		return g;
    		} 
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public List<gruppo> getGroupsByUser(int currentUser) throws SQLException {
        ArrayList<gruppo> groups = new ArrayList<>();
        String query = "SELECT g.nome_gruppo, g.idgruppo, g.data_creazione, g.descrizione_gruppo "
                + "FROM gruppo AS g, iscrizione AS i, utente AS u "
                + "WHERE g.idgruppo = i.idgruppo "
                + "AND i.idutente = u.idutente "
                + "AND u.idutente = ?";


        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, currentUser);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
    			int id=resultSet.getInt(2);
    			String nome= resultSet.getString(1);
    			String desc=resultSet.getString(4);
    			Date data=resultSet.getDate(3);
    			gruppo g = new gruppo(nome,id,desc,data);
                groups.add(g);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
    public List<gruppo> searchGroupByName( String groupName) throws SQLException {
    	ArrayList<gruppo> groups = new ArrayList<>();
    	String query= "SELECT * FROM gruppo WHERE nome_gruppo LIKE ?";
    	try(PreparedStatement stmt= connection.prepareStatement(query)){
    		stmt.setString(1,groupName + "%");
    		ResultSet res = stmt.executeQuery();
    		while(res.next()) {
    			int id=res.getInt(1);
    			String nome= res.getString(2);
    			String desc=res.getString(4);
    			Date data=res.getDate(3);
    			
    			gruppo g = new gruppo(nome,id,desc,data);
    			groups.add(g);
    		}
    	}
    	return groups; 
    }
    
  private final String GET_ID_DEL_GRUPPO ="SELECT MAX(idgruppo) FROM gruppo ";
  private final String INSERT_IN_ISCRITTO ="INSERT INTO iscrizione (idutente,idgruppo ) VALUES ( ?, ?)";
  private static final String INSERT_IN_CREA = "INSERT INTO crea (idutente,idgruppo ) VALUES ( ?, ?)";
  
    //metodo per creazione gruppo
    public boolean CreateGroup(String nome_gruppo,String desc,int currentUser) throws SQLException{
    	
    	try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GRUPPO)) {
            //funziona
            preparedStatement.setString(1, nome_gruppo);
            preparedStatement.setString(3, desc);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(2, currentTime);
            preparedStatement.executeUpdate();
            try (PreparedStatement p2 = connection.prepareStatement(GET_ID_DEL_GRUPPO)) {
                try (ResultSet idgruppo = p2.executeQuery()) {
                    if (idgruppo.next()) {  
                        int id = idgruppo.getInt(1);

                        try (PreparedStatement p3 = connection.prepareStatement(INSERT_IN_CREA)) {
                            p3.setInt(1, currentUser);
                            p3.setInt(2, id);
                            p3.executeUpdate();
                            //successivamente inserisco anche in "iscritto" 
                            //in maniera che risulti tra quelli iscritti al suo gruppo
                            try(PreparedStatement p4=connection.prepareStatement(INSERT_IN_ISCRITTO)){
                                p4.setInt(1, currentUser);
                                p4.setInt(2, id);
                                p4.executeUpdate();
                            }
                        }
                    } else {
                        // Se ResultSet non ha restituito alcuna riga
                        System.out.println("Nessun ID gruppo restituito.");
                        return false;
                    }

                }
                
            }
            return true;
        }
    	
    	
    
    }

    public List<gruppo> getGroupsRequestedByUser(int userId) {
        ArrayList<gruppo> groups = new ArrayList<>();
        String query = "SELECT gruppo.nome_gruppo,gruppo.idgruppo,gruppo.data_creazione,gruppo.descrizione_gruppo " +
                       "FROM richiesta " +
                       "INNER JOIN gruppo ON gruppo.idgruppo = richiesta.idgruppo " +
                       "WHERE richiesta.idrichiedente = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
    			int id=resultSet.getInt(2);
    			String nome= resultSet.getString(1);
    			String desc=resultSet.getString(4);
    			Date data=resultSet.getDate(3);
    			gruppo g = new gruppo(nome,id,desc,data);
    			groups.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }

	public static boolean LasciaGruppo(int currentUser, int idGruppo) {
		try (PreparedStatement statement = connection.prepareStatement("DELETE FROM ISCRIZIONE WHERE idutente=? and idgruppo=?")){
			statement.setInt(1, currentUser);
			statement.setInt(2, idGruppo);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
