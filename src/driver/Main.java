package driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DbConnection;
import controller.Controller;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DbConnection dbconn = null;
		Connection connection = null;
		try {
			dbconn = DbConnection.getDBConnection();
			connection = dbconn.getConnection();

			Controller c = new Controller(connection);

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}


