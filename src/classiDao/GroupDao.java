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

    public List<String> getGroupsByUser(String userId) throws SQLException {
        List<String> groups = new ArrayList<>();
        String query = "SELECT g.nomeGruppo " +
                       "FROM gruppo g " +
                       "JOIN iscritto i ON g.idGruppo = i.idGruppo " +
                       "JOIN utente U ON i.idUtente = U.idUtente " +
                       "WHERE U.nickname = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String groupName = resultSet.getString("nomeGruppo");
                groups.add(groupName);
            }
        }

        return groups;
    }
    public List<gruppo> searchGroupByName( String groupName) throws SQLException {
    	ArrayList<gruppo> groups = new ArrayList<>();
    	String query= "SELECT * FROM gruppo WHERE nome_gruppo LIKE ?";
    	try(PreparedStatement stmt= connection.prepareStatement(query)){
    		stmt.setString(1, "%" + groupName + "%");
    		ResultSet res = stmt.executeQuery();
    		while(res.next()) {
    			String id=res.getString(1);
    			String nome= res.getString(2);
    			String desc=res.getString(3);
    			Date data=res.getDate(4);
    			//1 andrà sostituito con un valore null quando il db avrà una pk che sarà autogenerata
    			gruppo g = new gruppo(nome,id,desc,data);
    			groups.add(g);
    		}
    	}
    	return groups; 
    }
}
