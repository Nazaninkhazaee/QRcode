package com.nazanin.khazaee.kavosh.Barcode;

import android.content.Context;

/**
 * Created by M.jahani on 01/28/2018.
 */

public class BarcodePresenter implements BarcodeContract.Presenter {
    private BarcodeContract.View view;
    BarcodeModel model;

    @Override
    public void attachView(BarcodeContract.View view) {
        this.view = view;
        model = new BarcodeModel(this);
    }

    @Override
    public void show(String barcode) {
        model.getBarcodeDetail(barcode);
    }

    @Override
    public void onShowResult(String name, String family, String mobile, String email,String value,String sum) {
        view.onShowResult(name, family, mobile, email,value,sum);
    }

    @Override
    public void onError() {
        view.onError();
    }

    @Override
    public void onSave(String name_b, String family_b, String mobile_b, String email_b,String barcode) {
        model.insertDetailsUsers(name_b, family_b, mobile_b, email_b,barcode);
    }

    @Override
    public void attachContext(Context mContext) {
        model.attachContext(mContext);
    }

    @Override
    public void saveSuccess() {
        view.saveSuccess();
    }


}
