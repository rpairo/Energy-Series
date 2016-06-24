package tech.rpairo.energyseries.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.fragments.FragmentSeries;

public class MainActivity extends AppCompatActivity {

    //region Variables
    private DrawerLayout drawerLayout;
    //endregion

    //region Constructores
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        this.agregarToolbar();

        if (navigationView != null) {
            this.prepararNavigationView(navigationView);

            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }
    //endregion

    //region Toolbar
    private void agregarToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this,
                    drawerLayout,
                    toolbar,
                    R.string.navigation_view_open,
                    R.string.navigation_view_close);

            this.drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }
    }
    //endregion

    //region NavigationView
    private void prepararNavigationView(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        seleccionarItem(item);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void seleccionarItem(MenuItem itemNavigationView) {

        Fragment fragmentGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemNavigationView.getItemId()) {
            case R.id.item_series:
                fragmentGenerico = new FragmentSeries();
                break;
        }

        if (fragmentGenerico != null)
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, fragmentGenerico)
                    .commit();

        setTitle(itemNavigationView.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion
}