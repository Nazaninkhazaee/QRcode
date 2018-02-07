package com.nazanin.khazaee.kavosh.utils;

import android.content.Context;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.nazanin.khazaee.kavosh.R;

/**
 * Created by M.jahani on 01/28/2018.
 */

public class PublicMethods {
    public static void showToast(Context mContext, String msg) {

         //Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        StyleableToast.makeText(mContext, msg, R.style.mytoast).show();
    }
}
