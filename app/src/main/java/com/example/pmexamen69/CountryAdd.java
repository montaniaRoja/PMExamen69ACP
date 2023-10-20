package com.example.pmexamen69;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CountryAdd extends AppCompatActivity {

    EditText pais;
    Button btnSaveCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_add);

        pais = (EditText) findViewById(R.id.txtPais);

        btnSaveCountry = (Button) findViewById(R.id.btnSaveCountry);

        btnSaveCountry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) { addCountry();

            }
        });
    }

    private void addCountry()

    {
        try {
            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.namedb, null,1);
            SQLiteDatabase db =  conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Transacciones.pais, pais.getText().toString());
            Long Result = db.insert(Transacciones.Tabla2, Transacciones.id, valores);

            Toast.makeText(this, getString(R.string.Respuesta), Toast.LENGTH_SHORT).show();
            db.close();
        }
        catch (Exception exception)
        {
            Toast.makeText(this, getString(R.string.ErrorResp), Toast.LENGTH_SHORT).show();
        }

    }

}