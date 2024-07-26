package sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.managedbeans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models.Venta;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.dao.VentaDao;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.dao.VideojuegoDao;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models.Videojuego;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class VentaBean {
    private final VideojuegoDao videojuegoDao = new VideojuegoDao();
    private final VentaDao ventaDao = new VentaDao();
    private Venta nuevaVenta = new Venta();
    private List<Videojuego> videojuegos;
    private String id;

    @PostConstruct
    public void init() {
        cargarVideojuegos();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        id = params.get("id");
        if (id != null && !id.isEmpty()) {
            cargarVenta();
        }
    }

    public void cargarVideojuegos() {
        videojuegos = videojuegoDao.listarVideojuegos();
    }

    public void cargarVenta() {
        try {
            Venta venta = ventaDao.obtenerVenta(id);
            if (venta != null) {
                nuevaVenta = venta;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String insertarVenta() {
        try {
            ventaDao.insertarVenta(nuevaVenta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "index";
    }

    public String eliminarVenta() {
        try {
            ventaDao.eliminarVenta(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "index";
    }

    public String editarVenta() {
        try {
            ventaDao.editarVenta(nuevaVenta);
            return "index";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Venta> getVenta() {
        return ventaDao.listarVentas();
    }

    public Venta getNuevaVenta() {
        return nuevaVenta;
    }

    public void setNuevaVenta(Venta nuevaVenta) {
        this.nuevaVenta = nuevaVenta;
    }

    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}