package classiDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao {
    private Connection connection;

    public NotificationDao(Connection connection) {
        this.connection = connection;
    }

    public List<String> getAllUserNotifications(int userId) throws SQLException {
        List<String> userNotifications = new ArrayList<>();
        String query = "SELECT message FROM notifications WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userNotifications.add(resultSet.getString("message"));
            }
        }
        return userNotifications;
    }
}