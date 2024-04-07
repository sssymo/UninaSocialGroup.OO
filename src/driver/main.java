package driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import database.DbConnection;
import controller.Controller;

			
public class main{

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


