package swtizona.androidapps.bpv.modeldata;

public class Taller {

    private String taller, calle, ncalle, colonia, telefono, comentario;

    public Taller(String taller, String calle, String ncalle, String colonia
            , String telefono, String comentario) {
        this.taller = taller;
        this.calle = calle;
        this.ncalle = ncalle;
        this.colonia = colonia;
        this.telefono = telefono;
        this.comentario = comentario;
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
