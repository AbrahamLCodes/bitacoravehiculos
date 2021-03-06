package swtizona.androidapps.bpv.database;

public class Queries {

    public static final String createAutos
            = "CREATE TABLE AUTOS ("
            + "FABRICANTE TEXT NOT NULL"
            + ", MODELO TEXT NOT NULL"
            + ", ANO TEXT NOT NULL"
            + ", MOTOR TEXT NOT NULL"
            + ", MATRICULA TEXT PRIMARY KEY NOT NULL"
            + ", COMENTARIO TEXT) ";

    public static final String createProductos
            = "CREATE TABLE PRODUCTOS("
            + "NOMBRE TEXT NOT NULL"
            + ", AUTO TEXT NOT NULL REFERENCES AUTOS(MATRICULA)"
            + ", MODELO TEXT NOT NULL"
            + ", MARCA TEXT NOT NULL"
            + ", NSERIE TEXT NOT NULL"
            + ", COMENTARIO TEXT"
            + ", PRIMARY KEY (MODELO, NSERIE));";

    public static final String createTalleres
            = "CREATE TABLE TALLERES ("
            + "  NOMBRE TEXT NOT NULL"
            + ", TELEFONO TEXT PRIMARY KEY NOT NULL"
            + ", CALLE TEXT NOT NULL"
            + ", NCALLE TEXT NOT NULL"
            + ", COLONIA TEXT NOT NULL"
            + ", CIUDAD TEXT NOT NULL"
            + ", ESTADO TEXT NOT NULL"
            + ", COMENTARIO TEXT)";

    public static final String createServicios
            = "CREATE TABLE SERVICIOS("
            + "SERVICIO TEXT PRIMARY KEY NOT NULL"
            + ", AUTO TEXT NOT NULL REFERENCES AUTOS(MATRICULA)"
            + ", DIA TEXT NOT NULL"
            + ", MES TEXT NOT NULL"
            + ", ANIO TEXT NOT NULL"
            + ", FECHA TEXT NOT NULL"
            + ", TALLER TEXT NOT NULL REFERENCES TALLERES (TELEFONO)"
            + ", PRODUCTOS TEXT NOT NULL REFERENCES PRODUCTOS (MODELO)"
            + ", COMENTARIO TEXT)";

    public static final String createRecordatorios
            = "CREATE TABLE RECORDATORIOS("
            + "ID NUMERIC PRIMARY KEY NOT NULL"
            + ", AUTO TEXT NOT NULL REFERENCES AUTOS(MATRICULA)"
            + ", DIA TEXT NOT NULL"
            + ", MES TEXT NOT NULL"
            + ", ANIO TEXT NOT NULL"
            + ", HORA TEXT NOT NULL"
            + ", MINUTO TEXT NOT NULL"
            + ", AMPM TEXT NOT NULL"
            + ", SERVICIO TEXT NOT NULL)";
}
