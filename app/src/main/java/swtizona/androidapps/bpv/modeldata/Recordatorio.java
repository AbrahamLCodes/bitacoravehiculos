package swtizona.androidapps.bpv.modeldata;

public class Recordatorio {

    private int id;
    private String automovil, fecha, hora;

    public Recordatorio(int id, String automovil, String fecha, String hora) {
        this.id = id;
        this.automovil = automovil;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
