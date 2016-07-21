package com.apps.webpouyaco.bookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apps.webpouyaco.bookstore.fragments.HomePage;
import com.apps.webpouyaco.bookstore.fragments.ProfilePage;
import com.apps.webpouyaco.bookstore.fragments.PublishersPage;
import com.apps.webpouyaco.bookstore.fragments.ShoppingCartPage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //setting icons for tab layout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.home_page,
            R.drawable.publishers,
            R.drawable.shop_basket,
            R.drawable.profile
    };


    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeObjectRequestBook, btnMakeObjectRequestPub;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponseBook, txtResponsePub;

    // temporary string to show the parsed response
    private String jsonResponseBook, jsonResponsePub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClick(v);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        btnMakeObjectRequestBook = (Button) findViewById(R.id.one_frag_get_book_btn);
        txtResponseBook = (TextView) findViewById(R.id.one_frag_get_book_txt);

        btnMakeObjectRequestPub = (Button) findViewById(R.id.two_frag_get_pub_btn);
        txtResponsePub = (TextView) findViewById(R.id.two_frag_get_pub_txt);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        /*btnMakeObjectRequestBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                makeJsonObjectRequestBook();
            }
        });*/

        /*btnMakeObjectRequestPub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                makeJsonObjectRequestPub();
            }
        });*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void appLogoClick(View view) {

    }

    public void searchClick(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    // tab layout icons
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomePage(), "ONE");
        adapter.addFragment(new PublishersPage(), "TWO");
        adapter.addFragment(new ShoppingCartPage(), "THREE");
        adapter.addFragment(new ProfilePage(), "FOUR");
        viewPager.setAdapter(adapter);
    }

    /**
     * Method to make json object request where json response starts wtih {
     */

    /*public void getPublishers(View view) {
        String tag_string_req = "string_req";
        String url = "http://api.androidhive.info/volley/string_response.html";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();


                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setText(response);
                findViewById(R.id.explanation2).setVisibility(View.INVISIBLE);


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }*/

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    // tab layout code
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

}
