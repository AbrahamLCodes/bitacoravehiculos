package swtizona.androidapps.bpv.modeldata;

public class Servicio {

    private String servicio, automovil, fecha, taller, productos, comentario;

    public Servicio(String servicio, String automovil, String fecha, String taller, String productos, String comentario) {
        this.servicio = servicio;
        this.automovil = automovil;
        this.fecha = fecha;
        this.taller = taller;
        this.productos = productos;
        this.comentario = comentario;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getAutomovil() {
        return automovil;
    }

    public void setAutomovil(String automovil) {
        this.automovil = automovil;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
