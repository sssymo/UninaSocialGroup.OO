package database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DbConnection
{
    // istanza statica e privata di questa classe
    private static DbConnection dbcon = null;
    // istanza privata della connessione ad SQL
    private Connection conn = null;

    // costruttore private
    private DbConnection(){}

    // metodo pubblico per ottenere una istanza della classe privata
    public static DbConnection getDBConnection()
    {   // se la classe connessione è nulla, la crea
        if (dbcon == null) {
            dbcon = new DbConnection();
        }
        // e la restituisce
        return dbcon;
    }

    // metodo pubblico per ottenere la connessione
    public Connection getConnection() throws SQLException 
    {
        String pwd = null;
        BufferedReader b = null;
        try
        {   // se la connessione non esiste oppure è stata chiusa
            if(conn==null || conn.isClosed())
            {   //legge la pwd dal file
                pwd = "Luxifer483";
                // registra il driver
                Class.forName("org.postgresql.Driver");
                // chiama il DriverManager e chiedi la connessione
                //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", pwd); // generico schema postgres
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UninaSocialNetwork", "postgres", pwd);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }
}

