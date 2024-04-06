package classiDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classi.gruppo;
public class GroupDao {
    private Connection connection;

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
    			//1 andrà sostituito con un valore null quando il db avrà una pk che sarà autogenerata
    			gruppo g = new gruppo(nome,id,desc,data);
    			groups.add(g);
    		}
    	}
    	return groups; 
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
