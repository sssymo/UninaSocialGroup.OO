package classiDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import classi.*;

public class UserDao {
    private static Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }
    public int getUserIdByUsername(String username,String password) throws SQLException {
        String query = "SELECT idutente FROM utente WHERE nickname = ? and password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idUtente");
                } else {
                    throw new SQLException("Utente non trovato");
                }
            }
        }
    }

    public static String getUserNameById(int currentUser) {
        String query = "SELECT nickname FROM utente WHERE idutente=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, currentUser);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                } else {
                    return ("Utente non trovato");
                }
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
        }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
    }
    }
    
    public boolean CheckUserAlreadyExistDuringRegistration(String username) {
    	String query = "SELECT * FROM utente WHERE nickname = ?";
    	 try (PreparedStatement statement = connection.prepareStatement(query)) {
             statement.setString(1, username);
             try (ResultSet resultSet = statement.executeQuery()) {
                 return resultSet.next(); // Se c'è almeno una riga corrispondente, l'autenticazione è avvenuta con successo
             }
         } catch (SQLException e) {
             e.printStackTrace();
             return false; // Gestione dell'eccezione: ritorna false in caso di errore di accesso al database
         }
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
            return false; 
        }
    }
 // Metodo per salvare un utente nel database 
    public void salvaUtente(Utente utente) throws SQLException {
        String query = "INSERT INTO utente (nickname,  password, bio ) " +
                       "VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, utente.getUsername());
           
            stmt.setString(2, utente.getPassword());
            stmt.setString(3, utente.getBio()); 
            //stmt.setInt(5, utente.getNumPubblicazioni());
            //stmt.setInt(6, utente.getNumGruppiPartecipanti());
            stmt.executeUpdate();
            System.out.println("Utente salvato nel database: " + utente.getUsername());
        }
    }

    // Metodo per caricare un utente dal database tramite ID
    public Utente caricaUtente(int idUtente) throws SQLException {
        String query = "SELECT * FROM Utenti WHERE idUtente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String bio = rs.getString("bio");
                    int numPubblicazioni = rs.getInt("numPubblicazioni");
                    int numGruppiPartecipanti = rs.getInt("numGruppiPartecipanti");
                    return new Utente(username, idUtente, password, bio);
                } else {
                    System.out.println("Nessun utente trovato nel database con ID: " + idUtente);
                    return null;
                }
            }
        }
    }

    public void aggiornaUtente(Utente utente) throws SQLException {
        String query = "UPDATE Utenti SET username = ?, password = ?, bio = ?, numPubblicazioni = ?, numGruppiPartecipanti = ? " +
                       "WHERE idUtente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, utente.getUsername());
            stmt.setString(2, utente.getPassword());
            stmt.setString(3, utente.getBio());
            stmt.setInt(4, utente.getNumPubblicazioni());
            stmt.setInt(5, utente.getNumGruppiPartecipanti());
            stmt.setInt(6, utente.getIdUtente());
            stmt.executeUpdate();
            System.out.println("Utente aggiornato nel database: " + utente.getUsername());
        }
    }

    // Metodo per eliminare un utente dal database tramite ID
    public void eliminaUtente(int idUtente) throws SQLException {
        String query = "DELETE FROM Utenti WHERE idUtente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUtente);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Utente eliminato dal database con ID: " + idUtente);
            } else {
                System.out.println("Impossibile eliminare l'utente dal database. Utente non trovato.");
            }
        }
    }

}