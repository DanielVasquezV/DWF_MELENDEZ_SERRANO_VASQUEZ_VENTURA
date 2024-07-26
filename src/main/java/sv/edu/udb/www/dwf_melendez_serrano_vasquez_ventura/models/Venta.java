package sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models;

public class Venta {
    private String id;
    private String idVideojuego;
    private String fecha;
    private int cantidad;

    public Venta() {
    }

    public Venta(String id, String idVideojuego, String fecha, int cantidad) {
        this.id = id;
        this.idVideojuego = idVideojuego;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(String idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
