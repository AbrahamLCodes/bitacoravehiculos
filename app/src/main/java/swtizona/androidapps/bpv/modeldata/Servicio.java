package swtizona.androidapps.bpv.modeldata;

public class Servicio {

    private String servicio, automovil, dia, mes, anio, taller, productos, comentario;

    public Servicio(String servicio, String automovil, String dia, String mes, String anio, String taller, String productos, String comentario) {
        this.servicio = servicio;
        this.automovil = automovil;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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
