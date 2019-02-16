package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider
{
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException
    {
        return getDSInstance().getConnection();
    }

    public static DataSource getDSInstance()
    {
        if (dataSource == null)
        {
            try
            {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/todo_app");
            } catch (NamingException e)
            {
                e.printStackTrace();
            }
        }

        return dataSource;
    }
}