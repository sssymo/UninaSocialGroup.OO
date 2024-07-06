package driver;

import java.sql.Connection;
import java.sql.SQLException;

import database.DbConnection;
import controller.Controller;

			
public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		try {
			
			connection = DbConnection.getConnection();

			Controller c = new Controller(connection);
            c.showLoginInterface();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}


