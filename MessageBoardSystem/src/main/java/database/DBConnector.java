package database;

import configuration.ConfigDriver;

import java.sql.*;

public class DBConnector {
    public static Connection getConnection() {
        String JDBC_DRIVER = ConfigDriver.getJDBCDriver();
        String DB_URL = ConfigDriver.getDBURL();
        String DB_NAME = ConfigDriver.getDBName();
        String DB_USER = ConfigDriver.getDBUser();
        String DB_PASSWORD = ConfigDriver.getDBPassword();

        try{
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database",e);
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Error Class Not Found",e);
        }
    }

    public static void releaseConnection(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ignored) { }
            rs = null;
        }

        releaseConnection(conn, stmt);
    }

    public static void releaseConnection(Connection conn, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ignored) { }
            stmt = null;
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) { }
            conn = null;
        }
    }

    public static void releaseConnection(Connection conn, PreparedStatement preparedStmt) {
        if (preparedStmt != null) {
            try {
                preparedStmt.close();
            } catch (SQLException ignored) { }
            preparedStmt = null;
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) { }
            conn = null;
        }
    }
}
