package info.upump.creepyapp;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IControllerFragment {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private ImageView imageView;
    private Menu menuTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState == null) {
            // Set the local night mode to some value
            getDelegate().setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
            // Now recreate for it to take effect
            recreate();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        appBarLayout = findViewById(R.id.main_appbar);
        imageView = findViewById(R.id.main_backdrop);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.INVISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            CoverFragment coverFragment = CoverFragment.newInstance();
            createFragment(coverFragment);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
            finish();
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menuTool = menu;
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
        if (id == R.id.action_settings) {
            int mode = AppCompatDelegate.getDefaultNightMode();
            if (mode == AppCompatDelegate.MODE_NIGHT_NO) {

                getDelegate().setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);


            } else {
                getDelegate().setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);

            }

            recreate();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            fragment = CoverFragment.newInstance();

        } else if (id == R.id.nav_favorite) {
            fragment = FavoriteCoverFragment.newInstance();

        } else if (id == R.id.nav_send_history) {

        }
        if (fragment != null) {
            createFragment(fragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void createFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        System.out.println(getSupportFragmentManager().getBackStackEntryCount());
        if (!(fragment instanceof CoverFragment)) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void setTitle(String title, int imgIdent) {
        collapsingToolbarLayout.setTitle(title);
        appBarLayout.setExpanded(true);
        Picasso.with(this).load(imgIdent).into(imageView);
    }


}
