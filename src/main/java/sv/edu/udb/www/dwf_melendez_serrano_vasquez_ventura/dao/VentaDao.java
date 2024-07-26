package sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.dao;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.database.DatabaseConnection;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models.Venta;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDao {
    private static final String INSERTAR_VENTA_SQL = "INSERT INTO venta (videojuego_id, fecha, cantidad) VALUES (?, ?, ?)";
    private static final String LISTAR_VIDEOJUEGOS_SQL = "SELECT * FROM venta";
    private static final String OBTENER_VENTA_POR_ID_SQL = "SELECT * FROM venta WHERE id = ?";
    private static final String ACTUALIZAR_VENTA_SQL = "UPDATE venta SET videojuego_id = ?, fecha = ?, cantidad = ? WHERE id = ?";
    private static final String ELIMINAR_VENTA_SQL = "DELETE FROM venta WHERE id = ?";

    public List<Venta> listarVentas() {
        List<Venta> ventas = new ArrayList<>();
        try (PreparedStatement stmtVentas = DatabaseConnection.getConnection().prepareStatement(LISTAR_VIDEOJUEGOS_SQL)) {
            ResultSet rs = stmtVentas.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getString("id"));
                venta.setIdVideojuego(rs.getString("videojuego_id"));
                venta.setFecha(rs.getString("fecha"));
                venta.setCantidad(rs.getInt("Cantidad"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las ventas: " + e.getMessage());
        }
        return ventas;
    }

    public Venta obtenerVenta(String id) throws SQLException {
        Venta venta = null;
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(OBTENER_VENTA_POR_ID_SQL)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                venta = new Venta();
                venta.setId(rs.getString("id"));
                venta.setIdVideojuego(rs.getString("videojuego_id"));
                venta.setFecha(rs.getString("fecha"));
                venta.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la venta por ID: " + e.getMessage());
            throw e;
        }
        return venta;
    }

    public void insertarVenta(Venta venta) throws SQLException {
        try (PreparedStatement stmtVenta = DatabaseConnection.getConnection().prepareStatement(INSERTAR_VENTA_SQL)) {
            stmtVenta.setString(1, venta.getIdVideojuego());
            stmtVenta.setString(2, venta.getFecha());
            stmtVenta.setInt(3, venta.getCantidad());
            stmtVenta.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar la venta: " + e.getMessage());
            throw e;
        }
    }

    public void editarVenta(Venta venta) throws SQLException {
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(ACTUALIZAR_VENTA_SQL)) {

            System.out.println("Actualizando venta con ID: " + venta.getId());
            System.out.println("ID videojuego: " + venta.getIdVideojuego());
            System.out.println("Nueva fecha: " + venta.getFecha());
            System.out.println("Nueva cantidad: " + venta.getCantidad());

            stmt.setString(1, venta.getIdVideojuego());
            stmt.setString(2, venta.getFecha());
            stmt.setInt(3, venta.getCantidad());
            stmt.setString(4, venta.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar venta: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarVenta(String id) throws SQLException{
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(ELIMINAR_VENTA_SQL)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar videojuego: " + e.getMessage());
            throw e;
        }
    }
}
