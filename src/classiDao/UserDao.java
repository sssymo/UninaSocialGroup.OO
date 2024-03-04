package classiDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public boolean authenticateUser(String username, String password) throws SQLException{
        String query = "SELECT * FROM utente WHERE nickname = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Se c'è almeno una riga corrispondente, l'autenticazione è avvenuta con successo
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Gestione dell'eccezione: ritorna false in caso di errore di accesso al database
        }
    }
}

