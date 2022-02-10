package com.example.patientsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatient extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4,ed5;
AppCompatButton b1;
String getPatientcode,getName,getAddress,getMobileno,getDoctorno;
Dbtext mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        ed1=(EditText)findViewById(R.id.ptncode);
        ed2=(EditText)findViewById(R.id.name);
        ed3=(EditText)findViewById(R.id.address);
        ed4=(EditText)findViewById(R.id.mob);
        ed5=(EditText)findViewById(R.id.drname);

        mydb=new Dbtext(this);
        mydb.getWritableDatabase();

        b1=(AppCompatButton)findViewById(R.id.sub);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPatientcode=ed1.getText().toString();
                getName=ed2.getText().toString();
                getAddress=ed3.getText().toString();
                getMobileno=ed4.getText().toString();
                getDoctorno=ed5.getText().toString();

                boolean status=mydb.insertPatient(getPatientcode,getName,getAddress,getMobileno,getDoctorno);
                if (status==true)
                {
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    ed5.setText("");
                    Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}