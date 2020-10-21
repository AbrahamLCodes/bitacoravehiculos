package swtizona.androidapps.bpv.modeldata;

public class Taller {

    private String taller, calle, ncalle, colonia, ciudad, estado, telefono, comentario;

    /*

        Alfredo Armendariz
        Aldama 112 Col. Centro
        6481227000

    */

    public Taller(String taller,  String telefono, String calle, String ncalle, String colonia, String ciudad
            , String estado, String comentario) {
        this.taller = taller;
        this.telefono = telefono;
        this.calle = calle;
        this.ncalle = ncalle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.comentario = comentario;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNcalle() {
        return ncalle;
    }

    public void setNcalle(String ncalle) {
        this.ncalle = ncalle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
