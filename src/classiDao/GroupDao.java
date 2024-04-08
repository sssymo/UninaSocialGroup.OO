package classiDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classi.gruppo;
public class GroupDao {
    private Connection connection;
    
    private static final String INSERT_GRUPPO = "INSERT INTO gruppo (nome_gruppo, data_creazione , descrizione_gruppo) VALUES ( ?, ?, ?)";
    public GroupDao(Connection connection) {
        this.connection = connection;
    }

    public List<String> getGroupsByUser(int currentUser) throws SQLException {
        ArrayList<String> groups = new ArrayList<>();
        String query = "SELECT g.nome_gruppo " +
                       "FROM gruppo g " +
                       "JOIN iscrizione i ON g.idgruppo = i.idgruppo " +
                       "JOIN utente U ON i.idutente = U.idutente " +
                       "WHERE U.nickname = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, currentUser);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String groupName = resultSet.getString("nomeGruppo");
                
                groups.add(groupName);
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
                    if (idgruppo.next()) {  // Spostarsi alla prima riga
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

    public List<String> getGroupsRequestedByUser(int userId) {
        ArrayList<String> groups = new ArrayList<>();
        String query = "SELECT gruppo.nome_gruppo " +
                       "FROM richiesta " +
                       "INNER JOIN gruppo ON gruppo.idgruppo = richiesta.idgruppo " +
                       "WHERE richiesta.idrichiedente = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String groupName = resultSet.getString("nome_gruppo");
                groups.add(groupName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }
}
