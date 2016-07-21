package com.apps.webpouyaco.bookstore.utilities;

import com.android.volley.VolleyError;

/**
 * Created by Ashkan on 7/21/2016.
 */
public class Interfaces {


    public interface NetworkListeners {
        public void onResponse(String response, String tag);

        public void onError(VolleyError error, String tag);

        public void onOffline(String tag);
    }

}
