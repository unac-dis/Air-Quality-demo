package co.edu.unac.iotunac.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.unac.iotunac.R;

public class Navigationdrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txtName;
    TextView txtEmail;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.navigationdrawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final AlertDialog.Builder informaciondesarrollador = new AlertDialog.Builder(this);
            informaciondesarrollador.setIcon(R.drawable.ic_informacion_icon);
            informaciondesarrollador.setTitle("Info Desarrolladores");
            informaciondesarrollador.setMessage("Aplicación Desarrollada por Brayan Torres, Kevin Ortiz, Christopher Vargas" +
                    "y Jhan Sierra; Version: 1.0.0");
            informaciondesarrollador.setCancelable(false);
            informaciondesarrollador.setPositiveButton("Cerrar", null);
            informaciondesarrollador.show();
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_resumensemanal) {
            Intent intent = new Intent(Navigationdrawer.this, Graficosemanal.class);
            startActivity(intent);
        } else if (id == R.id.nav_resumenmensual) {
            Intent intent = new Intent(Navigationdrawer.this, Graficomensual.class);
            startActivity(intent);
        } else if (id == R.id.nav_calcularimc) {
            Intent intent = new Intent(Navigationdrawer.this, CalcularIMC.class);
            startActivity(intent);
        } else if (id == R.id.nav_estadodelclima) {
            Intent intent = new Intent(Navigationdrawer.this, Estadodelclima.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_salir) {
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
