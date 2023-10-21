package com.example.pmexamen69;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nombres, telefono, notas;
    Button btnCountry, btnSave, btnList;
    SQLiteConexion conexion;
    Spinner comboPaises;
    ArrayList<Paises> listPaises;
    ArrayList<String> ArregloPaises;
    ImageView imageView;


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
        //imageView=findViewById(R.id.imageView);
        //imageView.setImageResource(R.drawable.imagen);

        conexion =  new SQLiteConexion(this, Transacciones.namedb, null, 1);
        GetPaises();

        View.OnClickListener butonclick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Class<?> actividad = null;
                if (view.getId() == R.id.btnCountry) {
                    actividad = CountryAdd.class;
                }
                else if (view.getId() == R.id.btnList) {
                    actividad = MainActivity2.class;
                }

                if(actividad != null)
                {
                    NoveActivity(actividad);
                }

            }
        };
        btnList.setOnClickListener(butonclick);
        btnCountry.setOnClickListener(butonclick);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {AddContact();

            }
        });
    }

    private void AddContact() {
        String nombre = nombres.getText().toString().trim();
        String numeroTelefono = telefono.getText().toString().trim();
        String comentario = notas.getText().toString().trim();

        if (nombre.isEmpty() || numeroTelefono.isEmpty() || comentario.isEmpty()) {
            if(nombre.isEmpty()){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Por favor, complete el nombre.");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
            else if(numeroTelefono.isEmpty()){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Por favor, complete el Telefono.");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
            else if(comentario.isEmpty()){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Por favor, complete un comentario.");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
            // Los campos están vacíos, muestra un diálogo de alerta

        }
        else {
            try {
                String paisSeleccionado = comboPaises.getSelectedItem().toString();
                SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.namedb, null, 1);
                SQLiteDatabase db = conexion.getWritableDatabase();

                ContentValues valores = new ContentValues();
                valores.put(Transacciones.nombres, nombre);
                valores.put(Transacciones.telefono, numeroTelefono);
                valores.put(Transacciones.notas, comentario);
                valores.put(Transacciones.pais, paisSeleccionado);

                Long Result = db.insert(Transacciones.Tabla1, Transacciones.id, valores);

                Toast.makeText(this, getString(R.string.Respuesta), Toast.LENGTH_SHORT).show();
                db.close();
                nombres.setText("");
                telefono.setText("");
                notas.setText("");
                nombres.requestFocus();

            } catch (Exception exception) {
                Toast.makeText(this, getString(R.string.ErrorResp), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void GetPaises() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Paises pais = null;
        listPaises = new ArrayList<Paises>();

        Cursor cursor = db.rawQuery(Transacciones.SelectTablePaises, null);
        while (cursor.moveToNext()) {
            pais = new Paises();
            pais.setId(cursor.getInt(0));
            pais.setNombrePais(cursor.getString(1));

            listPaises.add(pais);
        }

        cursor.close();

        FillCombo();

    }

    private void FillCombo() {

        ArregloPaises = new ArrayList<String>();

        for(int i = 0; i < listPaises.size(); i++)
        {
            ArregloPaises.add(listPaises.get(i).getNombrePais() );
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ArregloPaises);
        comboPaises.setAdapter(adapter);
    }


    private void NoveActivity(Class<?> actividad)
    {
        Intent intent = new Intent(getApplicationContext(),actividad);
        startActivity(intent);
    }

}