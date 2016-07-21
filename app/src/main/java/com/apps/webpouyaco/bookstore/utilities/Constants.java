package com.apps.webpouyaco.bookstore.utilities;

/**
 * Created by Ashkan on 7/14/2016.
 */

import java.text.DecimalFormat;

/**
 * be cause this app will be used for different websites, it needs different Constants too
 */
public class Constants {

    //request tags:

    //Name Strings:
    public static String LOG_TAG = "**** DEBUG****";

    // local

    //Shared Pref Keys
    public static String SP_FILE_NAME_BASE = "sp_file_base";
    public static String FALSE = "FALSE";
    public static String TRUE = "TRUE";
    public static String FIRST_NAME = "FIRST_NAME";
    public static String LAST_NAME = "LAST_NAME";
    public static String EMAIL = "EMAIL";
    public static String PASSWORD = "PASSWORD";
    public static String RETYPE_PASSWORD = "RETYPE_PASSWORD";




    //Numbers:
    public static final int VOLLEY_TIME_OUT = 25000;
    public static final int COUNT = 10;


    public static final int NOTIFICATION_ID = 900;
    public static boolean DEBUG = true;

    public static String persianNumbers(String num){
        try {
            char[] persianNumbers = {'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < num.length(); i++) {
                if (Character.isDigit(num.charAt(i))) {
                    builder.append(persianNumbers[(int) (num.charAt(i)) - 48]);
                } else {
                    builder.append(num.charAt(i));
                }
            }
            return builder.toString();
        } catch (Exception e) {
            return num;
        }
    }

    public static String formatPrice(String num) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(Float.valueOf(num));
    }

}
