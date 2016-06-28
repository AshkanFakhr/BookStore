package com.apps.webpouyaco.bookstore;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        Intent searchIntent = getIntent();
//        if (Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {
//
//            String query = searchIntent.getStringExtra(SearchManager.QUERY);
//            Toast.makeText(MainActivity.this, query, Toast.LENGTH_LONG).show();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.menu_main, menu);

//        SearchView searchView = (SearchView) menu.findItem(R.id.searchable).getActionView();
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void appLogoClick(View view) {

    }

    public void shopBasketClick(View view) {

    }
}
