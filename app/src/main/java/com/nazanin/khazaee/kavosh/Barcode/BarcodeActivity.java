package com.nazanin.khazaee.kavosh.Barcode;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nazanin.khazaee.kavosh.R;
import com.nazanin.khazaee.kavosh.utils.BaseActivity;
import com.nazanin.khazaee.kavosh.utils.PublicMethods;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.view.OnViewChangedNotifier;

import java.io.IOException;

@EActivity(R.layout.activity_barcode)

public class BarcodeActivity extends BaseActivity implements BarcodeContract.View {
    BarcodeContract.Presenter presenter;
    Context mContext = this;
    MediaPlayer mp_show;
    MediaPlayer mp_save;


    @ViewById
    EditText barcode;
    @ViewById
    EditText name_b;
    @ViewById
    EditText family_b;
    @ViewById
    EditText mobile_b;
    @ViewById
    EditText email_b;





    @AfterViews
    void init() {
        presenter = new BarcodePresenter();
        presenter.attachView(this);
        presenter.attachContext(this);
        mp_show = MediaPlayer.create(this, R.raw.show);
        mp_save = MediaPlayer.create(this, R.raw.button);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)

        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1500);
        }


    }

    @Click
    void show() {

        int len = barcode.length();
        if (len == 0) {
            new IntentIntegrator(mActivity).initiateScan();
           // PublicMethods.showToast(mContext, "لطفا بارکد را وارد کنید");
           // cleanForm();
        } else if (len > 0) {
            presenter.show(barcode.getText().toString());
            mp_show.start();
        }
    }

    @Click
    void save() {
        barcode.getText().toString();
        if (barcode.length()==0){
            PublicMethods.showToast(mContext,"لطفا بارکد را وارد نمایید!");
        }
        else {
        presenter.onSave(
                name_b.getText().toString(),
                family_b.getText().toString(),
                mobile_b.getText().toString(),
                email_b.getText().toString(),
                barcode.getText().toString()
        );}

    }

    @Override
    public void onShowResult(String name, String family, String mobile, String email, String value, String sum) {

        if (sum.equals(value)) {
            name_b.setText(name);
            family_b.setText(family);
            mobile_b.setText(mobile);
            email_b.setText(email);

        } else {
            onError();
        }

    }

    @Override
    public void onError() {
        PublicMethods.showToast(mContext, "بارکد نادرست می باشد!");
        cleanForm();

    }

    @Override
    public void saveSuccess() {
        mp_save.start();
        PublicMethods.showToast(mContext, "اطلاعات شما با موفقیت ذخیره شد");
        cleanForm();

    }

    void cleanForm() {
        barcode.setText("");
        name_b.setText("");
        family_b.setText("");
        mobile_b.setText("");
        email_b.setText("");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                PublicMethods.showToast(mContext , "nullllll");
            } else {
                barcode.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
