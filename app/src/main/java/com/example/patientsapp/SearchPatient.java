package com.example.patientsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchPatient extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    AppCompatButton b1;
    String getPatientcode,getName,getAddress,getMobile,getDoctorname;
    Dbtext mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);
        ed1=(EditText)findViewById(R.id.ptncd);
        ed2=(EditText)findViewById(R.id.nm);
        ed3=(EditText)findViewById(R.id.adrs);
        ed4=(EditText)findViewById(R.id.mobile);
        ed5=(EditText)findViewById(R.id.drnm);
        b1=(AppCompatButton)findViewById(R.id.srch);
        mydb=new Dbtext(this);
        mydb.getWritableDatabase();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               getMobile=ed4.getText().toString();
                Cursor c=mydb.SearchPatient(getMobile);
                if(c.getCount()==0)
                {
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed5.setText("");
                    Toast.makeText(getApplicationContext(),"Patient is not found",Toast.LENGTH_LONG).show();
                }
                else
                {
                    while(c.moveToNext())
                    {
                        getPatientcode=c.getString(1);
                        getName=c.getString(2);
                        getAddress=c.getString(3);
                        getDoctorname=c.getString(5);
                    }
                    ed1.setText(getPatientcode);
                    ed2.setText(getName);
                    ed3.setText(getAddress);
                    ed5.setText(getDoctorname);
                }
            }
        });
    }
}