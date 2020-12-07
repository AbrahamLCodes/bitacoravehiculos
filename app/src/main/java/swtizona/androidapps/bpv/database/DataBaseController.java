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
     * CRUD to SQLite. CREATE READ UPDATE & DELETE functions
     * <p>
     * HOW TO USE THESE CLASS's METHODS
     * <p>
     * 1* If the database is empty, don't use initList(), instead use INSERT
     * 2* Use initList() when deleting, updating or inserting data in order to refresh RAM Memory variables
     */

    public void insertRecordatorio(String[] rows) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO RECORDATORIOS VALUES (" +
                    "'" + rows[0] + "','"
                    + rows[1] + "','"
                    + rows[2] + "','"
                    + rows[3] + "','"
                    + rows[4] + "','"
                    + rows[5] + "','"
                    + rows[6] + "','"
                    + rows[7] + "','"
                    + rows[8] + "')");
        }
    }


    /**
     * INSERT with 6 rows
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
    }

    /**
     * INSERT with 8 rows (For Talleres)
     */

    public void insert8Rows(String tableName, String[] rows) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO " + tableName + " VALUES (" +
                    "'" + rows[0] + "','"
                    + rows[1] + "','"
                    + rows[2] + "','"
                    + rows[3] + "','"
                    + rows[4] + "','"
                    + rows[5] + "','"
                    + rows[6] + "','"
                    + rows[7] + "')");
        }
    }

    public void insert9Rows(String tableName, String[] rows) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO " + tableName + " VALUES (" +
                    "'" + rows[0] + "','"
                    + rows[1] + "','"
                    + rows[2] + "','"
                    + rows[3] + "','"
                    + rows[4] + "','"
                    + rows[5] + "','"
                    + rows[6] + "','"
                    + rows[7] + "','"
                    + rows[8] + "')");
        }
    }

    /**
     * SELECT
     */
    private void selectRows(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        addToList(tableName, cursor);
        db.close();
    }

    public ArrayList ultimateAllSelect(String tableName, ArrayList lista) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);

        switch (tableName) {
            case "AUTOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Auto(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5))
                        );
                    } while (cursor.moveToNext());
                }
                break;
            case "PRODUCTOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Producto(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5))
                        );
                    } while (cursor.moveToNext());
                }
                break;
            case "SERVICIOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Servicio(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(6)
                                , cursor.getString(7)
                                , cursor.getString(8))
                        );
                    } while (cursor.moveToNext());
                }
                break;
            case "TALLERES":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Taller(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5)
                                , cursor.getString(6)
                                , cursor.getString(7))
                        );
                    } while (cursor.moveToNext());
                }
                break;
            case "RECORDATORIOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Recordatorio(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                cursor.getString(8)
                        ));
                    } while (cursor.moveToNext());
                }
        }
        return lista;
    }

    public ArrayList customSelect(String tableName, String where, String like, ArrayList lista) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " WHERE " + where + " = '" + like + "'", null);

        switch (tableName) {
            case "AUTOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Auto(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5))
                        );
                    } while (cursor.moveToNext());
                }
                break;
            case "PRODUCTOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Producto(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5))
                        );
                    } while (cursor.moveToNext());
                }
                break;
            case "SERVICIOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Servicio(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(6)
                                , cursor.getString(7)
                                , cursor.getString(8))
                        );
                    } while (cursor.moveToNext());
                }
                break;
            case "TALLERES":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Taller(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(5)
                                , cursor.getString(6)
                                , cursor.getString(7))
                        );
                    } while (cursor.moveToNext());
                }
                break;
            case "RECORDATORIOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Recordatorio(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                cursor.getString(8)
                        ));
                    } while (cursor.moveToNext());
                }
        }

        return lista;
    }

    public ArrayList orderBySelect(String tableName, String column, String asc, ArrayList lista) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " ORDER BY " + column + " " + asc + "", null);

        switch (tableName) {
            case "SERVICIOS":
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(new Servicio(
                                cursor.getString(0)
                                , cursor.getString(1)
                                , cursor.getString(2)
                                , cursor.getString(3)
                                , cursor.getString(4)
                                , cursor.getString(6)
                                , cursor.getString(7)
                                , cursor.getString(8))
                        );
                    } while (cursor.moveToNext());
                }
                break;
        }
        return lista;
    }

    private void addToList(String tableName, Cursor cursor) {
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
                break;

            case "PRODUCTOS":
                if (cursor.moveToFirst()) {
                    do {
                        Lists.getProductoList().add(
                                new Producto(
                                        cursor.getString(0)
                                        , cursor.getString(1)
                                        , cursor.getString(2)
                                        , cursor.getString(3)
                                        , cursor.getString(4)
                                        , cursor.getString(5)));
                    } while (cursor.moveToNext());
                }
                break;

            case "TALLERES":
                if (cursor.moveToFirst()) {
                    do {
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

                    } while (cursor.moveToNext());
                }
                break;

            case "SERVICIOS":
                if (cursor.moveToFirst()) {
                    do {
                        Lists.getServicioList().add(
                                new Servicio(
                                        cursor.getString(0)
                                        , cursor.getString(1)
                                        , cursor.getString(2)
                                        , cursor.getString(3)
                                        , cursor.getString(4)
                                        , cursor.getString(6)
                                        , cursor.getString(7)
                                        , cursor.getString(8)));

                    } while (cursor.moveToNext());
                }
                break;
            case "RECORDATORIOS":
                if (cursor.moveToFirst()) {
                    do {
                        Lists.getRecordatorioList().add(
                                new Recordatorio(
                                        cursor.getString(0),
                                        cursor.getString(1),
                                        cursor.getString(2),
                                        cursor.getString(3),
                                        cursor.getString(4),
                                        cursor.getString(5),
                                        cursor.getString(6),
                                        cursor.getString(7),
                                        cursor.getString(8))
                        );

                    } while (cursor.moveToNext());
                }
                break;
        }
    }

    /**
     * Allocate rows and columns in ram memory because of speed
     */
    private void initList(String tableName) {
        selectRows(tableName);
    }

    public void updateLists() {
        initList("AUTOS");
        initList("PRODUCTOS");
        initList("TALLERES");
        initList("SERVICIOS");
        initList("RECORDATORIOS");
    }

    /**
     * UPDATE
     */
    public void update(String tableName, String set, String where, String like) {
        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            db.execSQL("UPDATE " + tableName + " SET " + set + " WHERE " + where + " = " + like);
            Log.d("debugeando sentencia", "UPDATE " + tableName + " SET " + set + " WHERE " + where + " = " + like);
            db.close();
        }
    }

    /**
     * DELETE
     */
    public void delete(String tableName, String where, String like) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("DELETE FROM " + tableName + " WHERE " + where + " =  '" + like + "' ");
        }
    }


    /**
     * Function to check if Foreign Key exists in a table
     */
    public boolean isForeignKey(String tableName, String where, String like) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " WHERE " + where + " =  '" + like + "' ",
                null);
        return cursor.moveToFirst();
    }

    /**
     * THESE ARE AUXILIAR FUNCTIONS TO HELP THE DEVELOPER DEBUG THE PROJECT WHEN CHANGING
     * DATABASE STRUCTURE
     */


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
