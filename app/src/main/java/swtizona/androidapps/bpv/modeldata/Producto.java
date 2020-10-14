package swtizona.androidapps.bpv.modeldata;

public class Producto {

    private String auto, modelo, marca, nserie, comentario;

    public Producto(String auto, String modelo, String marca, String nserie, String comentario) {
        this.auto = auto;
        this.modelo = modelo;
        this.marca = marca;
        this.nserie = nserie;
        this.comentario = comentario;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
