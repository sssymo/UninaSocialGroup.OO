package classiDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "SELECT * FROM Notifiche WHERE idUtente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notifica notifica = new notifica(
                            rs.getInt("idNotifica"),
                            rs.getInt("idUtente"),
                            rs.getString("testo")
                    );
                    notifiche.add(notifica);
                }
            }
        }
        return notifiche;
    }
}