package com.apps.webpouyaco.bookstore;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

import com.apps.webpouyaco.bookstore.R;
import com.apps.webpouyaco.bookstore.fragments.OneFragment;
import com.apps.webpouyaco.bookstore.fragments.ThreeFragment;
import com.apps.webpouyaco.bookstore.fragments.TwoFragment;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private SearchManager searchManager;
    private SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList = new ArrayList <ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
    private MenuItem searchItem;

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

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        parentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();

        //the app will crash if display list is not called here.
        displayList();

        //this expands the list of continents.
        expandAll();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (android.widget.SearchView) MenuItemCompat.getActionView(searchItem);

        if (searchView != null) {
            searchView.setSearchableInfo
                    (searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
            searchView.setOnQueryTextListener(this);
            searchView.setOnCloseListener(this);
            searchView.requestFocus();
        }
//        SearchView searchView = (SearchView) menu.findItem(R.id.searchable).getActionView();
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    private void loadData() {
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;

        childRows.add(new ChildRow(R.mipmap.ic_launcher, "test text"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher, "test text2"));
        parentRow = new ParentRow("first group", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();

        childRows.add(new ChildRow(R.mipmap.ic_launcher, "test text3"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher, "test text4"));
        parentRow = new ParentRow("second group", childRows);
        parentList.add(parentRow);
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    private void displayList() {
        loadData();

        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new MyExpandableListAdapter(MainActivity.this, parentList);

        myList.setAdapter(listAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void appLogoClick(View view) {

    }

    public void shopBasketClick(View view) {

    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }
}
