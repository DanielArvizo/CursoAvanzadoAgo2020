package com.pidelectronics.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import static com.pidelectronics.cursoavanzado.variablesGlobales.usuariosApp;

public class UsuariosActivity extends AppCompatActivity {

    ListView listUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        listUsuarios = findViewById(R.id.listUsuarios);
        listAdapter adaptador = new listAdapter(UsuariosActivity.this,
                R.layout.usuario_list_item,0,usuariosApp);
        listUsuarios.setAdapter(adaptador);
    }
}