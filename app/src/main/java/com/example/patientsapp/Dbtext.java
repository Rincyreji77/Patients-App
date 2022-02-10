package com.example.patientsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbtext extends SQLiteOpenHelper {
    static String Dbname = "PatientApp.db";
    static String Tablename = "patient";
    static String col1 = "id";
    static String col2 = "patientcode";
    static String col3 = "name";
    static String col4 = "address";
    static String col5 = "mobile";
    static String col6 = "doctorname";

    public Dbtext(Context context) {
        super(context, Dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+Tablename+"("+col1+
                " integer primary key autoincrement,"+col2+
                " text," +col3+ " text,"+col4+ " text,"+col5+
                " text,"+col6+" text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists "+Tablename;
        db.execSQL(query);
        onCreate(db);

    }

    public boolean insertPatient(String patientcode, String name, String address, String mobile, String doctorname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(col2, patientcode);
        content.put(col3, name);
        content.put(col4, address);
        content.put(col5, mobile);
        content.put(col6, doctorname);
        Long status = db.insert(Tablename, null, content);
        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor SearchPatient(String mobile)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from "+Tablename+" where "
                +col5+"="+"'"+mobile+"'";

        Cursor c=db.rawQuery(query,null);
        return c;
    }
}