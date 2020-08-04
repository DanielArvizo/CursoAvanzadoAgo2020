package com.pidelectronics.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.pidelectronics.cursoavanzado.metodosGlobales.obtenerVersionApp;

public class LoadActivity extends AppCompatActivity {

    ///////////////////////////////////  VISTAS   //////////////////////////////////////////////////
    TextView txtInfo, txtVersion;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  UTILIDADES  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    Context context;
    Timer primerTimer;
    Timer segundoTimer;
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txtInfo = findViewById(R.id.txtLoadInfo);
        txtVersion = findViewById(R.id.txtLoadVersion);
        context = LoadActivity.this;
        String version = obtenerVersionApp(context);
        txtVersion.setText(version);
        setPrimerTimer(3000);
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
                        startActivity(new Intent(context,LoginActivity.class));
                    }
                });
            }
        },milis);
    }
}