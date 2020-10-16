package swtizona.androidapps.bpv.database;

import java.util.ArrayList;

import swtizona.androidapps.bpv.modeldata.Auto;
import swtizona.androidapps.bpv.modeldata.Producto;
import swtizona.androidapps.bpv.modeldata.Recordatorio;
import swtizona.androidapps.bpv.modeldata.Servicio;
import swtizona.androidapps.bpv.modeldata.Taller;

public class Lists {

    private static ArrayList<Auto> autoList;
    private static ArrayList<Producto> productoList;
    private static ArrayList<Recordatorio> recordatorioList;
    private static ArrayList<Servicio> servicioList;
    private static ArrayList<Taller> tallerList;

    public void initLists(){
        autoList = new ArrayList<>();
        productoList = new ArrayList<>();
        recordatorioList = new ArrayList<>();
        servicioList = new ArrayList<>();
        tallerList = new ArrayList<>();
    }

    public static ArrayList<Auto> getAutoList() {
        return autoList;
    }

    public static void setAutoList(ArrayList<Auto> autoList) {
        Lists.autoList = autoList;
    }

    public static ArrayList<Producto> getProductoList() {
        return productoList;
    }

    public static void setProductoList(ArrayList<Producto> productoList) {
        Lists.productoList = productoList;
    }

    public static ArrayList<Recordatorio> getRecordatorioList() {
        return recordatorioList;
    }

    public static void setRecordatorioList(ArrayList<Recordatorio> recordatorioList) {
        Lists.recordatorioList = recordatorioList;
    }

    public static ArrayList<Servicio> getServicioList() {
        return servicioList;
    }

    public static void setServicioList(ArrayList<Servicio> servicioList) {
        Lists.servicioList = servicioList;
    }

    public static ArrayList<Taller> getTallerList() {
        return tallerList;
    }

    public static void setTallerList(ArrayList<Taller> tallerList) {
        Lists.tallerList = tallerList;
    }
}
