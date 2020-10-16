package swtizona.androidapps.bpv.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseController extends SQLiteOpenHelper {

    private static final String nombre = "bitacora.bd";
    private static final int version = 1;

    public DataBaseController(@Nullable Context context) {
        super(context, nombre, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating tables
        // 6 rows
        db.execSQL(Queries.createAutos);
        // 5 rows
        db.execSQL(Queries.createProductos);
        // 5 rows
        db.execSQL(Queries.createTalleres);
        // 6 rows
        db.execSQL(Queries.createServicios);
        // 4 rows
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
     */

    //create
    public void insert() {

    }

    //read
    public void select() {

    }

    //update
    public void update() {

    }

    //delete
    public void delete() {

    }

    //Allocate rows and columns in ram memory because of speed
    public void initList() {

    }

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

    public void showColumnName(String tableName) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM " + tableName, null);

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.d("Column Name: ", cursor.getColumnName(i));
        }
    }
}
