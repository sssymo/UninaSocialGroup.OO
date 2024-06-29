package database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DbConnection
{
    private static DbConnection dbcon = null;

    private static Connection conn = null;

    private DbConnection(){}

    public static DbConnection getDBConnection()
    {  
        if (dbcon == null) {
            dbcon = new DbConnection();
        }
       
        return dbcon;
    }
 
    public static Connection getConnection() throws SQLException 
    {
        String pwd = null;
        BufferedReader b = null;
        try
        {   
            if(conn==null || conn.isClosed())
            {   
                pwd = "";
                
                Class.forName("org.postgresql.Driver");
                
                
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UninaSocialNetwork", "postgres", pwd);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }
}
