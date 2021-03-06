package co.edu.unac.iotunac.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.squareup.picasso.Picasso;

import co.edu.unac.iotunac.actividades.Descanso;
import co.edu.unac.iotunac.actividades.Podometro;
import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;
import co.edu.unac.iotunac.objects.User;
import co.edu.unac.iotunac.task.AirQualityTask;
import co.edu.unac.iotunac.objects.AirStatus;

public class Navigationdrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txtName;
    TextView txtEmail;
    ImageView imageView;
    static double co;

    public static double getCo() {
        return co;
    }

    public static void setCo(double co) {
        Navigationdrawer.co = co;
    }

    public void airquality() {

        AirQualityTask task = new AirQualityTask(getApplicationContext());
        AirStatus airStatus = new AirStatus();
        try {
            airStatus = task.execute("").get();
            co  =    airStatus.getCo2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_informacion_icon)
                .setTitle("Status")
                .setMessage("La Calidad del aires es: " + airStatus.getStatus()+" con una medida de: "+airStatus.getCo2()+" ppm")
                .setCancelable(false)
                .setPositiveButton("Cerrar", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        DBSQLiteHelper baseDatos = new DBSQLiteHelper(getApplicationContext());
        NavigationView navigationView = (findViewById(R.id.nav_view));
        navigationView.setNavigationItemSelectedListener(this);
        txtName = navigationView.getHeaderView(0).findViewById(R.id.nombreuser);
        txtEmail = navigationView.getHeaderView(0).findViewById(R.id.usuarioemail);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.imageprofile);
        User user = baseDatos.findUser();
        Uri personPhotoUrl = user.getImage();
        txtName.setText(user.getName());
        txtEmail.setText(user.getCorreo());
        Picasso.with(this).load(personPhotoUrl).into(imageView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigationdrawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            final AlertDialog.Builder informaciondesarrollador = new AlertDialog.Builder(Navigationdrawer.this);
            informaciondesarrollador.setIcon(R.drawable.ic_informacion_icon);
            informaciondesarrollador.setView(R.layout.info);
            informaciondesarrollador.setMessage("Info!");
            informaciondesarrollador.setCancelable(false);
            informaciondesarrollador.setPositiveButton("Cerrar", null);
            informaciondesarrollador.show();
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_resumensemanal) {
            Intent intent = new Intent(Navigationdrawer.this, Graficosemanal.class);
            startActivity(intent);
        } else if (id == R.id.nav_resumenmensual) {
            Intent intent = new Intent(Navigationdrawer.this, Graficomensual.class);
            startActivity(intent);
        } else if (id == R.id.nav_podometro) {
            Intent intent = new Intent(Navigationdrawer.this, Podometro.class);
            startActivity(intent);
        } else if (id == R.id.nav_estadodelclima) {
            Intent intent = new Intent(Navigationdrawer.this, Estadodelclima.class);
            startActivity(intent);
        } else if (id == R.id.nav_calidaddelaire) {
            airquality();
        } else if (id == R.id.nav_descansar) {
            Intent intent = new Intent(Navigationdrawer.this, Descanso.class);
            startActivity(intent);
        } else if (id == R.id.nav_imc) {
            Intent intent = new Intent(Navigationdrawer.this, ImcActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_salir) {
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}