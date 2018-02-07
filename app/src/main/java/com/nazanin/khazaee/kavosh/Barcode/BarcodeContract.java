package com.nazanin.khazaee.kavosh.Barcode;

import android.content.Context;
import android.content.Intent;

/**
 * Created by M.jahani on 01/28/2018.
 */

public interface BarcodeContract {
    interface View {
        void onShowResult(String name, String family, String mobile, String email ,String value,String sum);
        void onError();
        void saveSuccess();
    }

    interface Presenter {
        void attachView(View view);
        void show(String barcode);
        void onShowResult(String name, String family, String mobile, String email,String value,String sum);
        void onError();
        void saveSuccess();
        void onSave(String name_b, String family_b, String mobile_b, String email_b,String barcode);
        void attachContext(Context mContext) ;


    }
}
