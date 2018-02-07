package com.nazanin.khazaee.kavosh.Barcode;

import android.content.Context;
import android.util.Log;

import com.nazanin.khazaee.kavosh.utils.BaseActivity;
import com.nazanin.khazaee.kavosh.utils.PublicMethods;
import com.nazanin.khazaee.kavosh.utils.SqliteDBHandler;

import static android.content.ContentValues.TAG;

/**
 * Created by M.jahani on 01/28/2018.
 */

public class BarcodeModel extends BaseActivity {
    private static final String TAG = BarcodeModel.class.getSimpleName();
    BarcodeContract.Presenter presenter;
    SqliteDBHandler db;
    Context mContext;


    public BarcodeModel(BarcodeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void attachContext(Context mContext) {
        this.mContext = mContext;
    }


    public void getBarcodeDetail(String barcode) {
        String[] len = barcode.split("#");
        if (len.length != 2 ) {
            presenter.onError();
        } else {
            String user = len[0];
            String value = len[1];
            String[] parts = user.split(":");
            if (parts.length != 4) {
                presenter.onError();
            } else {
                String name = parts[0];
                String family = parts[1];
                String mobile = parts[2];
                String email = parts[3];
                int str = name.length() + family.length() + mobile.length() + email.length();
                String sum = String.valueOf(str);

                Log.d(TAG, "getBarcodeDetail: " + name + " " + family + " " + mobile + " " + email + " " + value + "" + sum);

                presenter.onShowResult(name, family, mobile, email, value, sum);
            }

        }
    }

    public void insertDetailsUsers(String name_b, String family_b, String mobile_b, String email_b,String barcode) {
        createDB();

        int len_name = name_b.length();
        int len_family = family_b.length();
        int len_mobile = mobile_b.length();
        int len_email = email_b.length();
        int str = len_name+len_family+len_mobile+len_email;
        String sum = String.valueOf(str);

        String[] len = barcode.split("#");
        String user = len[0];
        String value = len[1];
        if (sum.equals(value)){
            Log.d(TAG, "insertDetailsUsers:" + name_b + " " + family_b + " " + mobile_b + " " + email_b);
            db.insertUsers(name_b, family_b, mobile_b, email_b);
            presenter.saveSuccess();
            db.listUsers();
        }
        else {
            presenter.onError();
        }


    }

    void createDB() {
        db = new SqliteDBHandler(mContext, "Barcode.db", null, 1);
    }

}
