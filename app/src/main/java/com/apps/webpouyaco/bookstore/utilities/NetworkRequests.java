package com.apps.webpouyaco.bookstore.utilities;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apps.webpouyaco.bookstore.AppController;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.apps.webpouyaco.bookstore.utilities.Constants.DEBUG;
import static com.apps.webpouyaco.bookstore.utilities.Constants.LOG_TAG;
import static com.apps.webpouyaco.bookstore.utilities.Snippets.isOnline;

/**
 * Created by Ashkan on 7/21/2016.
 */
public class NetworkRequests {

    public static void getRequest(final String url, final Interfaces.NetworkListeners listener, final String tag) {
        if (DEBUG) {
            Log.d(LOG_TAG, " url = " + url);
        }

        // creating volley string request
        final StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (DEBUG) {
                    Log.d(LOG_TAG, " response for url " + url + " ===== " + response);
                }
                listener.onResponse(response, tag);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // something went wrong
                listener.onError(error, tag);
            }
        });


        if (isOnline(AppController.applicationContext)) {
            AppController.getInstance().addToRequestQueue(strReq, "request");
        } else {
            listener.onOffline(tag);
        }

    }

    public static void postRequest(final String url, final Interfaces.NetworkListeners listener,
                                   final String tag, final Map<String, String> postParams) {


        if (DEBUG) {
            Log.d(LOG_TAG, tag + " url = " + url);
        }
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (!(response.contains("ا") || response.contains("ب") || response.contains("پ") ||
                        response.contains("ت") || response.contains("ث") || response.contains("ج")
                        || response.contains("چ") || response.contains("ح") || response.contains("خ")
                        || response.contains("د") || response.contains("ذ") || response.contains("ر")
                        || response.contains("ز") || response.contains("ژ") || response.contains("س")
                        || response.contains("ش") || response.contains("ص") || response.contains("ض")
                        || response.contains("ط") || response.contains("ظ") || response.contains("ع")
                        || response.contains("غ") || response.contains("ف") || response.contains("ق")
                        || response.contains("ک") || response.contains("گ") || response.contains("ل")
                        || response.contains("م") || response.contains("ن") || response.contains("و")
                        || response.contains("ه") || response.contains("ی"))) {
                    try {
                        response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                if (DEBUG) {
                    Log.d(LOG_TAG, " response for url " + url + " ===== " + response);
                }
                listener.onResponse(response, tag);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (DEBUG) {
                    if (error.networkResponse != null) {
                        Log.d(LOG_TAG, " error for url " + url + " ===== " + error.networkResponse.statusCode);
                    } else {
                        Log.d(LOG_TAG, " error for url " + url);
                    }

                }
                listener.onError(error, tag);
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return postParams;
            }
        };
        if (isOnline(AppController.applicationContext)) {
            AppController.getInstance().addToRequestQueue(strReq, "request");
        } else {
            listener.onOffline(tag);
        }

    }
}