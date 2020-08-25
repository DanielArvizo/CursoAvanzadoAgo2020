package com.pidelectronics.cursoavanzado;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.pidelectronics.cursoavanzado.variablesGlobales.usuariosApp;

public class metodosGlobales {
    /*
    Clase java donde estan todos los metodos globales de la aplicacion
     */

    public static String obtenerVersionApp(Context context){
        String respuesta = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            respuesta = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public static void leerBaseDeDatos(Context context){
        usuariosApp = new ArrayList<>(); //inicializamos usuariosApp
        SQLiteDatabase database;
        usuariosDB usuariosDB = new usuariosDB(context);
        database = usuariosDB.getWritableDatabase();
        Cursor cursor = database.query(usuariosContrato.usuariosColumnas.TABLE_NAME,null,
                null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String password = cursor.getString(2);
            String genero = cursor.getString(3);
            String rol = cursor.getString(4);
            String correo = cursor.getString(5);
            usuariosApp.add(new usuario(id,nombre,password,genero,rol,correo));
        }
        cursor.close();
    }

    public static void agregarUsuarioABaseDeDatos(Context context, usuario usuario){
        SQLiteDatabase database;
        usuariosDB usuariosDB = new usuariosDB(context);
        database = usuariosDB.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(usuariosContrato.usuariosColumnas.NOMBRE,usuario.getNombre());
        cv.put(usuariosContrato.usuariosColumnas.PASSWORD,usuario.getPassword());
        cv.put(usuariosContrato.usuariosColumnas.GENERO,usuario.getGenero());
        cv.put(usuariosContrato.usuariosColumnas.ROL,usuario.getRol());
        cv.put(usuariosContrato.usuariosColumnas.CORREO,usuario.getCorreo());
        database.insert(usuariosContrato.usuariosColumnas.TABLE_NAME,null,cv);
    }
}
