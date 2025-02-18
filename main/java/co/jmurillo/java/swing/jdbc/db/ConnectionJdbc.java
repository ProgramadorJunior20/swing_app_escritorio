package co.jmurillo.java.swing.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJdbc {

    private static String url = "jdbc:mysql://localhost:3306/db";
    private static String user = "root";
    private static String pass = "pass";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
