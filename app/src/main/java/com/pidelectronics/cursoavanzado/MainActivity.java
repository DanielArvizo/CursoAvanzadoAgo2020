package com.pidelectronics.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Vistas
    Toolbar mainToolbar;
    Menu menuActivity;
    //Variables
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuracionesIniciales();
    }

    private void configuracionesIniciales(){
        mainToolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(mainToolbar);
        context = MainActivity.this;
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
                Toast.makeText(context,"Logout presionado!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainMenu_aboutApp:
                Toast.makeText(context,"Acerca de la app presionado!",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}