package com.pidelectronics.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static com.pidelectronics.cursoavanzado.metodosGlobales.obtenerVersionApp;
import static com.pidelectronics.cursoavanzado.variablesGlobales.codigoQR;

public class MainActivity extends AppCompatActivity {

    //Vistas
    Toolbar mainToolbar;
    Menu menuActivity;
    Button btnQr;
    TextView txtCodigoQr;
    //Variables
    Context context;
    //Objetos y utilidades:
    AlertDialog acercaDeDialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuracionesIniciales();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,QrActivity.class));
            }
        });
        if (!codigoQR.isEmpty()){ //comprueba que la variable codigoQR no este vacia
            String codigoLeido = getString(R.string.codigoEscaneado) + " " + codigoQR;
            txtCodigoQr.setText(codigoLeido);
        }
    }

    private void configuracionesIniciales(){
        mainToolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(mainToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        context = MainActivity.this;
        btnQr = findViewById(R.id.btnLeerQr);
        txtCodigoQr = findViewById(R.id.txtCodigoQR);
    }

    /*
    Infla la vista del menu que disenamos (main_menu)
    Ademas se iguala el menuActivity al menu de la vista inflada
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        this.menuActivity = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); //obtenemos el id del item presionado por el usuario
        switch (id){
            case R.id.mainMenu_logout:
                myData myData = new myData(context);
                myData.setLogeo(false);
                startActivity(new Intent(context,LoadActivity.class));
                break;
            case R.id.mainMenu_aboutApp:
                mostrarCuadroDialogoAcercaDe();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarCuadroDialogoAcercaDe(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup parent = findViewById(R.id.parent);
        View vista = getLayoutInflater().inflate(R.layout.acerca_de_dialogo,parent,false);
        TextView txtVersion = vista.findViewById(R.id.txtDialogoVersion);
        TextView txtSigueme = vista.findViewById(R.id.txtSiguemeFacebook);
        Linkify.addLinks(txtSigueme,Linkify.WEB_URLS);
        Button btnLlamar = vista.findViewById(R.id.btnLlamarDesarrollador); //TODO falta codigo para realizar llamada
        txtVersion.setText(obtenerVersionApp(context));
        builder.setView(vista); //se configura la vista en el builder
        acercaDeDialogo = builder.create(); //se crea el cuadro de dialogo a apartir del builder
        acercaDeDialogo.show(); //muestra el cuadro de dialogo
    }


}