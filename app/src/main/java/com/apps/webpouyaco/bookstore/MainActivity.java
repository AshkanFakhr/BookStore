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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.apps.webpouyaco.bookstore.fragments.FourFragment;
import com.apps.webpouyaco.bookstore.fragments.OneFragment;
import com.apps.webpouyaco.bookstore.fragments.ThreeFragment;
import com.apps.webpouyaco.bookstore.fragments.TwoFragment;
import com.apps.webpouyaco.bookstore.volley.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.home_page,
            R.drawable.publishers,
            R.drawable.shop_basket,
            R.drawable.profile
    };

    // json object response url
    private String urlJsonObjBook = "http://api.androidhive.info/volley/person_object.json";
    private String urlJsonObjPub = "http://api.androidhive.info/volley/person_object.json";

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
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
        adapter.addFragment(new FourFragment(), "FOUR");
        viewPager.setAdapter(adapter);
    }

    // fragment 1 code (home page)

    /**
     * Method to make json object request where json response starts wtih {
     */

    // Json object request or string request???
    public void makeJsonObjectRequestBook() {

        showpDialog();

        JsonObjectRequest jsonObjReqBook = new JsonObjectRequest(Method.GET,
                urlJsonObjBook, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");
                    String email = response.getString("email");
                    JSONObject phone = response.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");

                    jsonResponseBook = "";
                    jsonResponseBook += "Name: " + name + "\n\n";
                    jsonResponseBook += "Email: " + email + "\n\n";
                    jsonResponseBook += "Home: " + home + "\n\n";
                    jsonResponseBook += "Mobile: " + mobile + "\n\n";

                    txtResponseBook.setText(jsonResponseBook);
                    findViewById(R.id.explanation1).setVisibility(View.INVISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReqBook);
    }


    /*public void getBook(View view) {
        final String tag_string_req = "string_req";
        String url = "http://api.androidhive.info/volley/string_response.html";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(tag_string_req, response);
                pDialog.hide();


                TextView tv = (TextView) findViewById(R.id.textView1);
                tv.setText(response);
                findViewById(R.id.explanation1).setVisibility(View.INVISIBLE);


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(tag_string_req, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }*/

    // fragment 2 code (publishers page)

    /**
     * Method to make json object request where json response starts wtih {
     */
    private void makeJsonObjectRequestPub() {

        showpDialog();

        JsonObjectRequest jsonObjReqPub = new JsonObjectRequest(Method.GET,
                urlJsonObjPub, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");
                    String email = response.getString("email");
                    JSONObject phone = response.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");

                    jsonResponsePub = "";
                    jsonResponsePub += "Name: " + name + "\n\n";
                    jsonResponsePub += "Email: " + email + "\n\n";
                    jsonResponsePub += "Home: " + home + "\n\n";
                    jsonResponsePub += "Mobile: " + mobile + "\n\n";

                    txtResponsePub.setText(jsonResponsePub);
                    findViewById(R.id.explanation2).setVisibility(View.INVISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReqPub);
    }


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

    public void getBook(View view) {
        makeJsonObjectRequestBook();
    }

    public void getPublishers(View view) {
        makeJsonObjectRequestPub();
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
