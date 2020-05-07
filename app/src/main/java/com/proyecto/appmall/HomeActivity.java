package com.proyecto.appmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyecto.appmall.common.Constantes;
import com.proyecto.appmall.dialogs.NuevaOfertaFragment;
import com.proyecto.appmall.dialogs.NuevaTiendaFragment;
import com.proyecto.appmall.dialogs.NuevoCineFragment;
import com.proyecto.appmall.dialogs.NuevoRestauranteFragment;
import com.proyecto.appmall.ui.cines.CinesFragment;
import com.proyecto.appmall.ui.inicio.InicioFragment;
import com.proyecto.appmall.ui.restaurantes.RestaurantesFragment;
import com.proyecto.appmall.ui.tiendas.TiendasFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private FirebaseUser user;
    private FirebaseAuth auth;
    public String tagFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Añadimos nuestro toolbar editado
        Toolbar toolbar = findViewById(R.id.toolbar);
        Button log = findViewById(R.id.login);


        toolbar.setTitle("Inicio");
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
            tagFragment = "inicio";
            navigationView.setCheckedItem(R.id.nav_inicio);
        }

        initFirebase();

    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    // Añade el boton "Añadir"
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        for(String adminUser : Constantes.ADMIN_UID){
            if(user.getUid().equals(adminUser)){
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.toolbar_menu, menu);
                return true;
            }
        }
        return false;
    }

    // Evento del boton "Añadir" del Toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(tagFragment.equals("inicio")){
            NuevaOfertaFragment nuevaOferta = new NuevaOfertaFragment();
            nuevaOferta.show(getSupportFragmentManager(), "NuevaOfertaFragment");
        }else if(tagFragment.equals("tiendas")){
            NuevaTiendaFragment nuevaTienda = new NuevaTiendaFragment();
            nuevaTienda.show(getSupportFragmentManager(), "NuevaTiendaFragmet");
        }else if(tagFragment.equals("restaurantes")){
            NuevoRestauranteFragment nuevoRestaurante = new NuevoRestauranteFragment();
            nuevoRestaurante.show(getSupportFragmentManager(), "NuevoRestauranteFragment");
        }else if(tagFragment.equals("cines")){
            NuevoCineFragment nuevoCine = new NuevoCineFragment();
            nuevoCine.show(getSupportFragmentManager(), "NuevoCineFragment");
        }

        return super.onOptionsItemSelected(item);
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
        TextView User = findViewById(R.id.txUser);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        User.setText("Usuario: " + user.getEmail());


        switch(item.getItemId()){
            case R.id.nav_inicio:
                toolbar.setTitle("Inicio");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new InicioFragment(), "inicio")
                        .commit();
                tagFragment = "inicio";
                break;
            case R.id.nav_tiendas:
                toolbar.setTitle("Tiendas");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new TiendasFragment(),"tiendas")
                        .commit();
                tagFragment = "tiendas";
                break;
            case R.id.nav_restaurantes:
                toolbar.setTitle("Restaurantes");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new RestaurantesFragment(),"restaurantes")
                        .commit();
                tagFragment = "restaurantes";
                break;
            case R.id.nav_cines:
                toolbar.setTitle("Cines");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new CinesFragment(),"cines")
                        .commit();
                tagFragment = "cines";
                break;
            case R.id.nav_navegar:
                toolbar.setTitle("Navegar");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    public void onClick(View v) {

       //LoginActivity logi = new LoginActivity();
       // logi.signOut();
       // Intent intent = new Intent(this, LoginActivity.class);
       // startActivity(intent);
    }

}
