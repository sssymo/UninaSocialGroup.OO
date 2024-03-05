package driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DbConnection;
import controller.Controller;
<<<<<<< HEAD
public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DbConnection dbconn = null;
		Connection connection = null;
		try {
			dbconn = DbConnection.getDBConnection();
			connection = dbconn.getConnection();

			Controller c = new Controller(connection);
			
=======

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DbConnection dbconn = null;
		Connection connection = null;
		try {
			dbconn = DbConnection.getDBConnection();
			connection = dbconn.getConnection();

			Controller c = new Controller(connection);
>>>>>>> branch 'main' of https://github.com/sssymo/UninaSocialGroup.OO.git

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}


