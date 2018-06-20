package com.davidpopayan.sena.guper.Controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.davidpopayan.sena.guper.Fragments.FragmentAcerca;
import com.davidpopayan.sena.guper.Fragments.FragmentInicio;
import com.davidpopayan.sena.guper.Fragments.FragmentListarPermisos;
import com.davidpopayan.sena.guper.Fragments.FragmentListarPermisosIn;
import com.davidpopayan.sena.guper.Fragments.FragmentPerfil;
import com.davidpopayan.sena.guper.Fragments.FragmentPermiso;
import com.davidpopayan.sena.guper.Fragments.FragmentPermisoIn;
import com.davidpopayan.sena.guper.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtNombreMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new FragmentInicio();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View view = navigationView.getHeaderView(0);
        txtNombreMenu = view.findViewById(R.id.NombreMenu);
        txtNombreMenu.setText(Login.personaN);



        if (Login.iniciarSesion == 1){
            navigationView.getMenu().setGroupVisible(R.id.grupo2, false);

        }
        if (Login.iniciarSesion == 2){
            navigationView.getMenu().setGroupVisible(R.id.grupo1, false);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.ayuda) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ayuda");
            builder.setMessage("Para más información, comunícate con nosotros a Guper@gmail.com o visita nuestro sitío web Guperproject.herokuapp.com ");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = new Fragment();

        if (id == R.id.nav_solicitar) {
            fragment = new FragmentPermiso();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();

        } else if (id == R.id.nav_ver) {
            fragment = new FragmentListarPermisos();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();

        } else if (id == R.id.nav_perfil) {

            fragment = new FragmentPerfil();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();

        }else if (id == R.id.nav_permisoI) {

            fragment = new FragmentPermisoIn();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();

        }else if (id == R.id.nav_VerIns){
            fragment = new FragmentListarPermisosIn();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();


        }else if ( id == R.id.nav_cerrar2){
            cerrarSesion();

        }else if (id == R.id.acerca){
            llamar();

        }else if ( id == R.id.acerca2){
            llamar();


        }else if (id == R.id.inicio){
            fragment = new FragmentInicio();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void llamar() {
        Fragment fragment = new FragmentAcerca();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();
    }

    public void cerrarSesion(){
        SharedPreferences sharedPreferences = getSharedPreferences("iniciarSesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user","No existe");
        editor.putString("password","No existe");
        editor.commit();

        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void ListarInstructor(){

    }



}
