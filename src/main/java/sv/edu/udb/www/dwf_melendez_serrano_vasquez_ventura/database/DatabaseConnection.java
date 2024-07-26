package sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.database;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/desafio_1?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado", e);
        }
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    // Métodos para ejecutar consultas INSERT, DELETE, UPDATE y SELECT
    public static void executeQuery(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        closeConnection(connection);
    }

    //Obtener los resultados de las Query's con RESULT-SET
    public static ResultSet executeQueryWithResultSet(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

}
