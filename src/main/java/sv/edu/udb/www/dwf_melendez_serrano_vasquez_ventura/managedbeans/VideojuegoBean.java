package sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.managedbeans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models.Videojuego;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.dao.VideojuegoDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class VideojuegoBean {
    private final VideojuegoDao videojuegoDao = new VideojuegoDao();
    private Videojuego nuevoVideojuego = new Videojuego();
    private String id;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        id = params.get("id");
        if (id != null && !id.isEmpty()) {
            cargarVideojuego();
        }
    }

    public void cargarVideojuego() {
        try {
            Videojuego videojuego = videojuegoDao.obtenerVideojuego(id);
            if (videojuego != null) {
                nuevoVideojuego = videojuego;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String cargarVideojuego(String idVideojuego) {
        try {
            Videojuego videojuego = videojuegoDao.obtenerVideojuego(idVideojuego);
            return videojuego != null ? videojuego.getNombre() : "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al obtener el nombre del videojuego";
        }
    }

    public String insertarVideojuego() {
        try {
            if (videojuegoDao.videojuegoExistente(nuevoVideojuego.getNombre())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El videojuego con ese nombre ya existe."));
                return null;
            }
            videojuegoDao.insertarVideojuego(nuevoVideojuego);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Videojuego agregado exitosamente."));
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un problema al agregar el videojuego."));
        }
        return "index";
    }

    public String editarVideojuego() {
        try {
            videojuegoDao.editarVideojuego(nuevoVideojuego);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "index";
    }


    public String eliminarVideojuego() {
        try {
            videojuegoDao.eliminarVideojuego(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "index";
    }

    public List<Videojuego> getVideojuego() {
        return videojuegoDao.listarVideojuegos();
    }

    public Videojuego getNuevoVideojuego() {
        return nuevoVideojuego;
    }

    public void setNuevoVideojuego(Videojuego nuevoVideojuego) {
        this.nuevoVideojuego = nuevoVideojuego;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}