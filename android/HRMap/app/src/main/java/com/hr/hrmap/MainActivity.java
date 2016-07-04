package com.hr.hrmap;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.jgrapht.*;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    PlattegrondFragment plattegrondFragment = null;
    Plattegrond plattegrond;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SearchView searchView;

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide statusbar of Android
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the status bar.
        if (Build.VERSION.SDK_INT < 16) { //ye olde method
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();

            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }

        plattegrond = new Plattegrond();
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new VerdiepingenFragment()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //show information fragment first
        navigationView.getMenu().getItem(1).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(1));
        final MainActivity mainActivity = this;

        /**
         * clear search
         */
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.reset);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plattegrond.reset();
                searchView.setQuery("", false);
                searchView.setQueryHint("Vul hier je huidige locatie in...");
                searchView.clearFocus();
                searchView.setIconified(true);
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, new VerdiepingenFragment()).commit();
                Toast.makeText(mainActivity, "reset done.", Toast.LENGTH_LONG).show();
            }
        });



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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Vul hier je huidige locatie in...");

        /**
         * when use start entering a search criteria, switch to the plattegrond view immediatelly
         */
        this.searchView = searchView;
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    navigationView.getMenu().getItem(0).setChecked(true);
                    onNavigationItemSelected(navigationView.getMenu().getItem(0));
                }
            }
        });

        final ActionBar actionBar = getActionBar();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.verdiepingen) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.containerView, new VerdiepingenFragment()).commit();
            //plattegrondFragment = fragment;
        } else if (id == R.id.informatie) {
            Fragment fragment;
            fragment = new InformatieFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.containerView, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri data = intent.getData();
            String search = data.getLastPathSegment();
            if (search.isEmpty()) {
                Toast.makeText(this, "Niet gevonden", Toast.LENGTH_LONG).show();
                return;
            }

            if(this.plattegrond.start == null)
            {
                this.plattegrond.clearVisibility();
                this.plattegrond.setLocatieStart(Integer.parseInt(search));
                SearchView searchView = (SearchView) findViewById(R.id.search);
                searchView.setQuery("", false);
                searchView.clearFocus();
                searchView.setQueryHint("target please");
                Locatie startLocatie = this.plattegrond.getLocatieByID(Integer.parseInt(search));
                if(startLocatie != null){
                    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                    viewPager.setCurrentItem(startLocatie.verdieping - 1, true);
                }
                Toast.makeText(this, "Vul nu een target in.", Toast.LENGTH_LONG).show();
            }else {
                //set the point visible then draw the line and the destination.
                this.plattegrond.setLocatieVisitble(Integer.parseInt(search));
                this.plattegrond.setDestination(Integer.parseInt(search));

                Verdieping1Fragment v1 = (Verdieping1Fragment) getSupportFragmentManager().findFragmentById(R.id.eerstedeverdiepingview);
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, new VerdiepingenFragment()).commit();
                Locatie endLocatie = this.plattegrond.getLocatieByID(Integer.parseInt(search));
                if(endLocatie != null){
                    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                    if(viewPager != null){
                        viewPager.setCurrentItem(endLocatie.verdieping - 1, true);
                    }

                    SearchView searchView = (SearchView) findViewById(R.id.search);
                    searchView.setQuery(endLocatie.naam, false);
                    searchView.clearFocus();
                }

            }
        }
    }


}
