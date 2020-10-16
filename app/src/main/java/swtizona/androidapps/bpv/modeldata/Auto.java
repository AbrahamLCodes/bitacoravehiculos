package swtizona.androidapps.bpv.modeldata;

public class Auto {

    private String fabricante, modelo, ano, motor, matricula, comentario;

    public Auto(String fabricante, String modelo, String ano, String motor, String matricula
            , String comentario) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.ano = ano;
        this.motor = motor;
        this.matricula = matricula;
        this.comentario = comentario;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
