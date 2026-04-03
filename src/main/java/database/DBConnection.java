package database;

import java.sql.*;

public class DBConnection {
    private static final String connectionString = "jdbc:mysql://localhost:3306/herogame";
    private static final String user = "root";
    private static final String password = "";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn;

    // Singleton pattern
    private static DBConnection _instance;

    private DBConnection(){
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(connectionString, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Singleton pattern
    public static DBConnection getInstance(){
        if (_instance == null){
            _instance = new DBConnection();
        }
        return _instance;
    }

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(connectionString, user, password);
        } catch (Exception e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
            return null;
        }
    }

    public Statement getStatement() throws Exception{
        return conn.createStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) throws Exception{
        return conn.prepareStatement(sql);
    }
}