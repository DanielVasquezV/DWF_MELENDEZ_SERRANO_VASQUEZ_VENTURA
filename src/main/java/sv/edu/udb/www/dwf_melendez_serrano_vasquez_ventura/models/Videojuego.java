package sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models;

public class Videojuego {
    private String id;
    private String nombre;
    private String genero;
    private Double precio;

    public Videojuego() {
    }

    public Videojuego(String id, String nombre, String genero, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public Double getPrecio() {
        return precio;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    };

    public void setPrecio(Double precio) {
        this.precio = precio;
    };
}
