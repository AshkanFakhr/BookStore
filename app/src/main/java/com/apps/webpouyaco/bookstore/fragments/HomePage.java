package com.apps.webpouyaco.bookstore.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.apps.webpouyaco.bookstore.R;
import com.apps.webpouyaco.bookstore.models.BookModel;
import com.apps.webpouyaco.bookstore.utilities.Interfaces;
import com.apps.webpouyaco.bookstore.utilities.NetworkRequests;


public class HomePage extends Fragment implements Interfaces.NetworkListeners {


    private String urlJsonObj = "http://api.androidhive.info/volley/person_object.json";
    View view;
    // Progress dialog
    private ProgressDialog pDialog;


    public HomePage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        view.findViewById(R.id.one_frag_get_book_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBooks();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void getBooks() {
        showpDialog();
        NetworkRequests.getRequest(urlJsonObj, this, urlJsonObj);
    }


    @Override
    public void onResponse(String response, String tag) {
        BookModel model = JSON.parseObject(response, BookModel.class);
        ((TextView) view.findViewById(R.id.one_frag_get_book_txt)).setText(model.toString());
        view.findViewById(R.id.explanation1).setVisibility(View.INVISIBLE);
        hidepDialog();
    }

    @Override
    public void onError(VolleyError error, String tag) {

    }

    @Override
    public void onOffline(String tag) {

    }
}