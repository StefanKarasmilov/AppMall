package com.proyecto.appmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.proyecto.appmall.ui.cines.CinesFragment;
import com.proyecto.appmall.ui.inicio.InicioFragment;
import com.proyecto.appmall.ui.restaurantes.RestaurantesFragment;
import com.proyecto.appmall.ui.tiendas.TiendasFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Añadimos nuestro toolbar editado
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enlazamos las variables con los componentes gráficos
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Botón que abre el menú
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            toolbar.setTitle("Inicio");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new InicioFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }

    }

    // Método que controla la apertura y cierre del menú
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Toolbar toolbar = findViewById(R.id.toolbar);

        switch(item.getItemId()){
            case R.id.nav_inicio:
                toolbar.setTitle("Inicio");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new InicioFragment())
                        .commit();
                break;
            case R.id.nav_tiendas:
                toolbar.setTitle("Tiendas");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new TiendasFragment())
                        .commit();
                break;
            case R.id.nav_restaurantes:
                toolbar.setTitle("Restaurantes");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new RestaurantesFragment())
                        .commit();
                break;
            case R.id.nav_cines:
                toolbar.setTitle("Cines");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new CinesFragment())
                        .commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
