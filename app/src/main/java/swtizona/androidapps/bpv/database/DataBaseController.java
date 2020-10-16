package swtizona.androidapps.bpv.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import swtizona.androidapps.bpv.modeldata.Auto;
import swtizona.androidapps.bpv.modeldata.Producto;
import swtizona.androidapps.bpv.modeldata.Recordatorio;
import swtizona.androidapps.bpv.modeldata.Servicio;
import swtizona.androidapps.bpv.modeldata.Taller;

public class DataBaseController extends SQLiteOpenHelper {

    private static final String nombre = "bitacora.bd";
    private static final int version = 1;

    public DataBaseController(@Nullable Context context) {
        super(context, nombre, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating tables
        db.execSQL(Queries.createAutos);
        db.execSQL(Queries.createProductos);
        db.execSQL(Queries.createTalleres);
        db.execSQL(Queries.createServicios);
        db.execSQL(Queries.createRecordatorios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Droping all tables

        db.execSQL("DROP TABLE IF EXISTS AUTOS");
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        db.execSQL("DROP TABLE IF EXISTS TALLERES");
        db.execSQL("DROP TABLE IF EXISTS SERVICIOS");
        db.execSQL("DROP TABLE IF EXISTS RECORDATORIOS");

        //calling onCreate in order to "re" create tables
        onCreate(db);
    }

    /**
      CRUD to SQLite. CREATE READ UPDATE & DELETE functions

      HOW TO USE THESE CLASS's METHODS

     1* If the database is empty, don't use initList(), instead use INSERT
     2* Use initList() when deleting, updating or inserting data in order to refresh RAM Memory variables
     */


    /**
      INSERT with 4 rows
     */
    public void insert4Rows(String tableName, String[] rows) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO " + tableName + " VALUES (" +
                    "'" + rows[0] + "','"
                    + rows[1] + "','"
                    + rows[2] + "','"
                    + rows[3] + "')");
        }
        bd.close();
    }

    /**
      INSERT with 5 rows
     */
    public void insert5Rows(String tableName, String[] rows) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO " + tableName + " VALUES (" +
                    "'" + rows[0] + "','"
                    + rows[1] + "','"
                    + rows[2] + "','"
                    + rows[3] + "','"
                    + rows[4] + "')");
        }
        bd.close();
    }

    /**
      INSERT with 6 rows
     */
    public void insert6Rows(String tableName, String[] rows) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO " + tableName + " VALUES (" +
                    "'" + rows[0] + "','"
                    + rows[1] + "','"
                    + rows[2] + "','"
                    + rows[3] + "','"
                    + rows[4] + "','"
                    + rows[5] + "')");
        }
        bd.close();
    }


    /**
      SELECT
     */
    public void selectRows(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        addToList(tableName, cursor);
        db.close();
    }


    public void addToList(String tableName, Cursor cursor) {
        switch (tableName) {
            case "AUTOS":
                if (cursor.moveToFirst()) {
                    do {
                        Lists.getAutoList().add(
                                new Auto(
                                        cursor.getString(0)
                                        , cursor.getString(1)
                                        , cursor.getString(2)
                                        , cursor.getString(3)
                                        , cursor.getString(4)
                                        , cursor.getString(5)));

                    } while (cursor.moveToNext());
                }
            case "PRODUCTOS":
                Lists.getProductoList().add(
                        new Producto(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5)));
            case "TALLERES":
                Lists.getTallerList().add(
                        new Taller(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5)
                                , cursor.getString(6)
                                , cursor.getString(7)));
            case "SERVICIOS":
                Lists.getServicioList().add(
                        new Servicio(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5)));
            case "RECORDATORIOS":
                Lists.getRecordatorioList().add(
                        new Recordatorio(
                                cursor.getInt(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)));
        }
    }

    /**
      Allocate rows and columns in ram memory because of speed
     */
    public void initList(String tableName) {
        selectRows(tableName);

    }

    /**
      UPDATE
     */
    public void update(String tableName, String set, String where, String like) {
        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            db.execSQL("UPDATE " + tableName + " SET " + set + " WHERE " + where + " = " + like);
            db.close();
        }
    }

    /**
      DELETE
     */
    public void delete(String tableName, String where, String like) {
        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            db.execSQL("DELETE FROM " + tableName + " WHERE " + where + " = " + like);
        }
    }

    /** THESE ARE AUXILIAR FUNCTIONS TO HELP THE DEVELOPER DEBUG THE PROJECT WHEN CHANGING
      DATABASE STRUCTURE */


    //Auxiliar function to debug. Returns the row count of a table
    public long getRowCount(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, tableName);
        db.close();

        return count;
    }

    //Auxiliar function to debug. Returns the column count of a table
    public long getColumnCount(String tableName) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM " + tableName, null);

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.d("Column Name: ", cursor.getColumnName(i));
        }

        long count = cursor.getColumnCount();
        return count;
    }

    //Auxiliar function to debuG. Returns the column name of a table
    public void showColumnName(String tableName) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM " + tableName, null);

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.d("Column Name: ", cursor.getColumnName(i));
        }
    }
}