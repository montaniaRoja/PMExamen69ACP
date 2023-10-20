package com.example.pmexamen69;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombres, telefono, notas;
    Button btnCountry, btnSave, btnList;

    Spinner comboPaises;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres=(EditText)findViewById(R.id.txtName);
        telefono=(EditText) findViewById(R.id.txtPhone);
        notas=(EditText) findViewById(R.id.txtComment);
        comboPaises=(Spinner) findViewById(R.id.countrySelect);
        btnCountry=(Button) findViewById(R.id.btnCountry);
        btnSave=(Button) findViewById(R.id.btnSave);
        btnList=(Button) findViewById(R.id.btnList);

        View.OnClickListener butonclick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Class<?> actividad = null;
                if (view.getId() == R.id.btnCountry) {
                    actividad = CountryAdd.class;
                }

                if(actividad != null)
                {
                    NoveActivity(actividad);
                }

            }
        };
        btnCountry.setOnClickListener(butonclick);
    }

    private void NoveActivity(Class<?> actividad)
    {
        Intent intent = new Intent(getApplicationContext(),actividad);
        startActivity(intent);
    }



}