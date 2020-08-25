package com.pidelectronics.cursoavanzado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.pidelectronics.cursoavanzado.variablesGlobales.LOG_TAG;

public class usuariosDB extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;
    public static final String NOMBRE_BASEDATOS = "usuarios.db";


    public usuariosDB(@Nullable Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String comando = "CREATE TABLE " + usuariosContrato.usuariosColumnas.TABLE_NAME + " (" +
                usuariosContrato.usuariosColumnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                usuariosContrato.usuariosColumnas.NOMBRE + " TEXT NOT NULL," +
                usuariosContrato.usuariosColumnas.PASSWORD + " TEXT NOT NULL," +
                usuariosContrato.usuariosColumnas.GENERO + " TEXT NOT NULL," +
                usuariosContrato.usuariosColumnas.ROL + " TEXT NOT NULL," +
                usuariosContrato.usuariosColumnas.CORREO + " TEXT NOT NULL" +
                ")";
        try{
            db.execSQL(comando);
            Log.i(LOG_TAG, "Comando ejecutado en sql: " + comando);
            Log.i(LOG_TAG, "Tabla: " + usuariosContrato.usuariosColumnas.TABLE_NAME +
                    " Creada exitosamente!!");
        }catch (Exception e){
            Log.e(LOG_TAG,"Exception on db.execSQL: " + e);
        }
    }

    /*
    Se ejecuta la primera que se crea la base de datos o cuando cambia el numero de version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_BASEDATOS);
        onCreate(db);
    }
}
