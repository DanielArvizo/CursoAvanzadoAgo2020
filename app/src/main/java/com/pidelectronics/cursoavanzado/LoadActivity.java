package com.pidelectronics.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.CAMERA;
import static com.pidelectronics.cursoavanzado.metodosGlobales.obtenerVersionApp;

public class LoadActivity extends AppCompatActivity {

    ///////////////////////////////////  VISTAS   //////////////////////////////////////////////////
    TextView txtInfo, txtVersion;
    Button btnPermisos;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  UTILIDADES  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    Context context;
    Timer primerTimer;
    Timer segundoTimer;
    boolean permisoCamara = false;
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txtInfo = findViewById(R.id.txtLoadInfo);
        txtVersion = findViewById(R.id.txtLoadVersion);
        btnPermisos = findViewById(R.id.btnPermisos);
        context = LoadActivity.this;
        String version = obtenerVersionApp(context);
        txtVersion.setText(version);
        revisarPermisos();

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revisarPermisos();
                btnPermisos.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void revisarPermisos(){
        permisoCamara = checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED;
        if (permisoCamara){
            setPrimerTimer(3000);
        }else{
            requestPermissions(new String[]{CAMERA},25);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 25){
            permisoCamara = checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED;
            if (permisoCamara){
                setPrimerTimer(3000);
            }else{
                txtInfo.setText(getString(R.string.alertaPermisos));
                btnPermisos.setVisibility(View.VISIBLE);
            }
        }

    }

    private void setPrimerTimer(int milis){
        primerTimer = new Timer(); //declarando el timer como uno nuevo
        primerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //aqui se ejecuta el codigo una vez que el timer termina
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Aqui nos regresamos al hilo principal
                        txtInfo.setText(getString(R.string.preparando));
                        setSegundoTimer(3500);
                    }
                });
            }
        },milis);

    }

    private void setSegundoTimer(int milis){
        segundoTimer = new Timer(); //declarando el timer como uno nuevo
        segundoTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //aqui se ejecuta el codigo una vez que el timer termina
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Aqui nos regresamos al hilo principal
                        txtInfo.setText(getString(R.string.espere));
                        myData myData = new myData(context);
                        boolean acceso = myData.getLogeo();
                        if (acceso){
                            startActivity(new Intent(context,MainActivity.class));
                        }else {
                            startActivity(new Intent(context, LoginActivity.class));
                        }
                    }
                });
            }
        },milis);
    }
}