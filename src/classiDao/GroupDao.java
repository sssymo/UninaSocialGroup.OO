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
                       "FROM gruppi g " +
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
      /*
       * da aggiungere metodo che cerca gruppo per nome
       * */
       */
    }
}
