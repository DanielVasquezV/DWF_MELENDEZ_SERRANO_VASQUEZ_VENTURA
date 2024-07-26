package sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.dao.VideojuegoDao;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.dao.VentaDao;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models.Videojuego;
import sv.edu.udb.www.dwf_melendez_serrano_vasquez_ventura.models.Venta;

import java.sql.SQLException;

@Path("/obtener")

public class RestService {
    private final VideojuegoDao videojuegoDao = new VideojuegoDao();
    private final VentaDao ventaDao = new VentaDao();

    @GET
    @Path("/videojuegos/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response obtenerVideojuego(@PathParam("id") String id) {
        try {
            Videojuego videojuego = videojuegoDao.obtenerVideojuego(id);
            if (videojuego != null) {
                return Response.ok(videojuego).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Videojuego no encontrado\"}").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al consultar el videojuego\"}").build();
        }
    }

    @GET
    @Path("/ventas/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response ObtenerVenta(@PathParam("id") String id) {
        try {
            Venta venta = ventaDao.obtenerVenta(id);
            if (venta != null) {
                return Response.ok(venta).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Venta no encontrado\"}").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al consultar la venta\"}").build();
        }
    }
}
