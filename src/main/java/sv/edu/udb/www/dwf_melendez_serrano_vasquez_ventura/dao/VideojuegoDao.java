package sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.dao;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.database.DatabaseConnection;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models.Videojuego;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDao {
    private static final String INSERTAR_VIDEOJUEGO_SQL = "INSERT INTO videojuego (nombre, genero, precio) VALUES (?, ?, ?)";
    private static final String LISTAR_VIDEOJUEGOS_SQL = "SELECT * FROM videojuego";
    private static final String ACTUALIZAR_VIDEOJUEGO_SQL = "UPDATE videojuego SET nombre = ?, genero = ?, precio = ? WHERE id = ?";
    private static final String OBTENER_VIDEOJUEGO_POR_ID_SQL = "SELECT * FROM videojuego WHERE id = ?";
    private static final String ELIMINAR_VIDEOJUEGO_SQL = "DELETE FROM videojuego WHERE id = ?";
    private static final String VIDEOJUEGO_EXISTENTE_SQL = "SELECT COUNT(*) FROM videojuego WHERE nombre = ?";


    public List<Videojuego> listarVideojuegos() {
        List<Videojuego> videojuegos = new ArrayList<>();
        try (PreparedStatement stmtVideojuegos = DatabaseConnection.getConnection().prepareStatement(LISTAR_VIDEOJUEGOS_SQL)) {
            ResultSet rs = stmtVideojuegos.executeQuery();
            while (rs.next()) {
                Videojuego videojuego = new Videojuego();
                videojuego.setId(rs.getString("id"));
                videojuego.setNombre(rs.getString("nombre"));
                videojuego.setGenero(rs.getString("genero"));
                videojuego.setPrecio(rs.getDouble("precio"));
                videojuegos.add(videojuego);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar videojuegos: " + e.getMessage());
        }
        return videojuegos;
    }
    public void insertarVideojuego(Videojuego videojuego) throws SQLException {
        try (PreparedStatement stmtVideojuego = DatabaseConnection.getConnection().prepareStatement(INSERTAR_VIDEOJUEGO_SQL)) {
            stmtVideojuego.setString(1, videojuego.getNombre());
            stmtVideojuego.setString(2, videojuego.getGenero());
            stmtVideojuego.setDouble(3, videojuego.getPrecio());
            stmtVideojuego.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar videojuego: " + e.getMessage());
            throw e;
        }
    }
    public Videojuego obtenerVideojuego(String id) throws SQLException {
        Videojuego videojuego = null;
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(OBTENER_VIDEOJUEGO_POR_ID_SQL)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                videojuego = new Videojuego();
                videojuego.setId(rs.getString("id"));
                videojuego.setNombre(rs.getString("nombre"));
                videojuego.setGenero(rs.getString("genero"));
                videojuego.setPrecio(rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener videojuego por ID: " + e.getMessage());
            throw e;
        }
        return videojuego;
    }

    public boolean videojuegoExistente(String nombre) throws SQLException {
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(VIDEOJUEGO_EXISTENTE_SQL)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar si el ya videojuego existe: " + e.getMessage());
            throw e;
        }
        return false;
    }

    public void editarVideojuego(Videojuego videojuego) throws SQLException {
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(ACTUALIZAR_VIDEOJUEGO_SQL)) {
            stmt.setString(1, videojuego.getNombre());
            stmt.setString(2, videojuego.getGenero());
            stmt.setDouble(3, videojuego.getPrecio());
            stmt.setString(4, videojuego.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar videojuego: " + e.getMessage());
            throw e;
        }
    }


    public void eliminarVideojuego(String id) throws SQLException{
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(ELIMINAR_VIDEOJUEGO_SQL)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar videojuego: " + e.getMessage());
            throw e;
        }
    }
}