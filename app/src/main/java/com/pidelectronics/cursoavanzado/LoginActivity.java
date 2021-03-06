package com.pidelectronics.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import static com.pidelectronics.cursoavanzado.metodosGlobales.leerBaseDeDatos;
import static com.pidelectronics.cursoavanzado.variablesGlobales.passwordAdmin;
import static com.pidelectronics.cursoavanzado.variablesGlobales.usuarioAdmin;
import static com.pidelectronics.cursoavanzado.variablesGlobales.usuariosApp;

public class LoginActivity extends AppCompatActivity {

    ///////////////////////////////////  VISTAS   //////////////////////////////////////////////////
    TextView txtVersion;
    EditText edtUsuario, edtPassword;
    Button btnAccesar;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  UTILIDADES  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    Context context;
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configuracionesIniciales();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAccesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = edtUsuario.getText().toString(); //se obtiene el usuario ingresado
                String password = edtPassword.getText().toString();
                if (usuario.isEmpty()){ //Compara si el usuario esta vacio
                    Toast.makeText(context,getString(R.string.faltaUsuario),Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){ //Compara si el password esta vacio
                    Toast.makeText(context,getString(R.string.faltaPassword), Toast.LENGTH_SHORT).show();
                }else{ //Entra aqui si usuario y password no estan vacios
                    if (usuarioExisteEnBaseDeDatos(usuario) || usuario.equals(usuarioAdmin)){
                        if (passwordExisteEnBaseDeDatos(password) || password.equals(passwordAdmin)){
                            Toast.makeText(context,getString(R.string.accesoCorrecto),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context,MainActivity.class));
                            myData myData = new myData(context);
                            myData.guardarUsuario(usuario);
                            myData.setLogeo(true);
                        }else{
                            Toast.makeText(context,getString(R.string.accesoIncorrecto),Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context,getString(R.string.accesoIncorrecto),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean usuarioExisteEnBaseDeDatos(String usuarioIngresado){
        leerBaseDeDatos(context);
        for(usuario u: usuariosApp){
            if (usuarioIngresado.equals(u.getNombre())){
                return true;
            }
        }
        return false;
    }

    private boolean passwordExisteEnBaseDeDatos(String passwordIngresado){
        for (usuario u: usuariosApp){
            if (passwordIngresado.equals(u.getPassword())){
                return true;
            }
        }
        return false;
    }

    private void configuracionesIniciales(){
        txtVersion = findViewById(R.id.txtLoginVersion);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnAccesar = findViewById(R.id.btnAccesar);
        context = LoginActivity.this;
    }
}