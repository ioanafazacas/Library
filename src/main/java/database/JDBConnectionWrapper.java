package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnectionWrapper {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String USER = "root";
    private static final String PASSWORD = "MySQL3002ioana*";
    private static final int TIMEOUT = 5;
    private Connection connection;
    public JDBConnectionWrapper(String schema){
        //schema ii library sau testlibrary
        // folosim reflection
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + schema , USER, PASSWORD);
            createTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void createTables() throws SQLException{
        Statement statement= connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS book("+
                " id bigint NOT NULL AUTO_INCREMENT,"+
                " author VARCHAR(500) NOT NULL,"+
                " title VARCHAR(500) NOT NULL," +
                " publishedDate datetime DEFAULT NULL,"+
                " PRIMARY KEY(id), "+
                " UNIQUE KEY id_UNIQUE(id)"+
                ") ENGINE= InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8; ";
        statement.execute(sql);

        String sql2 = "CREATE TABLE IF NOT EXISTS book_order("+
                " id bigint NOT NULL AUTO_INCREMENT,"+
                " author VARCHAR(500) NOT NULL,"+
                " title VARCHAR(500) NOT NULL," +
                " saleDate datetime DEFAULT NULL,"+
                " quantity int DEFAULT 0,"+
                " price float DEFAULT 0,"+
                " PRIMARY KEY(id), "+
                " UNIQUE KEY id_UNIQUE(id)"+
                ") ENGINE= InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8; ";
        statement.execute(sql2);
    }

    public boolean testConnecion() throws SQLException{
        return connection.isValid(TIMEOUT);
    }
    public Connection getConnection(){
        return connection;
    }
}
