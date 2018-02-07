package com.nazanin.khazaee.kavosh.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nazanin.khazaee.kavosh.Barcode.BarcodeContract;

import static android.content.ContentValues.TAG;

/**
 * Created by n.khazaee on 1/30/2018.
 */

public class SqliteDBHandler extends SQLiteOpenHelper {
    String tbluser =
            "CREATE TABLE tbl_users ( " +
                    " id        INTEGER         PRIMARY KEY AUTOINCREMENT" +
                    " NOT NULL" +
                    " UNIQUE," +
                    "name      VARCHAR( 50 )," +
                    "family    VARCHAR( 50 )," +
                    "mobile    TEXT( 30 )," +
                    " email     VARCHAR( 80 ) " +
                    // "barcode   VARCHAR( 500 )" +
                    //  "id_master INTEGER  REFERENCES tbl_master ( id ) " +
                    ");";

    //    String tblmaster =""+
//            "CREATE TABLE tbl_master(" +
//                    "id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL UNIQUE ," +
//                    "number_user INTEGER   NOT NULL UNIQUE ," +
//                    "password  VARCHAR( 20 )  NOT NULL" +
//                    ")";

    public SqliteDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(tbluser);

        //sqLiteDatabase.execSQL(tblmaster);

    }

    public void insertUsers(String name_b, String family_b, String mobile_b, String email_b) {

        String insertQuery =
                "INSERT INTO " +
                        "tbl_users" +
                        "( name,family,mobile,email ) " +
                        "VALUES( '" + name_b + "'  ,  '" + family_b + "' , '" + mobile_b + "' , '" + email_b + "')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
        db.close();

    }

    public void listUsers() {
        String test = "";
        String Query =
                "select * from tbl_users";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        while (cursor.moveToNext() == true) {
            test += cursor.getString(0) + "\n";
        }
        Log.d(TAG, "Users:" + test);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_users");

    }
}
